package com.thorton.grant.uspto.prototypewebapp.service.REST;


import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.OwnerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class ApplicationService  extends  BaseRESTapiService{


    public ApplicationService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/update/{applicationField}/{param}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationFields(@PathVariable String applicationField , @PathVariable String param, @PathVariable String appInternalID){


        if(verifyValidUserSession("xxx") == false){

            String responseMsg = applicationField+" has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg );

        }

        // retrieve application using passed internal id
        //BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        // page 1 fields
        if(applicationField.equals("set-lawyer-options-value")){
            // ptoUser.setState(param); // sets state code
            if(param.equals("no")){
                baseTrademarkApplication.setAttorneySet(true);
                baseTrademarkApplication.setAttorneyFiling(false);
                baseTrademarkApplication.setLastViewModel("application/attorney/AttorneyStart");
                // drop all attorneys added to attorney pool if any exists
                baseTrademarkApplication.setPrimaryLawyer(null);
                for(Iterator<Lawyer> iter = baseTrademarkApplication.getAvailableLawyers().iterator(); iter.hasNext(); ) {
                    Lawyer current = iter.next();
                    baseTrademarkApplication.removeAvailableLawyer(current);
                    // remove lawyer from the database completely
                    LawyerService lawyerService = getServiceBeanFactory().getLawyerService();
                    lawyerService.delete(current);

                }

                baseTradeMarkApplicationService.save(baseTrademarkApplication);

            }
            else {
                baseTrademarkApplication.setAttorneySet(true);
                baseTrademarkApplication.setAttorneyFiling(true);
                baseTrademarkApplication.setLastViewModel("application/attorney/AttorneyStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);
            }


            appFieldReadable = "Attorney Information";
        }


        // page two fields
        if(applicationField.equals("set-Owner-entity-types")){
            // parse params

            int start = param.indexOf("+");
            String entity_type = param.substring(0,start);
            String sub_type = param.substring(start+1);


            if(entity_type.equals("US")){

                baseTrademarkApplication.setOwnerType(entity_type);
                baseTrademarkApplication.setOwnerSubType(sub_type);
                baseTrademarkApplication.setForeignEnityFiling(false);
                baseTrademarkApplication.setEntityTypeSet(true);

                baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Sub Type";
            }
            else {
                baseTrademarkApplication.setOwnerType("Foreign");
                baseTrademarkApplication.setOwnerSubType("Corporation");
                baseTrademarkApplication.setForeignEnityFiling(true);
                baseTrademarkApplication.setEntityTypeSet(true);

                baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Sub Type";

            }
            // ptoUser.setState(param); // sets state code
        }

        // page two fields
        if(applicationField.equals("set-Owner-entity")){
            // parse params

            if(param.equals("US")){

                if(baseTrademarkApplication.isForeignEnityFiling() == true){

                    OwnerService ownerService = getServiceBeanFactory().getOwnerService();
                    for(Iterator<Owner> iter = baseTrademarkApplication.getOwners().iterator(); iter.hasNext(); ) {
                        Owner current = iter.next();
                        baseTrademarkApplication.removeOwner(current);
                        ownerService.delete(current);

                    }
                }
                baseTrademarkApplication.setForeignEnityFiling(false);
                baseTrademarkApplication.setEntityTypeSet(true);



                baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Type";
            }
            else {
                if(baseTrademarkApplication.isForeignEnityFiling() == false){

                    OwnerService ownerService = getServiceBeanFactory().getOwnerService();
                    for(Iterator<Owner> iter = baseTrademarkApplication.getOwners().iterator(); iter.hasNext(); ) {
                        Owner current = iter.next();
                        baseTrademarkApplication.removeOwner(current);
                        ownerService.delete(current);

                    }
                }
                baseTrademarkApplication.setForeignEnityFiling(true);
                baseTrademarkApplication.setEntityTypeSet(true);

                baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                baseTradeMarkApplicationService.save(baseTrademarkApplication);

                appFieldReadable = "Entity Type";

            }
            // ptoUser.setState(param); // sets state code
        }

        String responseMsg = appFieldReadable+" has been saved";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Owner create + set for eFile Application
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // owner update - PTOUser --> Owner Contacts // this should get moved to ContactServices
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/attorney/add/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationAddContactsPool(@PathVariable String contact_email , @PathVariable String appInternalID){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // verify authentication is valid before moving on ....
        // have to have a valid session

        if(verifyValidUserSession(contact_email) == false){
            String responseMsg = "Contact with email address :"+contact_email+ " has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg);

        }
        PTOUserService ptoUserService = getServiceBeanFactory().getPTOUserService();

        PTOUser ptoUser = ptoUserService.findByEmail(email);//
        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        //////////////////////////////////////////////////////////
        // find contact via email from PTOUser
        // create a copy of the contact object
        // add the copy of the contact to the application object
        // save application object
        ///////////////////////////////////////////////////////////



        Lawyer lawyer = ptoUser.findLawyerContactByEmail(contact_email);
        /////////////////////////////////////////////////////////////////
        // check if contact is already in the pool ...
        // if already in pool, simply do nothing
        /////////////////////////////////////////////////////////////////
        if(lawyer.getPoolMember() != null){
            if(lawyer.getPoolMember().getApplicationInternalID().equals(baseTrademarkApplication.getApplicationInternalID())){
                /////////////////////////////////////////////////////////////////////////
                // check if lawyer already in pool
                // if so, nothing to do here
                // though we should not every reach this block
                // as persistance should prevent user from adding a contact
                // that's already been added. persistance  rendering should have
                // greyed out that contact and also list it in the selected contact list
                /////////////////////////////////////////////////////////////////////////
            }
            else {

                Lawyer application_lawyer = createLawyerCopy(lawyer, ptoUser, baseTrademarkApplication);
                /////////////////////////////////////////////////////////////////
                // copy over contact's lawyer's personal info
                /////////////////////////////////////////////////////////////////
                baseTrademarkApplication.addAvailableLawyer(application_lawyer);


                baseTradeMarkApplicationService.save(baseTrademarkApplication);
                // save application object  and check database and confirm values

            }
           // contact is already added to the application
            /////////////////////////////////////////////////////////////////
            // this should not really happen. as our persistance scheme should
            // show application pool lawyers in the selected contacts widget, and the
            // corresponding contact should have it check mark greyed out
            // the only action available to users should be to remove
            // a contact from the application
            /////////////////////////////////////////////////////////////////
        }
        else {
            // application lawyer pool is currently empty

            Lawyer application_lawyer = createLawyerCopy(lawyer, ptoUser, baseTrademarkApplication);
            /////////////////////////////////////////////////////////////////
            // copy over contact's lawyer's personal info
            /////////////////////////////////////////////////////////////////
            baseTrademarkApplication.addAvailableLawyer(application_lawyer);


            baseTradeMarkApplicationService.save(baseTrademarkApplication);
            // save application object  and check database and confirm values
        }

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Contact with email address :"+contact_email+" has been added to the Application";

        return buildResponseEnity("200", responseMsg);

    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/attorney/delete/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationRemoveContactsPool(@PathVariable String contact_email , @PathVariable String appInternalID){


        if(verifyValidUserSession(contact_email) == false){
            String responseMsg = "Contact with email address :"+contact_email+ " has not been saved";
            return buildResponseEnity("404", responseMsg);

        }
        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        LawyerService lawyerService = getServiceBeanFactory().getLawyerService();
        //////////////////////////////////////////////////////////
        // find contact via email from PTOUser
        // create a copy of the contact object
        // add the copy of the contact to the application object
        // save application object
        ///////////////////////////////////////////////////////////

        Lawyer delLawyer;

        for(Iterator<Lawyer> iter = baseTrademarkApplication.getAvailableLawyers().iterator(); iter.hasNext(); ) {
            //delLawyer = null;
            delLawyer = iter.next();
            if(delLawyer.getEmail().equals(contact_email)){

                if(baseTrademarkApplication.getAvailableLawyers().size() == 1){
                    baseTrademarkApplication.setLastViewModel("application/attorney/AttorneyStart");
                    //baseTrademarkApplication.setAttorneyFiling(false);

                }
                if(delLawyer.isPrimary()){
                        baseTrademarkApplication.setPrimaryLawyer(null);
                        delLawyer.setPrimary(false);
                        baseTrademarkApplication.removeAvailableLawyer(delLawyer);

                }
                else {
                    baseTrademarkApplication.removeAvailableLawyer(delLawyer);
                }
                lawyerService.delete(delLawyer);

            }
        }


        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Contact with email address :"+contact_email+" has been removed from the Application";
        return buildResponseEnity("200", responseMsg);

    }






    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/Attorney/Primary/set/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> setApplicationPrimaryAttorney(@PathVariable String contact_email , @PathVariable String appInternalID){

        if(verifyValidUserSession(contact_email) == false){

            String responseMsg = "Contact with email address :"+contact_email+ "has not been set as Primary Attorney. invalid user session";
            return buildResponseEnity("404", responseMsg);

        }

        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        //////////////////////////////////////////////////////////
        // find contact via email from PTOUser
        // create a copy of the contact object
        // add the copy of the contact to the application object
        // save application object
        ///////////////////////////////////////////////////////////


       Lawyer primaryAttorney = baseTrademarkApplication.findContactByEmail(contact_email);

       if(primaryAttorney != null){
           // unset existing if there is one ...
           Lawyer currentPrimary = baseTrademarkApplication.getPrimaryLawyer();
           if(currentPrimary != null){
               currentPrimary.setPrimary(false);
           }
           baseTrademarkApplication.setPrimaryLawyer(primaryAttorney);
           primaryAttorney.setPrimary(true);
           baseTrademarkApplication.setLastViewModel("application/attorney/AttorneySet2");
           baseTrademarkApplication.setAttorneyCollapseID(primaryAttorney.getFirstName()+primaryAttorney.getLastName());
           baseTradeMarkApplicationService.save(baseTrademarkApplication);


       }
       else{
           // error

           String responseMsg = "Contact with email address :"+contact_email+ " has not been set as Primary Attorney. invalid contact email";
           //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
           return buildResponseEnity("404", responseMsg);
       }

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Contact with email address :"+contact_email+"  have been set as Primary Attorney";
        return buildResponseEnity("200", responseMsg);

    }
    ////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////
    // helper functions
    ////////////////////////////////////////////////



    ////////////////////////////////////////////////
    // create copy of lawyer object
    ////////////////////////////////////////////////
    Lawyer createLawyerCopy(Lawyer lawyer, PTOUser ptoUser, BaseTrademarkApplication baseTrademarkApplication){


        Lawyer application_lawyer = new Lawyer();
        /////////////////////////////////////////////////////////////////
        // copy over contact's lawyer's personal info
        /////////////////////////////////////////////////////////////////
        application_lawyer.setFirstName(lawyer.getFirstName());
        application_lawyer.setLastName(lawyer.getLastName());
        application_lawyer.setCountry(lawyer.getCountry());
        application_lawyer.setAddress(lawyer.getAddress());
        application_lawyer.setCity(lawyer.getCity());
        application_lawyer.setState(lawyer.getState());
        application_lawyer.setZipcode(lawyer.getZipcode());
        application_lawyer.setPrimaryPhonenumber(lawyer.getPrimaryPhonenumber());
        application_lawyer.setEmail(lawyer.getEmail());
        //////////////////////////////////////////////////////////////////
        // copy over contact's professional info
        //////////////////////////////////////////////////////////////////
        application_lawyer.setBarJurisdiction(lawyer.getBarJurisdiction());
        application_lawyer.setBarLicense(lawyer.getBarLicense());
        application_lawyer.setValidBarAssociation(lawyer.isValidBarAssociation());
        application_lawyer.setLawFirmName(lawyer.getLawFirmName());
        ///////////////////////////////////////////////////////////////////
        // copy over contact's owner info  --->sets clientID to ptoUser
        ///////////////////////////////////////////////////////////////////
        application_lawyer.setClient(ptoUser);
        ///////////////////////////////////////////////////////////////////
        // set lawyer's pool ID ---> sets application's internal id as pool id
        ///////////////////////////////////////////////////////////////////
        application_lawyer.setPoolMember(baseTrademarkApplication);
        return application_lawyer;
    }

    ////////////////////////////////////////////////

    ////////////////////////////////////////////////
    // create copy of owner object
    ////////////////////////////////////////////////
    Owner creaateOwnerCopy( Owner owner, PTOUser ptoUser, BaseTrademarkApplication baseTrademarkApplication){

        Owner application_owner = new Owner();
        /////////////////////////////////////////////////////////////////
        // copy over contact's lawyer's personal info
        /////////////////////////////////////////////////////////////////
        application_owner.setFirstName(owner.getFirstName());
        application_owner.setLastName(owner.getLastName());
        application_owner.setMidlleName(owner.getMidlleName());
        application_owner.setCountry(owner.getCountry());
        application_owner.setAddress(owner.getAddress());
        application_owner.setAddress1(owner.getAddress1());
        application_owner.setAddress2(owner.getAddress2());
        application_owner.setAddress3(owner.getAddress3());



        application_owner.setCity(owner.getCity());
        application_owner.setState(owner.getState());
        application_owner.setZipcode(owner.getZipcode());
        application_owner.setPrimaryPhonenumber(owner.getPrimaryPhonenumber());
        application_owner.setEmail(owner.getEmail());
        //////////////////////////////////////////////////////////////////
        // copy over contact's professional info
        //////////////////////////////////////////////////////////////////
        application_owner.setOwnerEnityType(owner.getOwnerEnityType());
        application_owner.setOwnerType(owner.getOwnerType());
        application_owner.setOwnersubType(owner.getOwnersubType());
        application_owner.setWebSiteURL(owner.getWebSiteURL());
        //////////////////////////////////////////////////////////////////
        // copy none individual owner info
        //////////////////////////////////////////////////////////////////


        application_owner.setOwnerName(owner.getOwnerName());
        application_owner.setOwnerDisplayname(owner.getOwnerDisplayname());
        application_owner.setGoverningEntities(owner.getGoverningEntities());
        application_owner.setOwnerOrganizationState(owner.getOwnerOrganizationState());



        ///////////////////////////////////////////////////////////////////
        // copy over contact's owner info  --->sets clientID to ptoUser
        ///////////////////////////////////////////////////////////////////
        application_owner.setClient(ptoUser);
        ///////////////////////////////////////////////////////////////////
        // set lawyer's pool ID ---> sets application's internal id as pool id
        ///////////////////////////////////////////////////////////////////
        application_owner.setTrademarkApplication(baseTrademarkApplication);
        return application_owner;
    }
    ////////////////////////////////////////////////




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/mark/designType/{markType}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> setMarkType(@PathVariable String markType , @PathVariable String appInternalID){

   //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        baseTrademarkApplication.getTradeMark().setTrademarkDesignType(markType);


        //////////////////////////////////////////////////////////
        // find contact via email from PTOUser
        // create a copy of the contact object
        // add the copy of the contact to the application object
        // save application object
        ///////////////////////////////////////////////////////////
        if(markType.equals("Standard Character")){
            baseTrademarkApplication.setStandardTextMark(true);
            baseTrademarkApplication.getTradeMark().setStandardCharacterMark(true);
        }
        else {
            baseTrademarkApplication.setStandardTextMark(false);
            baseTrademarkApplication.getTradeMark().setStandardCharacterMark(false);
        }



        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Mark Type:"+markType+" has been set for the Application";
        return buildResponseEnity("200", responseMsg);

    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/contacts/owner/delete/{contact_email}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationRemoveOwner(@PathVariable String contact_email , @PathVariable String appInternalID){


        if(verifyValidUserSession(contact_email) == false){
            String responseMsg = "Contact with email address :"+contact_email+ " has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg);

        }
        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        OwnerService ownerService = getServiceBeanFactory().getOwnerService();
        //////////////////////////////////////////////////////////
        // find contact via email from PTOUser
        // create a copy of the contact object
        // add the copy of the contact to the application object
        // save application object
        ///////////////////////////////////////////////////////////

        Owner delOwner;

        for(Iterator<Owner> iter = baseTrademarkApplication.getOwners().iterator(); iter.hasNext(); ) {
            //delLawyer = null;
            delOwner = iter.next();
            if(delOwner.getEmail().equals(contact_email)){

                if(baseTrademarkApplication.getOwners().size() == 1){
                    baseTrademarkApplication.setLastViewModel("application/owner/OwnerStart");
                    //baseTrademarkApplication.setAttorneyFiling(false);

                }

                baseTrademarkApplication.removeOwner(delOwner);

                ownerService.delete(delOwner);

            }
        }


        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Contact with email address :"+contact_email+" has been removed from the Application";
        return buildResponseEnity("200", responseMsg);

    }




    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/signDirect/{signature}/{date}/{dateDisplay}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> saveApplicatoinSignature(@PathVariable String signature ,@PathVariable String date, @PathVariable String dateDisplay, @PathVariable String appInternalID){

        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);


        baseTrademarkApplication.setApplicationSignature("/"+signature+"/");
        Long dateInMilli = Long.valueOf(date);

        //DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date result = new Date(dateInMilli);
        baseTrademarkApplication.setApplicationDateSigned(result);
        baseTrademarkApplication.setApplicationDateSignedDisplay(dateDisplay);




        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = "Direct Sign Signature has been saved for the Application";
        return buildResponseEnity("200", responseMsg);

    }


    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/signDeclare/{fieldName}/{fieldValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> saveApplicatoinSignDeclareFields(@PathVariable String fieldName ,@PathVariable String fieldValue,  @PathVariable String appInternalID){

        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        String appFieldReadable = "";
        if(fieldName.equals("app-sign-name")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.setAppSignatoryName(fieldValue);
            appFieldReadable = "Application Signatory Name";

        }

        if(fieldName.equals("app-sign-pos")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.setAppSignatoryPosition(fieldValue);
            appFieldReadable = "Application Signatory Position";

        }

        if(fieldName.equals("app-sign-phone")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.setAppSignatoryPhone(fieldValue);
            appFieldReadable = "Application Signatory Phone number";

        }


        if(fieldName.equals("app-sign-type")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.setSignatureType(fieldValue);
            if(fieldValue.equals("direct")){
                baseTrademarkApplication.setSignDirect(true);

            }
            appFieldReadable = "Application Signature type";

        }

        if(fieldName.equals("declare-all")){

            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setDeclarationApplicantIsOwner(true);

                baseTrademarkApplication.setDeclarationMarkInUse(true);
                baseTrademarkApplication.setDeclarationSpecimen(true);
                baseTrademarkApplication.setDeclarationConcurrentUser(true);
                baseTrademarkApplication.setDeclarationEvidenceSupport(true);
                baseTrademarkApplication.setDeclarationWarningFalseStatement(true);
                baseTrademarkApplication.setDeclarationNoOtherHasRight(true);
                baseTrademarkApplication.setDeclarationAll(true);

                baseTrademarkApplication.setDeclarationApplicantIsOwnerSet(true);
                baseTrademarkApplication.setDeclarationMarkInUseSet(true);
                baseTrademarkApplication.setDeclarationSpecimenSet(true);
                baseTrademarkApplication.setDeclarationConcurrentUserSet(true);
                baseTrademarkApplication.setDeclarationEvidenceSupportSet(true);
                baseTrademarkApplication.setDeclarationWarningFalseStatementSet(true);
                baseTrademarkApplication.setDeclarationNoOtherHasRightSet(true);
            }
            else {
                baseTrademarkApplication.setDeclarationApplicantIsOwner(false);
                baseTrademarkApplication.setDeclarationMarkInUse(false);
                baseTrademarkApplication.setDeclarationSpecimen(false);
                baseTrademarkApplication.setDeclarationConcurrentUser(false);
                baseTrademarkApplication.setDeclarationEvidenceSupport(false);
                baseTrademarkApplication.setDeclarationWarningFalseStatement(false);
                baseTrademarkApplication.setDeclarationNoOtherHasRight(false);
                baseTrademarkApplication.setDeclarationAll(false);


                baseTrademarkApplication.setDeclarationApplicantIsOwnerSet(false);
                baseTrademarkApplication.setDeclarationMarkInUseSet(false);
                baseTrademarkApplication.setDeclarationSpecimenSet(false);
                baseTrademarkApplication.setDeclarationConcurrentUserSet(false);
                baseTrademarkApplication.setDeclarationEvidenceSupportSet(false);
                baseTrademarkApplication.setDeclarationWarningFalseStatementSet(false);
                baseTrademarkApplication.setDeclarationNoOtherHasRightSet(false);

            }



            appFieldReadable = "Application Declaration all";
        }

        if(fieldName.equals("declare-app-owner")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){
               baseTrademarkApplication.setDeclarationApplicantIsOwner(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setDeclarationApplicantIsOwner(false);

            }
            baseTrademarkApplication.setDeclarationApplicantIsOwnerSet(true);
            appFieldReadable = "Application Declaration";

        }


        if(fieldName.equals("declare-mark-inUse")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setDeclarationMarkInUse(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setDeclarationMarkInUse(false);

            }
            baseTrademarkApplication.setDeclarationMarkInUseSet(true);

            appFieldReadable = "Application Declaration";

        }

        if(fieldName.equals("declare-mark-inUse-spec")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setDeclarationSpecimen(true);



            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setDeclarationSpecimen(false);


            }



            baseTrademarkApplication.setDeclarationSpecimenSet(true);
            appFieldReadable = "Application Declaration";

        }

        if(fieldName.equals("declare-concurrent-user")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setDeclarationConcurrentUser(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setDeclarationConcurrentUser(false);

            }

            baseTrademarkApplication.setDeclarationConcurrentUserSet(true);
            appFieldReadable = "Application Declaration";

        }

        if(fieldName.equals("declare-evidence-support")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setDeclarationEvidenceSupport(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setDeclarationEvidenceSupport(false);

            }

            appFieldReadable = "Application Declaration";

            baseTrademarkApplication.setDeclarationEvidenceSupportSet(true);
        }

        if(fieldName.equals("declare-warning-false")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setDeclarationWarningFalseStatement(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setDeclarationWarningFalseStatement(false);

            }

            baseTrademarkApplication.setDeclarationWarningFalseStatementSet(true);
            appFieldReadable = "Application Declaration";

        }


        if(fieldName.equals("declare-noOther-has-right")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setDeclarationNoOtherHasRight(true);


            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setDeclarationNoOtherHasRight(false);
            }
            baseTrademarkApplication.setDeclarationNoOtherHasRightSet(true);
            appFieldReadable = "Application Declaration";

        }


        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        String responseMsg = appFieldReadable+" has been saved";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }

    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/application/TEASOpt/{fieldValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> saveApplicatoinTEASOpt(@PathVariable String fieldValue , @PathVariable String appInternalID){

        //////////////////////////////////////////////////////////
        // retrieve application using passed internal id
        //////////////////////////////////////////////////////////
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String responseMsg = "";

        if(fieldValue.equals("yes")){
            baseTrademarkApplication.setValidateTEASFields(true);
            responseMsg = "Reduced fee validation has been set to TRUE for the current application.";
        }
        else {
            baseTrademarkApplication.setValidateTEASFields(false);
            responseMsg = "Reduced fee level validation has been set to FALSE for the current application.";
        }




        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        ////////////////////////////////////////////////
        // start generating response
        ////////////////////////////////////////////////

        return buildResponseEnity("200", responseMsg);

    }


}
