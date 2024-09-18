package io.security.corespringsecurity.aopsecurity;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AopSecurityController {
    @GetMapping("/preAuthorize")
    @PreAuthorize("hasRole('ROLE_USER') AND #account.username == principal.username")
    public String preAuthorize(AccountDto account, Model model){

        System.out.println("account.username = " + account.getUsername() + "");
        model.addAttribute("method", "Success @PreAuthorize");

        return "aop/method";
    }
}
