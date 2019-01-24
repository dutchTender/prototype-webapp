package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.IUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.PasswordSetDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.RegistrationDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.UserCredentialsDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;


@Controller
public class EmailVerificationController {

    ///////////////////////////////////////////////////////////////
    // email verification handler
    ///////////////////////////////////////////////////////////////
    @Autowired
    private IUserService service;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmailVerificationController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private String account_email;
    private String account_token;

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_token() {
        return account_token;
    }

    public void setAccount_token(String account_token) {
        this.account_token = account_token;
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%email activation link clicked!!!!!%%%%%%%%%%%%%%%%%%%%%");

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = "auth.message.invalidToken";
            RegistrationDTO registrationDTO = new RegistrationDTO();
            model.addAttribute("userCredentialsDTO", registrationDTO);
            model.addAttribute("message", message);

            return "registration/index";
        }

        UserCredentials userCredentials = verificationToken.getNewCredential();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiredTime().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = "token has expired";
            RegistrationDTO registrationDTO = new RegistrationDTO();
            model.addAttribute("userCredentialsDTO", registrationDTO);
            model.addAttribute("message", messageValue);

            return "registration/index";
        }

        //////////////////////////////////////////////////////////////////////////
        // activates account
        //////////////////////////////////////////////////////////////////////////
        userCredentials.setActive(1);

        service.saveRegisteredUserCredential(userCredentials);
        System.out.println ("account : " +userCredentials.getEmail() + "  is now active. user still need to set account password.");
        ///////////////////////////////////////////////////////////////////////////
        // persists user data locally to this servlet
        ///////////////////////////////////////////////////////////////////////////
        setAccount_email(userCredentials.getEmail());
        setAccount_token(token);
        //////////////////////////////////////////////////////////////////////////
        // these two values will be reset every time this work flow is accessed
        //////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////
        // create DTO object and bind it to form on the next view
        //////////////////////////////////////////////////////////////////////////
      // PasswordSetDTO passwordSetDTO = new PasswordSetDTO();
       //model.addAttribute("passwordSetDTO", passwordSetDTO);

       //return "registrationConfirm/activate";

        //String token2 = getAccount_token();
        //VerificationToken verificationToken2 = service.getVerificationToken(token2);
        //UserCredentials userCredentials2 = verificationToken.getNewCredential();
       // userCredentials.setPassword(bCryptPasswordEncoder.encode(r.getPassword()));
        //userCredentials.setPasswordConfirm(bCryptPasswordEncoder.encode(passwordSetDTO.getPassword()));
        // password matches confirms checked with js on previous page

       // service.saveRegisteredUserCredential(userCredentials);


        String server_message = "Registration Compelete. You can now sign into the application.";
        //redirectAttributes.addFlashAttribute("message",server_message );
       model.addAttribute("message", server_message);


        return "login";

    }



    @RequestMapping(value = "/CompleteRegistration", method = RequestMethod.POST)
    public String savePassword(
            Model model,
            @ModelAttribute("passwordSetDTO") @Valid PasswordSetDTO passwordSetDTO,
            BindingResult result,
            WebRequest request,
            Errors errors){



            String account = getAccount_email();
            System.out.println("saving password for account : " + account);

            String token = getAccount_token();
            VerificationToken verificationToken = service.getVerificationToken(token);
            UserCredentials userCredentials = verificationToken.getNewCredential();
            userCredentials.setPassword(bCryptPasswordEncoder.encode(passwordSetDTO.getPassword()));
            userCredentials.setPasswordConfirm(bCryptPasswordEncoder.encode(passwordSetDTO.getPassword()));
            // password matches confirms checked with js on previous page
            service.saveRegisteredUserCredential(userCredentials);


            String server_message = "Registration Compelete. You can now sign into the application.";
            //redirectAttributes.addFlashAttribute("message",server_message );
            model.addAttribute("message", server_message);


            return "login";



    }
}
