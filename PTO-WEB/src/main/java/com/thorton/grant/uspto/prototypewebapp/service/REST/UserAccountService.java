package com.thorton.grant.uspto.prototypewebapp.service.REST;


import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class UserAccountService extends BaseRESTapiService {

    //@Autowired
    //private IUserService service;

    /////////////////////////////////////////////////////////////////////////////////////////
    // based on the profile  ...we should be able
    // to inject the correct bean mapped to the correct host file here
    ////////////////////////////////////////////////////////////////////////////////////////

    //private final ApplicationContext appContext;


    public UserAccountService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }

    //////////////////////////////////////////////////////////////////////////////////
    // header test
    //////////////////////////////////////////////////////////////////////////////////
    // will add request headers for security in the next update
    @RequestMapping(value = "/rest/api/test", headers = "key=val", method = GET)
    @ResponseBody
    public String restTest() {
        return "Get some Foos with Header";
    }





    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/user/update/pw/{password1}/{password2}")
    @ResponseBody
    ResponseEntity<String> updateUserPassword(@PathVariable String password1, @PathVariable String password2){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserCredentialsService userCredentialsService = getServiceBeanFactory().getUserCredentialsService();

        UserCredentials userCredentials = userCredentialsService.findByEmail(email);




        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean pwMatched = bCryptPasswordEncoder.matches(password1, userCredentials.getPassword());

        String statusCode = "200";
        String responseMsg;


        if(pwMatched == false){
            statusCode = "420";
            responseMsg = "Entered current password did not match!";
        }
        else {
            // update pass word
            // verify password is not null
            if(password2 != "" || password2 != null){

                // user userCredentials object to save passowrd, then save object to repository via service
                userCredentials.setPassword(bCryptPasswordEncoder.encode(password2));
                userCredentialsService.save(userCredentials);

                responseMsg = "Your new password have been saved";

            }
            else{
                statusCode = "444";
                responseMsg = "New password can not be empty";
            }
        }

      return buildResponseEnity(statusCode, responseMsg );

    }



    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/user/update/{userAccountField}/{param}")
    @ResponseBody
    ResponseEntity<String> updateUserAccountInfo(@PathVariable String userAccountField , @PathVariable String param){


        PTOUserService ptoUserService = getServiceBeanFactory().getPTOUserService();
        PTOUser ptoUser = getCurrentPTOuser();

        if(userAccountField.equals("State")){
              ptoUser.setState(param); // sets state code
        }

        if(userAccountField.equals("City")){
            ptoUser.setCity(param);
        }

        if(userAccountField.equals("Zipcode")){
            ptoUser.setZipcode(param);
        }

        if(userAccountField.equals("Address")){
            ptoUser.setAddress(param);
        }

        if(userAccountField.equals("Country")){
            ptoUser.setCountry(param); // sets country code
        }

        if(userAccountField.equals("Phone")){
            ptoUser.setPrimaryPhonenumber(param);
        }

        if(userAccountField.equals("TwoFactorEmail")){
            if(param.equals("true")){

                ptoUser.setUseTwoFactorAuthentication(true);
                ptoUser.setTwoFactorAuthType("email");
            }
            else {
                ptoUser.setUseTwoFactorAuthentication(false);
                ptoUser.setTwoFactorAuthType("");
            }
        }


        ////////////////////////////////////////////////
        // check if all required fields are set
        ///////////////////////////////////////////////
        boolean profileSet = true;
        if(ptoUser.getAddress() == null || ptoUser.getAddress().equals("")){
            profileSet = false;
        }
        if(ptoUser.getCity() == null || ptoUser.getCity().equals("")){
            profileSet = false;
        }
        if(ptoUser.getState() == null || ptoUser.getState().equals("")){
            profileSet = false;
        }
        if(ptoUser.getZipcode() == null || ptoUser.getZipcode().equals("")){
            profileSet = false;
        }
       // if(ptoUser.getPrimaryPhonenumber() == null || ptoUser.getPrimaryPhonenumber().equals("")){
        //    profileSet = false;
        //}
        ////////////////////////////////////////////////
        /// end profile check
        ////////////////////////////////////////////////


        if(profileSet == true){
            ManagedContact newContact = createCopyPTOUserInfo4ManagedContact(ptoUser);
            if(ptoUser.getMyManagedContacts().size() ==0){
                ptoUser.addManagedContact(newContact);
            }


        }

        ////////////////////////////////////////////////
        // set ProfileComplete status and save user data
        ////////////////////////////////////////////////
        ptoUser.setProfileComplete(profileSet);
        ptoUserService.save(ptoUser);


        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////
        String statusCode = "200";
        String responseMsg = userAccountField+" has been saved";

        return buildResponseEnity(statusCode, responseMsg );

    }



    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/user/complete/")
    @ResponseBody
    ResponseEntity<String> checkProfileComplete(){

        PTOUser ptoUser = getCurrentPTOuser();

       if(ptoUser.isProfileComplete() == false){

           String statusCode = "444";
           String responseMsg = "User Profile required information is not complete";

           return buildResponseEnity(statusCode, responseMsg );

       }



        String statusCode = "200";
        String responseMsg = "User Profile required information is  complete";

      return buildResponseEnity(statusCode, responseMsg );

    }


    @WebFilter("/REST/apiGateway*")
    public class AddResponseHeaderFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader(
                    "Access-Control-Allow-Origin", getHostBean().getHost()+getHostBean().getPort());
            chain.doFilter(request, response);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            // ...
        }

        @Override
        public void destroy() {
            // ...
        }
    }


    //////////////////////////////////////////////////////////////////////////////////
    // deep copy of field value to managedContact object
    //////////////////////////////////////////////////////////////////////////////////
    private ManagedContact createCopyPTOUserInfo4ManagedContact(PTOUser ptoUser){

        ManagedContact contact = new ManagedContact();
        /////////////////////////////////////////////////////////////////
        // copy over contact's lawyer's personal info
        /////////////////////////////////////////////////////////////////
        contact.setFirstName(ptoUser.getFirstName());
        contact.setLastName(ptoUser.getLastName());
        contact.setMidlleName(ptoUser.getMidlleName());
        contact.setCountry(ptoUser.getCountry());
        contact.setAddress(ptoUser.getAddress());
        contact.setDisplayName(ptoUser.getFirstName()+ " "+ptoUser.getLastName());
        contact.setContactType("owner");




        contact.setCity(ptoUser.getCity());
        contact.setState(ptoUser.getState());
        contact.setZipcode(ptoUser.getZipcode());
        contact.setPrimaryPhonenumber(ptoUser.getPrimaryPhonenumber());
        contact.setEmail(ptoUser.getEmail());
        //////////////////////////////////////////////////////////////////
        // copy over contact's professional info
        //////////////////////////////////////////////////////////////////

        return contact;
    }



}
