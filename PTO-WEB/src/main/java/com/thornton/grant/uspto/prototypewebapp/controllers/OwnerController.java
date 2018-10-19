package com.thornton.grant.uspto.prototypewebapp.controllers;


import com.thornton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thornton.grant.uspto.prototypewebapp.interfaces.PTOUserService;
import com.thornton.grant.uspto.prototypewebapp.model.entities.PTOUser;
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



    @RequestMapping({"/userProfile","/account/userProfile"})
    public String userProfile(Model model){

        // retrieve owner using email from the credentials ..
        // add find by email methods to both personal data and credentails cass

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("logged in user email :"+authentication.getPrincipal());
        System.out.println("logged in user name: "+authentication.getName());
        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        return "account/userProfile";
    }



    @RequestMapping({"/dashboard","/account/dashboard"})
    public String dashboard(){

        //need to add model to view

        return "account/dashboard";
    }




}
