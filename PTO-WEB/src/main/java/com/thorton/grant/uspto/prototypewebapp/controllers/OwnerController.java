package com.thorton.grant.uspto.prototypewebapp.controllers;


import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
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

        return "/account/userHome";
    }

}
