package io.security.corespringsecurity.security.factory;

import io.security.corespringsecurity.security.service.SecurityResourceService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;

//DB로 부터 자원 권한정보를 가져와야한다..
public class UrlResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {

    private SecurityResourceService securityResourceService;
    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap;
    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {

        if(resourceMap == null){
            init();
        }
        return resourceMap;
    }
    private void init(){
        resourceMap = securityResourceService.getResourceList();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }



}
