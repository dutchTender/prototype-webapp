package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class ContactsService extends  BaseRESTapiService {

    /////////////////////////////////////////////////////////////////////////////////////////
    // REST methods
    // add contact  param: lawyer-email
    // update contact info  param1: lawyer-field-name  parma2: lawyer-field-value
    /////////////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////////////////////////
    // need to refactor attorney creation using form
    // these REST servies can still be used for auto save on contact update module
    /////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////
    // based on the profile  ...we should be able
    // to inject the correct bean mapped to the correct host file here
    ////////////////////////////////////////////////////////////////////////////////////////


    public ContactsService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/contacts/lawyer/add/{contact_email}")

    @ResponseBody
    ResponseEntity<String> createContact(@PathVariable String contact_email){

        String appFieldReadable = "New Contact";
        ////////////////////////////////////////////////////////////////////////////////////////////////
        // check for valid security session ...as new contacts are added for PTOUser with valid sessions
        ////////////////////////////////////////////////////////////////////////////////////////////////
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if(verifyValidUserSession("xxx") == false){

            String responseMsg = appFieldReadable+" has not been saved";
            return buildResponseEnity("404", responseMsg );

        }
        PTOUserService ptoUserService = getServiceBeanFactory().getPTOUserService();
        // create Lawyer object and add it to PTOUser and save
        PTOUser ptoUser = ptoUserService.findByEmail(email);// ?? we may not need to save this
        // ?? check if contact already exists ???

        Lawyer lawyer = ptoUser.findLawyerContactByEmail(contact_email);
        if(lawyer != null){

            String responseMsg = appFieldReadable+" has not been saved Contact email exists";
            return buildResponseEnity("404", responseMsg );
        }


        Lawyer newLawyer = new Lawyer();
        newLawyer.setClient(ptoUser);
        //newLawyer.setLawFirmName("Grant Thornton, LLC");
        //newLawyer.setPoolMember(trademarkApplication);
        //newLawyer.setBarLicense("DC234567889");
        //newLawyer.setDocketNumber("100000000111");
        //newLawyer.setBarJurisdiction("DC");
        //newLawyer.setFirstName("test");
        //newLawyer.setLastName("lawyer");
        newLawyer.setEmail(contact_email);
        ptoUser.addLawyer(newLawyer);


        ptoUserService.save(ptoUser);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = appFieldReadable+" has been saved";
        return buildResponseEnity("200", responseMsg );
    }


    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/contacts/attorney/update/{contact_email}/{contact_field_name}/{contact_field_value}")
    @ResponseBody
    ResponseEntity<String> updateContact(@PathVariable String contact_email,@PathVariable String contact_field_name, @PathVariable String contact_field_value ){

        String appFieldReadable = "Contact";
        ////////////////////////////////////////////////////////////////////////////////////////////////
        // check for valid security session ...as new contacts are added for PTOUser with valid sessions
        ////////////////////////////////////////////////////////////////////////////////////////////////
        PTOUserService ptoUserService = getServiceBeanFactory().getPTOUserService();
        PTOUser ptoUser = getCurrentPTOuser();
        // verify authentication is valid before moving on ....
        // have to have a valid session

        if(ptoUser == null){ // can probably put this in a function
            String responseMsgs = appFieldReadable+" has not been saved";
            return buildResponseEnity("404", responseMsgs);

        }
        Lawyer lawyer = ptoUser.findLawyerContactByEmail(contact_email);


        if(contact_field_name.equals("attorney-first-name")){
            lawyer.setFirstName(contact_field_value);
            appFieldReadable = "Contact First Name ";

        }
        if(contact_field_name.equals("attorney-last-name")){
            lawyer.setLastName(contact_field_value);
            appFieldReadable = "Contact Last Name ";
        }
        if(contact_field_name.equals("attorney-lawfirm-name" )){
            lawyer.setLawFirmName(contact_field_value);
            appFieldReadable = "Contact Law Firm Name ";

        }

        if(contact_field_name.equals("attorney-country" )){
            lawyer.setCountry(contact_field_value);
            appFieldReadable = "Contact Country  ";

        }
        if(contact_field_name.equals("attorney-address1" )){
            lawyer.setAddress(contact_field_value);
            appFieldReadable = "Contact Street Address  ";

        }

        if(contact_field_name.equals("attorney-city" )){
            lawyer.setCity(contact_field_value);
            appFieldReadable = "Contact City  ";

        }

        if(contact_field_name.equals("attorney-state" )){
            lawyer.setState(contact_field_value);
            appFieldReadable = "Contact State  ";

        }

        if(contact_field_name.equals("attorney-zipcode" )){
            lawyer.setZipcode(contact_field_value);
            appFieldReadable = "Contact Zipcode  ";

        }

        if(contact_field_name.equals("attorney-phone" )){
            lawyer.setPrimaryPhonenumber(contact_field_value);
            appFieldReadable = "Contact Phone number ";

        }

        if(contact_field_name.equals("docket-id" )){
            lawyer.setDocketNumber(contact_field_value);
            appFieldReadable = "Contact Docket number ";

        }

        if(contact_field_name.equals("attorney-bar-standing" )){
            lawyer.setBarJurisdiction(contact_field_value);
            appFieldReadable = "Contact Affiliation ";

        }


        ptoUserService.save(ptoUser);
        LawyerService lawyerService = getServiceBeanFactory().getLawyerService();
        lawyerService.save(lawyer);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = appFieldReadable+" has been saved";
        return buildResponseEnity("200", responseMsg);
    }



}
