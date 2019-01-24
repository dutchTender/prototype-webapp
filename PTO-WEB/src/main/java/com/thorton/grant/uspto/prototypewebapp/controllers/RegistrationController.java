package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.IUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.RegistrationDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;
import com.thorton.grant.uspto.prototypewebapp.service.registratrion.OnRegistrationCompleteEvent;
import com.thorton.grant.uspto.prototypewebapp.service.registratrion.UserRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class RegistrationController {


    private final UserRegistrationService service;

    public RegistrationController(UserRegistrationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showForm(WebRequest request, Model model){

        System.out.print("registration show form pre-binding.......");
        // get values from newUser form
        // assign a use_role to new user
        // save new user data into User table

        // create owner object
        // copy over name info and user id/email
        // add owner object to model as attribute
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("userCredentialsDTO", registrationDTO);


        return "registration/index";

    }




    @Autowired
    ApplicationEventPublisher eventPublisher;
    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("userCredentialsDTO") @Valid RegistrationDTO accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        System.out.print("post process for registration .......form result binded to DTO object");
        System.out.println("DTO capture output: ");
        System.out.println();
        System.out.println("####################################################################");
        System.out.println(accountDto.getEmail());
        System.out.println(accountDto.getFirstName());
        System.out.println(accountDto.getLastName());
        System.out.println("####################################################################");



        ////////////////////////////////
        // main authentication logic
        ///////////////////////////////
         UserCredentials  registered = new UserCredentials() ;
        if (!result.hasErrors()) {
            // account will be created, but no password, and not active
            // also no address or telephone information will be saved here
            registered = createUserAccount(accountDto, result);
            // generate email token

            //////////////////////////////////////////////////////////////////////////////////////
            // nothing to do here, we publish custom registration event.
            // token creation/storage and email notifications are generated in registration event
            //////////////////////////////////////////////////////////////////////////////////////
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
            // new users needs to log in. will need to verify email before activation.
           // no errors ...send user to email verification notifcation page
            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                        (registered, request.getLocale(), appUrl));
            } catch (Exception me) {
                return new ModelAndView("emailError", "user", accountDto);
            }

            //////////////////////////////////////////////////////////////////////////////////////
            // after registration event, return user to check email activation link page
            //////////////////////////////////////////////////////////////////////////////////////
            return new ModelAndView("registration/regConfirm", "user",accountDto);
        }



    }
    private UserCredentials createUserAccount(RegistrationDTO accountDto, BindingResult result) {
        UserCredentials registered = null;

            // this creates an account that requires email verifcation
            registered = service.registerNewUserAccount(accountDto);

        return registered;
    }




}
