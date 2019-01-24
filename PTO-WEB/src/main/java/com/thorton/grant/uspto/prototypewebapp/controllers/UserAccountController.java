package com.thorton.grant.uspto.prototypewebapp.controllers;


import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.RegistrationDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.TradeMarkApplicationsInternalIDDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;

import com.thorton.grant.uspto.prototypewebapp.service.registratrion.OnRegistrationCompleteEvent;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

@Controller
public class UserAccountController {


    private final ServiceBeanFactory serviceBeanFactory;

    public UserAccountController(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }



    // profile/ user account page controller
    @RequestMapping({"/accounts/userHome"})
    public String usreProfile(Model model){

        // retireve owner using email from the credentials ..
        // add find by email methods to both personal data and credentails cass

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("logged in user email :"+authentication.getPrincipal());
        System.out.println("logged in user name: "+authentication.getName());
        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);


        // check if user have address information set.


        return "account/userHome";
    }


    @RequestMapping({"/accounts/Home"})
    public String landingPageLoggedIn(Model model){

        // retireve owner using email from the credentials ..
        // add find by email methods to both personal data and credentails cass

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);


        // check if user have address information set.


        return "account/home";
    }



    @RequestMapping({"/accounts/dashboard","accounts/dashboard"})
    public String dashboard(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("logged in user email :"+authentication.getPrincipal());
        System.out.println("logged in user name: "+authentication.getName());
        PTOUserService  ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);


        ////////////////////////////////////////////////////////////////////////////////////////////////
        // find user's trademark applications
        // iterate over myTrademarks and get their internal ids into an array list
        ////////////////////////////////////////////////////////////////////////////////////////////////

        BaseTrademarkApplication baseTrademarkApplication = null;
        ArrayList<String> userfilingTableRowsID = new ArrayList<>();
        ArrayList<String> userfilingTableRowsowneName = new ArrayList<>();
        ArrayList<String> userfilingTableRowsStatus = new ArrayList<>();
        ArrayList<String> userfilingTableRoowsTMname = new ArrayList<>();
        for(Iterator<BaseTrademarkApplication> iter = ptoUser.getMyApplications().iterator(); iter.hasNext(); ) {


            baseTrademarkApplication = iter.next();
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@User Account Controller@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("internal : "+baseTrademarkApplication.getApplicationInternalID());

            userfilingTableRowsID.add(  baseTrademarkApplication.getApplicationInternalID());
            userfilingTableRowsowneName.add(ptoUser.getFirstName()+" "+ptoUser.getLastName());
            userfilingTableRowsStatus.add("Draft");
            userfilingTableRoowsTMname.add(baseTrademarkApplication.getTrademarkName());

        }
        Collections.reverse(userfilingTableRowsID);
        Collections.reverse(userfilingTableRowsowneName);
        Collections.reverse(userfilingTableRowsStatus);
        Collections.reverse(userfilingTableRoowsTMname);




        //////////////////////////////////////////////////////////////////////////////////////////////
        //model.addAttribute("newFilingTableRow", userfilingTableRowsID);


        TradeMarkApplicationsInternalIDDTO tradeMarkApplicationsInternalIDDTO = new TradeMarkApplicationsInternalIDDTO();
        tradeMarkApplicationsInternalIDDTO.setMyApplicationIDs(userfilingTableRowsID);
        tradeMarkApplicationsInternalIDDTO.setMyApplicationOwners(userfilingTableRowsowneName);
        tradeMarkApplicationsInternalIDDTO.setMyApplicationStatues(userfilingTableRowsStatus);
        tradeMarkApplicationsInternalIDDTO.setMyApplicationTMname(userfilingTableRoowsTMname);
        model.addAttribute("newFilingTableRow", tradeMarkApplicationsInternalIDDTO);

        //model.addAttribute("trademarkApplication", baseTrademarkApplication);

        //////////////////////////////////////////////////////////////////////////////////////////////
        if(ptoUser.isProfileComplete() == false){ // redirect back to userAccounts if profile is not complete yet
            model.addAttribute("message", "Please Complete your Contact Information First.");
            return "account/userHome";
        }
        //////////////////////////////////////////////////////////////////////////////////////////////

        return "account/dashboard2";
        //return baseTrademarkApplication.getLastViewModel();

    }







}
