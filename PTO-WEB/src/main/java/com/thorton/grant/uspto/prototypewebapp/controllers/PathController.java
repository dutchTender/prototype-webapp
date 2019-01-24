package com.thorton.grant.uspto.prototypewebapp.controllers;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.AuthenticationTokenService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.IUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.TwoFactorDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.AuthenticationToken;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.service.mail.gmail.GmailJavaMailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Random;


@Controller
public class PathController {

    private final ServiceBeanFactory serviceBeanFactory;

    private final IUserService service;

    private final GmailJavaMailSenderService mailSender;

    public PathController(ServiceBeanFactory serviceBeanFactory, IUserService service, GmailJavaMailSenderService mailSender) {
        this.serviceBeanFactory = serviceBeanFactory;
        this.service = service;
        this.mailSender = mailSender;
    }

    @RequestMapping({"", "/","/index","/index.html", "/home"})
    public String index(){
        // get owner info
        System.out.println("#############################################################");

        return "public/index2";

        //return "registrationConfirm/VerificationEmail";
    }




    // login intercept
    @RequestMapping({"/verifyAddress"})
    public String verifyAddress(Model model){

        // get access credentials
        // get email and get PTOUser object from repository
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);

        if(ptoUser.isProfileComplete()){
              return "forward:accounts/dashboard";
        }
        else {
            return "forward:accounts/userHome";
        }

    }


    // login intercept
    @RequestMapping({"/2FactorAuth"})
    public String twofactorAuth(Model model) {

        // get access credentials
        // get email and get PTOUser object from repository
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());
        /////////////////////////////////////////////////////////////////////
        // generate token + send email with token
        // link to current user and store in token store
        // add token DTO to model
        /////////////////////////////////////////////////////////////////////

        String viewName="";
        if (ptoUser.isUseTwoFactorAuthentication() == true) {


            Random random = new Random();
            String token = Long.toString(100000 + Math.round(random.nextDouble() * 900000));

            System.out.println("genearated random token for user: " + token);
            service.createAuthenticationToken(credentials, token);


            String recipientAddress = credentials.getEmail();
            String subject = "Two-factor Authentication Code";
            String authToken = token;


            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);


            //email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
            // make send email call none blocking
            new Thread(new Runnable() {
                public void run() {

                    // perform any operation
                    //mailSender.sendEmailverificationLink("http://efile-reimagined.com"+confirmationUrl,recipientAddress);
                    mailSender.sendAuthenticationToken(token, recipientAddress);

                }
            }).start();


            TwoFactorDTO twoFactorDTO = new TwoFactorDTO();
            model.addAttribute("twoFactorDTO", twoFactorDTO);
            viewName = "2factorAuth";

       }
       else{
           viewName="forward:/verifyAddress";

        }


        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);

        return viewName;

    }








    @RequestMapping(value = "/2factorSubmit", method = RequestMethod.POST)
    public String verifyTwoFactorToken(
            Model model,
            @ModelAttribute("twoFactorDTO") @Valid TwoFactorDTO twoFactorDTO,
            BindingResult result,
            WebRequest request,
            HttpServletRequest requesthttp,
            HttpServletResponse response,
            Errors errors,
            RedirectAttributes redirectAttributes){




        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("token value : "+twoFactorDTO.getToken());
        AuthenticationTokenService authenticationTokenService = serviceBeanFactory.getAuthenticationTokenService();
        AuthenticationToken token =authenticationTokenService.findByToken(twoFactorDTO.getToken());
        ///////////////////////////////////////////////
        // get token from twoFactorDTO
        // if token exists ...(check token store)
        ///////////////////////////////////////////////
        // forward user to  /verifyAddress
        ///////////////////////////////////////////////


       if( token != null) {


           return "forward:/verifyAddress";
       }
       else {
           ////////////////////////////////////////////////////////////////
           // probably need to add a message that says bad 2 factor token
           ////////////////////////////////////////////////////////////////
           // add message

           //ServerMessageDTO serverMessageDTO = new ServerMessageDTO();
           //serverMessageDTO.setError("Invalid Two Factor Authentication Code.");

           String server_message = "Invalid Two Factor Authentication Code.";
           //redirectAttributes.addFlashAttribute("message",server_message );
           model.addAttribute("message", server_message);

           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
           if (auth != null){
               new SecurityContextLogoutHandler().logout(requesthttp, response, auth);
           }
           return "forward:/login";
           //return "forward:/2FactorAuthFailure";
       }


    }


    // login intercept
    @RequestMapping({"/logoutConfirm"})
    public String logoutConfirm(Model model) {


        String server_message = "You have Successfully logged out.";
        model.addAttribute("message", server_message);

        return "public/index2";

    }
    ///////////////////////////////////
    // login failure intercept
    ///////////////////////////////////
    @RequestMapping({"/passwordFailure"})
    public String loginFailure(Model model) {


        String server_message = "Password entered was not not correct.";
        //redirectAttributes.addFlashAttribute("message",server_message );
        model.addAttribute("message", server_message);

        return "login";

    }




    @RequestMapping({"/aboutUs"})
    public String info(){

        return "aboutUs";
    }


    @RequestMapping({"/contact"})
    public String contact(){

        return "contacts/index";
    }



    @RequestMapping({"/owner"})
    public String testDashboard(){


        return "owner/index";
    }
}
