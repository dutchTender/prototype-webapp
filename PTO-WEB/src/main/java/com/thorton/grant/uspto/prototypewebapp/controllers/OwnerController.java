package com.thorton.grant.uspto.prototypewebapp.controllers;


import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.PTOUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {


    private final ServiceBeanFactory serviceBeanFactory;

    public OwnerController(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }



    @RequestMapping({"/accounts/userHome"})
    public String dashboard(Model model){

        // retireve owner using email from the credentials ..
        // add find by email methods to both personal data and credentails cass

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("logged in user email :"+authentication.getPrincipal());
        System.out.println("logged in user name: "+authentication.getName());
        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);



        return "account/userHome";
    }




}
