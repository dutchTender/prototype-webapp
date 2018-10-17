package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.UserCredentialsDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.service.UserRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {


    private final UserRegistrationService service;

    public RegistrationController(UserRegistrationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showForm(WebRequest request, Model model){

        System.out.print("get process for registration .......");
        // get values from newUser form
        // assign a use_role to new user
        // save new user data into User table

        // create owner object
        // copy over name info and user id/email
        // add owner object to model as attribute
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        model.addAttribute("userCredentialsDTO", userCredentialsDTO);


        return "registration/index";


    }


    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("userCredentialsDTO") @Valid UserCredentialsDTO accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        System.out.print("post process for registration .......");
        System.out.println("DTO capture output: ");
        System.out.println();
        System.out.println("####################################################################");
        System.out.println(accountDto.getEmail());
        System.out.println(accountDto.getFirstName());
        System.out.println(accountDto.getLastName());
        System.out.println(accountDto.getPassword());

        System.out.println(accountDto.getPhoneNumber());
        System.out.println("####################################################################");






         UserCredentials  registered = new UserCredentials() ;
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            System.out.println("result has errors !!!!!!!!!!!!!!!!!!!!"+result.getAllErrors().toString());

            return new ModelAndView("registration/index", "user", accountDto);
        }
        else {
            //return new ModelAndView("/account/userHome", "users", accountDto);
            return new ModelAndView("login", "user",accountDto);
        }



    }
    private UserCredentials createUserAccount(UserCredentialsDTO accountDto, BindingResult result) {
        UserCredentials registered = null;

        registered = service.registerNewUserAccount(accountDto);

        return registered;
    }
}
