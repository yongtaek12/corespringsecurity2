package io.security.corespringsecurity.security.factory;

import io.security.corespringsecurity.security.service.SecurityResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
public class MethodResourcesFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

    private SecurityResourceService securityResourceService;

    private String resourcesType;
    private LinkedHashMap<String, List<ConfigAttribute>> resourcesMap;

    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    public void setResourcesType(String resourcesType) {
        this.resourcesType = resourcesType;
    }
    public void init(){
        if("method".equals(resourcesType)){
            resourcesMap = securityResourceService.getMethodResourceList();
        } else if ("pointcut".equals(resourcesType)) {
            resourcesMap = securityResourceService.getPointcutResourceList();
        }
    }

    @Override
    public LinkedHashMap<String, List<ConfigAttribute>> getObject(){
        if(resourcesMap ==null){
            init();
        }
        return resourcesMap;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class<LinkedHashMap> getObjectType() {
        return LinkedHashMap.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
