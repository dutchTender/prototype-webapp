package com.thorton.grant.uspto.prototypewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping({"", "/","/index","/index.html", "/home"})
    public String index(){
        // get owner info
        System.out.println("#############################################################");
        return "index";
    }


    @RequestMapping({"/aboutUs"})
    public String info(){


        return "aboutUs";
    }


    @RequestMapping({"/contact"})
    public String contact(){

        return "/resources/templates/contacts/index";
    }
}
