package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.UserCredentialsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {


    @RequestMapping({"/login"})
    public ModelAndView login(){

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        //return "login";

        UserCredentialsDTO accountDto = new UserCredentialsDTO();
        return new ModelAndView("login", "user",accountDto);
    }


}
