package com.thorton.grant.uspto.prototypewebapp.controllers;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.asset.GoodsAndServicesService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.ContactsDisplayDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.form.NewAttorneyContactFormDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.form.NewOwnerContactFormDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.form.partnerDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.GoverningEntity;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GSClassCategory;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.service.REST.Goods_ServicesService;
import com.thorton.grant.uspto.prototypewebapp.service.storage.StorageService;
import com.thorton.grant.uspto.prototypewebapp.service.storage.error.StorageException;
import org.springframework.context.ApplicationContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;


@Controller
public class ApplicationObjectCreationController {


    private final ServiceBeanFactory serviceBeanFactory;
    private static long counter = 3000000;

    private final StorageService storageService;



    /////////////////////////////////////////////////////////////////////////////////////////
    // based on the profile  ...we should be able
    // to inject the correct bean mapped to the correct host file here
    ////////////////////////////////////////////////////////////////////////////////////////
    private final HostBean hostBean;


    private final ApplicationContext appContext;

    public ApplicationObjectCreationController(ServiceBeanFactory serviceBeanFactory, ApplicationContext appContext, StorageService storageService) {
        this.serviceBeanFactory = serviceBeanFactory;
        this.appContext = appContext;

        this.hostBean = (HostBean) appContext.getBean(HostBean.class);
        this.storageService = storageService;

    }




    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @PostMapping(value = "/attorney/add")
    public String addAttorneyContact( @ModelAttribute("addNewAttorneyContactFormDTO") @Valid NewAttorneyContactFormDTO newAttorneyContactFormDTO,
                                      @RequestParam(name="file", required=false) MultipartFile file,
                                      Model model,
                                      WebRequest request,
                                      BindingResult result,
                                      Errors errors) {


        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println("app internal id : "+newAttorneyContactFormDTO.getAppInternalID());
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
        String tempAppId = newAttorneyContactFormDTO.getAppInternalID();
        if(tempAppId.contains(",")){
            int index = tempAppId.indexOf(",");
            tempAppId = tempAppId.substring(0, index);
            newAttorneyContactFormDTO.setAppInternalID(tempAppId);
        }

        System.out.println("22222222222222222222222222222222222222222222222222222222222222222222222222222222");
        System.out.println("app internal id : "+newAttorneyContactFormDTO.getAppInternalID());
        System.out.println("222222222222222222222222222222222222222222222222222222222222222222222222222222222");


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(newAttorneyContactFormDTO.getAppInternalID());


        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);
        String trademarkInternalID = baseTrademarkApplication.getApplicationInternalID();

        ////////////////////////////////////////////////////////////////////////
        //add new attorney contact business logic
        ////////////////////////////////////////////////////////////////////////
        // create lawyer ...and set it to PTO user ..then save PTO user object and add it to model
        // set all fields from DTO
        ////////////////////////////////////////////////////////////////////////
        Lawyer lawyer = new Lawyer();


        if(newAttorneyContactFormDTO.getFirstName()!= null){


            lawyer.setFirstName(newAttorneyContactFormDTO.getFirstName());
            lawyer.setCollapseID((newAttorneyContactFormDTO.getFirstName()+newAttorneyContactFormDTO.getLastName()).replaceAll("[^A-Za-z0-9]", ""));
        }

        lawyer.setLastName(newAttorneyContactFormDTO.getLastName());
        if(newAttorneyContactFormDTO.getMiddleName() != null){
            lawyer.setMidlleName(newAttorneyContactFormDTO.getMiddleName());
        }
        if(newAttorneyContactFormDTO.getSuffix() != null){
            lawyer.setSuffix(newAttorneyContactFormDTO.getSuffix());
        }
        lawyer.setLawFirmName(newAttorneyContactFormDTO.getLawFirmName());

        if(newAttorneyContactFormDTO.getAttorneyAddressCountry() != null){
            lawyer.setCountry(newAttorneyContactFormDTO.getAttorneyAddressCountry());
        }

        if(newAttorneyContactFormDTO.getAttorneyAddress1() != null){
            lawyer.setAddress1(newAttorneyContactFormDTO.getAttorneyAddress1());
            lawyer.setAddress(newAttorneyContactFormDTO.getAttorneyAddress1());
        }

        if(newAttorneyContactFormDTO.getAttorneyAddress2() != null){
            lawyer.setAddress2(newAttorneyContactFormDTO.getAttorneyAddress2());
        }

        if(newAttorneyContactFormDTO.getAttorneyAddress3() != null){
            lawyer.setAddress3(newAttorneyContactFormDTO.getAttorneyAddress3());
        }
        if(newAttorneyContactFormDTO.getAttorneyCity() != null){
            lawyer.setCity(newAttorneyContactFormDTO.getAttorneyCity());
        }

        if(newAttorneyContactFormDTO.getAttorneyState() != null){
            lawyer.setState(newAttorneyContactFormDTO.getAttorneyState());
        }
        if(newAttorneyContactFormDTO.getAttorneyZipcode() != null){
            lawyer.setZipcode(newAttorneyContactFormDTO.getAttorneyZipcode());
        }
        if(newAttorneyContactFormDTO.getAttorneyEmail()!= null){
            if(baseTrademarkApplication.findContactByEmail(newAttorneyContactFormDTO.getAttorneyEmail()) != null){
                model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);
                ArrayList<String> contactNames = new ArrayList<>();
                ArrayList<String> contactEmails = new ArrayList<>();
                ArrayList<String> contactSubTypes = new ArrayList<>();
                ManagedContact managedContact = null;

                for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
                    managedContact = iter.next();
                    contactNames.add(managedContact.getDisplayName());
                    contactEmails.add(managedContact.getEmail());
                    contactSubTypes.add(managedContact.getContactType());

                }
                Collections.reverse(contactNames);
                Collections.reverse(contactEmails);
                Collections.reverse(contactSubTypes);
                ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
                contactsDisplayDTO.setContactNames(contactNames);
                contactsDisplayDTO.setContactEmails(contactEmails);
                contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
                model.addAttribute("myManagedContacts", contactsDisplayDTO);

                // also add error message
                model.addAttribute("message", "ERROR: Attorney email exists for this Application.");

                return "application/attorney/AttorneyStart";
                // return to  ownerStartPage with error message
            }

            lawyer.setEmail(newAttorneyContactFormDTO.getAttorneyEmail());

        }
        //////////////////////////////////////////////////////////////////////////////////////
        // add attorney bar information here ...
        //////////////////////////////////////////////////////////////////////////////////////

        if(file != null){

              if(file.isEmpty() == false) {
                  lawyer.setBarCertificateImageKey("/files/"+storageService.getCounter()+file.getOriginalFilename());
                  try {
                      storageService.store(file);

                  }
                  catch ( StorageException ex){
                      model.addAttribute("message", "ERROR: Attorney Credentials upload failed due to error: "+ex );
                      return "forward:/application/start/?trademarkID="+trademarkInternalID;

                  }
              }

        }

        if(newAttorneyContactFormDTO.getAttorneyPhone() != null){
            lawyer.setPrimaryPhonenumber(newAttorneyContactFormDTO.getAttorneyPhone());
        }
        if(newAttorneyContactFormDTO.getAttorneyDocketNumber()!= null){
            lawyer.setDocketNumber(newAttorneyContactFormDTO.getAttorneyDocketNumber());
        }
        if(newAttorneyContactFormDTO.getAttorneyAffiliation()!= null){
            lawyer.setAffiliationStatus(newAttorneyContactFormDTO.getAttorneyAffiliation());
        }
        if(newAttorneyContactFormDTO.getAttorneyBarJurisdiction()!= null){
            lawyer.setBarJurisdiction(newAttorneyContactFormDTO.getAttorneyBarJurisdiction());
        }

        if(newAttorneyContactFormDTO.getAttorneyBarMembershipNumber()!= null){
            lawyer.setMembershipNumber(newAttorneyContactFormDTO.getAttorneyBarMembershipNumber());
        }

        if(newAttorneyContactFormDTO.getAttorneyBarAdmissionDate()!= null){

            String string = newAttorneyContactFormDTO.getAttorneyBarAdmissionDate();

            System.out.println("Date value : "+string);
            if(string.length() < 10){
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                    Date date = format.parse(string);

                    lawyer.setBarAdmissionDate(date);
                }
                catch(Exception ex){

                    model.addAttribute("message", "ERROR: Could not save Bar Admission Date, invalid Date format.");
                }
            }

        }

        if(newAttorneyContactFormDTO.getAttorneyCAagentName()!= null){
            lawyer.setCanadianAgentName(newAttorneyContactFormDTO.getAttorneyCAagentName());
        }
        if(newAttorneyContactFormDTO.applicantCA != null){
            if(newAttorneyContactFormDTO.getApplicantCA() == "true") {
                lawyer.setApplicantCA(true);
            }
            else {
                lawyer.setApplicantCA(false);
            }
        }



        // check if managed contact should be added to PTOUser
        if(ptoUser.findManagedContactByDisplayName(lawyer.getFirstName()+" "+lawyer.getLastName()) == null && ptoUser.findManagedContactByEmail(lawyer.getEmail()) == null){
            ManagedContact newContact = createCopyAttorneyInfo4ManagedContact(lawyer);
            ptoUser.addManagedContact(newContact);
            ptoUserService.save(ptoUser);

        }
        System.out.println("lawyer pool size : "+baseTrademarkApplication.getAvailableLawyers().size());

        if(baseTrademarkApplication.getAvailableLawyers().size() == 0){
            lawyer.setPrimary(true);
            baseTrademarkApplication.setPrimaryLawyer(lawyer);
        }


        baseTrademarkApplication.addAvailableLawyer(lawyer);

        // check if attorney should be set as primary




        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        return "forward:/application/AttorneySet/?trademarkID="+trademarkInternalID;
    }
    ///////////////////////////////////////////////////////////////////////////////
     // end of attorney add
    ///////////////////////////////////////////////////////////////////////////////



    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @RequestMapping(value = "/owner/add", method = RequestMethod.POST)
    public String addOwnerContact( Model model,
                                   @ModelAttribute("addNewOwnerContactFormDTO") @Valid NewOwnerContactFormDTO newOwnerContactFormDTO,
                                   WebRequest request,
                                   BindingResult result,
                                   Errors errors) {


        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println("app internal id : "+newOwnerContactFormDTO.getAppInternalID());
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
        String tempAppId = newOwnerContactFormDTO.getAppInternalID();
        if(tempAppId.contains(",")){
            int index = tempAppId.indexOf(",");
            tempAppId = tempAppId.substring(0, index);
            newOwnerContactFormDTO.setAppInternalID(tempAppId);
        }

        System.out.println("22222222222222222222222222222222222222222222222222222222222222222222222222222222");
        System.out.println("app internal id : "+newOwnerContactFormDTO.getAppInternalID());
        System.out.println("222222222222222222222222222222222222222222222222222222222222222222222222222222222");


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(newOwnerContactFormDTO.getAppInternalID());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        model.addAttribute("hostBean", hostBean);

        System.out.println("app id value : "+newOwnerContactFormDTO.getAppInternalID());
        ////////////////////////////////////////////////////////////////////////
        //add new owner contact business logic
        ////////////////////////////////////////////////////////////////////////
        // create owner ...and set it to PTO user ..then save PTO user object and add it to model
        Owner owner = new Owner();
        owner.setOwnerEnityType(baseTrademarkApplication.getOwnerType());
        owner.setOwnersubType(baseTrademarkApplication.getOwnerSubType());
        baseTrademarkApplication.setOwnerSubType(null);
        baseTrademarkApplication.setOwnerType(null);

        System.out.println("OWNER SUB TYPE : "+owner.getOwnersubType());
        // transfer and reset owner type and subtype

        if(newOwnerContactFormDTO.getFirstName()!= null){



            owner.setOwnerDisplayname(newOwnerContactFormDTO.getFirstName()+ " "+newOwnerContactFormDTO.getLastName());
            owner.setFirstName(newOwnerContactFormDTO.getFirstName());



        }
        if(newOwnerContactFormDTO.getLastName()!= null) {
            owner.setLastName(newOwnerContactFormDTO.getLastName());
        }
        if(newOwnerContactFormDTO.getMiddleName() != null){
            owner.setMidlleName(newOwnerContactFormDTO.getMiddleName());
        }
        if(newOwnerContactFormDTO.getSuffix() != null){
            owner.setSuffix(newOwnerContactFormDTO.getSuffix());
        }
        if(newOwnerContactFormDTO.getOwnerType() != null){
            owner.setOwnerType(newOwnerContactFormDTO.getOwnerType());
        }
        if(newOwnerContactFormDTO.getOwnerCitizenShip()!= null){
            owner.setCitizenShip(newOwnerContactFormDTO.getOwnerCitizenShip());
        }
        owner.setCountry(newOwnerContactFormDTO.getOwnerAddressCountry());
        owner.setAddress1(newOwnerContactFormDTO.getOwnerAddress1());
        owner.setAddress(newOwnerContactFormDTO.getOwnerAddress1());
        // set both address and address1
        if(newOwnerContactFormDTO.getOwnerAddress2() != null){
            owner.setAddress2(newOwnerContactFormDTO.getOwnerAddress2());
        }
        if(newOwnerContactFormDTO.getOwnerAddress3() != null){
            owner.setAddress3(newOwnerContactFormDTO.getOwnerAddress3());
        }

        owner.setCity(newOwnerContactFormDTO.getOwnerCity());

        if(newOwnerContactFormDTO.getOwnerState() != null){
            owner.setState(newOwnerContactFormDTO.getOwnerState());
        }
        if(newOwnerContactFormDTO.getOwnerZipcode() != null){
            owner.setZipcode(newOwnerContactFormDTO.getOwnerZipcode());
        }
        if(newOwnerContactFormDTO.getOwnerEmail() != null){
            if(baseTrademarkApplication.findOwnerByEmail(newOwnerContactFormDTO.getOwnerEmail()) != null){
                model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);
                ArrayList<String> contactNames = new ArrayList<>();
                ArrayList<String> contactEmails = new ArrayList<>();
                ArrayList<String> contactSubTypes = new ArrayList<>();
                ManagedContact managedContact = null;

                for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
                    managedContact = iter.next();
                    contactNames.add(managedContact.getDisplayName());
                    contactEmails.add(managedContact.getEmail());
                    contactSubTypes.add(managedContact.getContactType());

                }
                Collections.reverse(contactNames);
                Collections.reverse(contactEmails);
                Collections.reverse(contactSubTypes);
                ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
                contactsDisplayDTO.setContactNames(contactNames);
                contactsDisplayDTO.setContactEmails(contactEmails);
                contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
                model.addAttribute("myManagedContacts", contactsDisplayDTO);

                // also add error message
                model.addAttribute("message", "ERROR: Owner Email exists for this Application.");

                return "application/owner/OwnerStart";
                // return to  ownerStartPage with error message
            }

            owner.setEmail(newOwnerContactFormDTO.getOwnerEmail());
        }
        if(newOwnerContactFormDTO.getOwnerWebSite() != null){
            owner.setWebSiteURL(newOwnerContactFormDTO.getOwnerWebSite());
        }
        if(newOwnerContactFormDTO.getOwnerPhone() != null){
            owner.setPrimaryPhonenumber(newOwnerContactFormDTO.getOwnerPhone());
        }

        // set new fields that are specific to sole Proprietorship
        // should be 3 fields
        // check for null then add
        if(newOwnerContactFormDTO.getOwnerName() != null){


            if(baseTrademarkApplication.findOwnerByDisplayName(newOwnerContactFormDTO.getOwnerName()) != null){
                model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);
                ArrayList<String> contactNames = new ArrayList<>();
                ArrayList<String> contactEmails = new ArrayList<>();
                ArrayList<String> contactSubTypes = new ArrayList<>();
                ManagedContact managedContact = null;

                for(Iterator<ManagedContact> iter = ptoUser.getMyManagedContacts().iterator(); iter.hasNext(); ) {
                    managedContact = iter.next();
                    contactNames.add(managedContact.getDisplayName());
                    contactEmails.add(managedContact.getEmail());
                    contactSubTypes.add(managedContact.getContactType());

                }
                Collections.reverse(contactNames);
                Collections.reverse(contactEmails);
                Collections.reverse(contactSubTypes);
                ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
                contactsDisplayDTO.setContactNames(contactNames);
                contactsDisplayDTO.setContactEmails(contactEmails);
                contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
                model.addAttribute("myManagedContacts", contactsDisplayDTO);

                // also add error message
                model.addAttribute("message", "ERROR: Owner Name exists for this Application.");

                return "application/owner/OwnerStart";
                // return to  ownerStartPage with error message
            }


            owner.setOwnerName(newOwnerContactFormDTO.getOwnerName());
            owner.setOwnerDisplayname(newOwnerContactFormDTO.getOwnerName());
        }

        if(newOwnerContactFormDTO.getOwnerAdditionalName() !=  null){
            owner.setOwnerAdditionalName(newOwnerContactFormDTO.getOwnerAdditionalName());
        }
        if(newOwnerContactFormDTO.getOwnerOrganizationState() != null){
            owner.setOwnerOrganizationState(newOwnerContactFormDTO.getOwnerOrganizationState());
        }


        // crate partner if partner fields are not null
        List<partnerDTO> partnerDTOS = newOwnerContactFormDTO.getPartnerDTOs();
        if(partnerDTOS.size() > 0){

            //for(Iterator<partnerDTO> iter = partnerDTOS.iterator(); iter.hasNext(); ) {
            //partnerDTO partner = partnerDTOS.iterator().next();
            partnerDTO partner = partnerDTOS.get(0);

            GoverningEntity governingEntity = new GoverningEntity();

            if(partner.getPartnerName() != ""){
                governingEntity.setEntityName(partner.getPartnerName());
                governingEntity.setDisplayName(partner.getPartnerName());
                governingEntity.setCollapseID(partner.getPartnerName().replaceAll("[^A-Za-z0-9]", ""));
            }
            if(partner.getFirstName() != "" ){
                governingEntity.setFirstName(partner.getFirstName());
                governingEntity.setDisplayName(partner.getFirstName()+" "+partner.getLastName());
                governingEntity.setCollapseID((partner.getFirstName()+partner.getLastName()).replaceAll("[^A-Za-z0-9]", ""));
            }
            if(partner.getLastName() != ""){
                governingEntity.setLastName(partner.getLastName());
            }
            if(partner.getMiddleName() != ""){
                governingEntity.setMiddleName(partner.getMiddleName());
            }
            if(partner.getSuffix() != ""){
                governingEntity.setSuffix(partner.getSuffix());
            }
            if(partner.getCitizenShip() != ""){
                governingEntity.setEntityCitizenship(partner.getCitizenShip());
            }

            if(partner.getState() != ""){
                governingEntity.setOrganizationState(partner.getState());
            }
            if(partner.getType() != ""){
                governingEntity.setEntityType(partner.getType());
            }

            owner.addGoverningEnity(governingEntity);
            // }

        }


        if(baseTrademarkApplication.getOwners().size() == 0){
            //owner.setPrimaryApplication(baseTrademarkApplication);
            baseTrademarkApplication.setPrimaryOwner(owner);
        }
        baseTrademarkApplication.addOwner(owner);
        owner.setTrademarkApplication(baseTrademarkApplication);

        //owner.setClient(p);
        // ptoUser.addOwner(owner);
        //ptoUserService.save(ptoUser);
        baseTradeMarkApplicationService.save(baseTrademarkApplication);



        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);



        /////////////////////////////////////////////
        // load my contacts list for thyemleaf
        /////////////////////////////////////////////
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<String> contactEmails = new ArrayList<>();
        ArrayList<String> contactSubTypes = new ArrayList<>();
        Owner owner1 = null;

        for(Iterator<Owner> iter = ptoUser.getMyOwners().iterator(); iter.hasNext(); ) {
            owner1 = iter.next();
            contactNames.add(owner.getOwnerDisplayname());
            contactEmails.add(owner1.getEmail());
            contactSubTypes.add(owner1.getOwnersubType());

        }
        Collections.reverse(contactNames);
        Collections.reverse(contactEmails);
        Collections.reverse(contactSubTypes);
        ContactsDisplayDTO contactsDisplayDTO = new ContactsDisplayDTO();
        contactsDisplayDTO.setContactNames(contactNames);
        contactsDisplayDTO.setContactEmails(contactEmails);
        contactsDisplayDTO.setContactEntitySubType(contactSubTypes);
        model.addAttribute("myOwnerContacts", contactsDisplayDTO);


        // check if managed contact should be added to PTOUser
        if(ptoUser.findManagedContactByDisplayName(owner.getOwnerDisplayname()) == null && ptoUser.findManagedContactByEmail(owner.getEmail()) == null){
            ManagedContact newContact = createCopyOwnerInfo4ManagedContact(owner);
            ptoUser.addManagedContact(newContact);
            ptoUserService.save(ptoUser);

        }



        return "forward:/application/OwnerSetView/?trademarkID="+newOwnerContactFormDTO.getAppInternalID();
        //return "application/OwnerSetView";
    }
    ///////////////////////////////////////////////////////////////////////////////
    // end of owner add
    ///////////////////////////////////////////////////////////////////////////////








    //////////////////////////////////////////////////////////////////////////////////
    // deep copy of field value to managedContact object
    //////////////////////////////////////////////////////////////////////////////////
    private ManagedContact createCopyAttorneyInfo4ManagedContact(Lawyer ptoUser){

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
        contact.setContactType("attorney");
        contact.setLawFirmName(ptoUser.getLawFirmName());
        contact.setDocketNumber(ptoUser.getDocketNumber());
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

    //////////////////////////////////////////////////////////////////////////////////
    // deep copy of field value to managedContact object
    //////////////////////////////////////////////////////////////////////////////////
    private ManagedContact createCopyOwnerInfo4ManagedContact(Owner ptoUser){

        ManagedContact contact = new ManagedContact();
        /////////////////////////////////////////////////////////////////
        // copy over contact's lawyer's personal info
        /////////////////////////////////////////////////////////////////
        contact.setFirstName(ptoUser.getFirstName());
        contact.setLastName(ptoUser.getLastName());
        contact.setMidlleName(ptoUser.getMidlleName());
        contact.setCountry(ptoUser.getCountry());
        contact.setAddress(ptoUser.getAddress());
        contact.setOwnerEntityName(ptoUser.getOwnerName());
        if(contact.getFirstName() != null) {
            contact.setDisplayName(ptoUser.getFirstName() + " " + ptoUser.getLastName());
            contact.setContactType("owner");
        }
        else{
            contact.setDisplayName(ptoUser.getOwnerName());
            contact.setContactType("owner");
        }

        contact.setCity(ptoUser.getCity());
        contact.setState(ptoUser.getState());
        contact.setZipcode(ptoUser.getZipcode());
        contact.setPrimaryPhonenumber(ptoUser.getPrimaryPhonenumber());
        contact.setEmail(ptoUser.getEmail());
        //////////////////////////////////////////////////////////////////
        // copy over contact's professional info
        //////////////////////////////////////////////////////////////////
        contact.setOwnerEntityName(ptoUser.getOwnerName());
        contact.setOwnerEntityType(ptoUser.getOwnerType());
        contact.setOwnerStateOfFormation(ptoUser.getOwnerOrganizationState());
        contact.setOwnerCountryOfCitizenship(ptoUser.getCitizenShip());


        return contact;
    }




    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @PostMapping(value = "/Mark/add")
    public String addMarkDetailsImageFile(
                                      @RequestParam(name="file", required=false) MultipartFile file,
                                      @RequestParam String AppInternalID,
                                      Model model) {



        System.out.println("1111111111111111111111111111111111111111111111122222222222222222222222222222222222222222");
        System.out.println("mark upload controller !!!!!!!!!!!");
        System.out.println("1111111111111111111111111111111111111111111111122222222222222222222222222222222222222222");
        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println("app internal id : "+AppInternalID);
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");

        if(AppInternalID.contains(",")){
            int index = AppInternalID.indexOf(",");
            AppInternalID = AppInternalID.substring(0, index);
        }

        System.out.println("22222222222222222222222222222222222222222222222222222222222222222222222222222222");
        System.out.println("app internal id : "+AppInternalID);
        System.out.println("222222222222222222222222222222222222222222222222222222222222222222222222222222222");


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID( AppInternalID);

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);
        String trademarkInternalID = baseTrademarkApplication.getApplicationInternalID();

        //TradeMark tradeMark = new TradeMark();
        if(file != null){

            if(file.isEmpty() == false) {


                try {
                    String image_path = storageService.store(file);

                    baseTrademarkApplication.getTradeMark().setTrademarkImagePath("/files/"+image_path);
                    model.addAttribute("markImagePath",baseTrademarkApplication.getTradeMark().getTrademarkImagePath());
                }
                catch ( StorageException ex){
                    model.addAttribute("message", "ERROR: Mark Image upload failed due to error: "+ex );
                   // return "forward:/mark/designWithText/?trademarkID="+trademarkInternalID;
                    return "application/mark/MarkDetailsUpload";

                }

                // generate black and white version and store path

                try {
                    String BWimagePath = storageService.storeBW(file);

                    baseTrademarkApplication.getTradeMark().setTrademarkBWImagePath("/files/"+BWimagePath);
                    model.addAttribute("markImagePathBW",baseTrademarkApplication.getTradeMark().getTrademarkBWImagePath());

                }
                catch ( StorageException ex){
                    model.addAttribute("message", "ERROR: BW Mark Image upload failed due to error: "+ex );
                    // return "forward:/mark/designWithText/?trademarkID="+trademarkInternalID;
                    return "application/mark/MarkDetailsUpload";

                }
                baseTrademarkApplication.setTradeMarkUploaded(true);
                baseTradeMarkApplicationService.save(baseTrademarkApplication);


            }

        }
        model.addAttribute("breadCrumbStatus",baseTrademarkApplication.getSectionStatus());
        //return "application/MarkDetailsDesignWText";
        return "application/mark/MarkDetailsUpload";
    }
    ///////////////////////////////////////////////////////////////////////////////
    // end of attorney add
    ///////////////////////////////////////////////////////////////////////////////




    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @PostMapping(value = "/Mark/consent/add")
    public String uploadMarkConset(
            @RequestParam(name="file", required=false) MultipartFile file,
            @RequestParam String AppInternalID,
            Model model) {



        System.out.println("1111111111111111111111111111111111111111111111122222222222222222222222222222222222222222");
        System.out.println("mark consent file upload controller !!!!!!!!!!!");
        System.out.println("1111111111111111111111111111111111111111111111122222222222222222222222222222222222222222");
        // create a new application and tie it to user then save it to repository
        // create attorneyDTO + to model
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println("app internal id : "+AppInternalID);
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111");

        if(AppInternalID.contains(",")){
            int index = AppInternalID.indexOf(",");
            AppInternalID = AppInternalID.substring(0, index);
        }

        System.out.println("22222222222222222222222222222222222222222222222222222222222222222222222222222222");
        System.out.println("app internal id : "+AppInternalID);
        System.out.println("222222222222222222222222222222222222222222222222222222222222222222222222222222222");


        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID( AppInternalID);

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        model.addAttribute("hostBean", hostBean);
        String trademarkInternalID = baseTrademarkApplication.getApplicationInternalID();

        //TradeMark tradeMark = new TradeMark();
        if(file != null){

            if(file.isEmpty() == false) {


                try {
                    String file_path = storageService.store(file);

                    baseTrademarkApplication.getTradeMark().setTrademarkConsentFilePath("/files/"+file_path);
                    baseTrademarkApplication.getTradeMark().setConsentFileUploaded(true);
                    boolean colorClaim = baseTrademarkApplication.getTradeMark().isMarkColorClaim();
                    boolean acceptBW = baseTrademarkApplication.getTradeMark().isMarkColorClaimBW();
                    boolean translationFW = baseTrademarkApplication.getTradeMark().isForeignLanguageTranslationWording();

                    boolean transliterationFW = baseTrademarkApplication.getTradeMark().isForeignLanguateTransliterationWording();

                    boolean containsSignatureName = baseTrademarkApplication.getTradeMark().isContainNamePortaitSignature();
                    boolean isName = baseTrademarkApplication.getTradeMark().isName();
                    boolean isSignature = baseTrademarkApplication.getTradeMark().isSignature();
                    boolean isPortrait =  baseTrademarkApplication.getTradeMark().isPortrait();
                    boolean isNPSLivingPerson =  baseTrademarkApplication.getTradeMark().isNPSLivingPerson();

                    model.addAttribute("markColorClaim", colorClaim);
                    model.addAttribute("markColorClaimBW", acceptBW);
                    model.addAttribute("translationFW", translationFW);
                    model.addAttribute("translitFW", transliterationFW);
                    model.addAttribute("containsSignatureName", containsSignatureName );
                    model.addAttribute("isName", isName );
                    model.addAttribute("isSignature", isSignature );
                    model.addAttribute("isPortrait", isPortrait );
                    model.addAttribute("isNPSLivingPerson", isNPSLivingPerson );
                }
                catch ( StorageException ex){
                    model.addAttribute("message", "ERROR: Mark Image upload failed due to error: "+ex );
                    // return "forward:/mark/designWithText/?trademarkID="+trademarkInternalID;
                    return "application/mark/MarkDetailsDesignWText";

                }


                baseTradeMarkApplicationService.save(baseTrademarkApplication);


            }

        }
        model.addAttribute("breadCrumbStatus",baseTrademarkApplication.getSectionStatus());
        return "application/mark/MarkDetailsDesignWText";
        //return "application/MarkDetailsUpload";
    }
    ///////////////////////////////////////////////////////////////////////////////
    // end of attorney add
    ///////////////////////////////////////////////////////////////////////////////

    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @PostMapping(value = "/class/specimen/add")
    public ResponseEntity addClassCategorySpecimenImg(
                                      @RequestParam(name="file", required=false) MultipartFile file,
                                      @RequestParam (name="appID")String AppInternalID,
                                      @RequestParam (name="catNum")String classCategoryNumber,
                                      Model model
    ) {

        System.out.println("Specimen file upload!!!! ");

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID( AppInternalID);
        //Goods_ServicesService goods_servicesService = serviceBeanFactory.getGoodsAndServicesService();
        String filePath ="";
        if(file != null){
            System.out.println("file is not null");

            if(file.isEmpty() == false) {
               System.out.println("file is not empty !!!!!!!!!!!!!!!");


                try {
                    String image_path = storageService.store(file);

                    // this will be store for each good and service that matches this

                    // on building class categoreis, this value is then copied over
                    // file path returned to client in response

                    // server redraw should render the image file path from category object


                   // baseTrademarkApplication.getc("/files/"+image_path);
                    filePath = "/files/"+image_path;

                    for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                        GoodAndService current = iter.next();

                        if(current.getClassNumber().equals(classCategoryNumber)){
                            current.setClassSpecimenImgPath(filePath);
                            //goods_servicesService.save(current);
                        }
                    }
                    model.addAttribute("markImagePath",baseTrademarkApplication.getTradeMark().getTrademarkImagePath());
                }
                catch ( StorageException ex){
                    model.addAttribute("message", "ERROR: Mark Image upload failed due to error: "+ex );
                    // return "forward:/mark/designWithText/?trademarkID="+trademarkInternalID;
                    return buildResponseEnity("420", "ERROR: Mark Image upload failed due to error: "+ex);

                }
                baseTradeMarkApplicationService.save(baseTrademarkApplication);
            }

        }
        else{
            System.out.println("file object is null");
        }


        return buildResponseEnity("200", "{image-url:" +filePath+"}");

        //return ResponseEntity.ok().build();

    }



    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @PostMapping(value = "/gs/specimen/add")
    public ResponseEntity addGSSpecimenImg(
            @RequestParam(name="file", required=false) MultipartFile file,
            @RequestParam (name="appID")String AppInternalID,
            @RequestParam (name="gsID")String gsID,
            Model model
    ) {

        System.out.println("GS Specimen file upload!!!! ");

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID( AppInternalID);

        String filePath ="";
        if(file != null){
            System.out.println("file is not null");

            if(file.isEmpty() == false) {
                System.out.println("file is not empty !!!!!!!!!!!!!!!");


                try {
                    String image_path = storageService.store(file);

                    // this will be store for each good and service that matches this

                    // on building class categoreis, this value is then copied over
                    // file path returned to client in response

                    // server redraw should render the image file path from category object


                    // baseTrademarkApplication.getc("/files/"+image_path);
                    filePath = "/files/"+image_path;

                    baseTrademarkApplication.findGSbyInternalID(gsID).setSampleImagePath(filePath);
                    baseTrademarkApplication.findGSbyInternalID(gsID).setSampleUploaded(true);

                    model.addAttribute("markImagePath",baseTrademarkApplication.getTradeMark().getTrademarkImagePath());
                }
                catch ( StorageException ex){
                    model.addAttribute("message", "ERROR: Mark Image upload failed due to error: "+ex );
                    // return "forward:/mark/designWithText/?trademarkID="+trademarkInternalID;
                    return buildResponseEnity("420", "ERROR: Mark Image upload failed due to error: "+ex);

                }
                baseTradeMarkApplicationService.save(baseTrademarkApplication);
            }

        }
        else{
            System.out.println("file object is null");
        }


        return buildResponseEnity("200", "{image-url:" +filePath+"}");

        //return ResponseEntity.ok().build();

    }
    ////////////////////////////////////////////////

    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @PostMapping(value = "/fb/foreignReg/add")
    public ResponseEntity addGSSfrRegCertImg(
            @RequestParam(name="file", required=false) MultipartFile file,
            @RequestParam (name="appID")String AppInternalID,
            @RequestParam (name="gsID")String gsID,
            Model model
    ) {

        System.out.println("GS FR registrton certifcate file upload!!!! ");

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID( AppInternalID);

        String filePath ="";
        if(file != null){
            System.out.println("file is not null");

            if(file.isEmpty() == false) {
                System.out.println("file is not empty !!!!!!!!!!!!!!!");


                try {
                    String image_path = storageService.store(file);

                    // this will be store for each good and service that matches this

                    // on building class categoreis, this value is then copied over
                    // file path returned to client in response

                    // server redraw should render the image file path from category object


                    // baseTrademarkApplication.getc("/files/"+image_path);
                    filePath = "/files/"+image_path;

                    baseTrademarkApplication.findGSbyInternalID(gsID).setFrCertImagePath(filePath);



                }
                catch ( StorageException ex){
                    model.addAttribute("message", "ERROR: Mark Image upload failed due to error: "+ex );
                    // return "forward:/mark/designWithText/?trademarkID="+trademarkInternalID;
                    return buildResponseEnity("420", "ERROR: Mark Image upload failed due to error: "+ex);

                }
                baseTradeMarkApplicationService.save(baseTrademarkApplication);
            }

        }
        else{
            System.out.println("file object is null");
        }


        return buildResponseEnity("200", "{image-url:" +filePath+"}");

        //return ResponseEntity.ok().build();

    }
    ////////////////////////////////////////////////



    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @PostMapping(value = "/misc/specimen/add")
    public ResponseEntity addMiscInfoImg(
            @RequestParam(name="file", required=false) MultipartFile file,
            @RequestParam (name="appID")String AppInternalID,
            Model model
    ) {

        System.out.println("Misc Specimen file upload!!!! ");

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID( AppInternalID);

        String filePath ="";
        if(file != null){
            System.out.println("file is not null");

            if(file.isEmpty() == false) {
                System.out.println("file is not empty !!!!!!!!!!!!!!!");


                try {
                    String image_path = storageService.store(file);

                    // this will be store for each good and service that matches this

                    // on building class categoreis, this value is then copied over
                    // file path returned to client in response

                    // server redraw should render the image file path from category object


                    // baseTrademarkApplication.getc("/files/"+image_path);
                    filePath = "/files/"+image_path;

                    baseTrademarkApplication.setMiscInfoImagePath(filePath);


                   // model.addAttribute("markImagePath",baseTrademarkApplication.getTradeMark().getTrademarkImagePath());
                }
                catch ( StorageException ex){
                    model.addAttribute("message", "ERROR: Mark Image upload failed due to error: "+ex );
                    // return "forward:/mark/designWithText/?trademarkID="+trademarkInternalID;
                    return buildResponseEnity("420", "ERROR: Mark Image upload failed due to error: "+ex);

                }
                baseTradeMarkApplicationService.save(baseTrademarkApplication);
            }

        }
        else{
            System.out.println("file object is null");
        }


        return buildResponseEnity("200", "{image-url:" +filePath+"}");

        //return ResponseEntity.ok().build();

    }


    // hopefully just a redirect here, we won't need to add the applicaiton and credentials to the model
    @PostMapping(value = "/mark/js/add")
    public ResponseEntity addMarkJSupload(
            @RequestParam(name="file", required=false) MultipartFile file,
            @RequestParam (name="appID")String AppInternalID,
            Model model
    ) {

        System.out.println("Mark image js file upload!!!! ");

        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID( AppInternalID);

        String filePath ="";
        if(file != null){
            System.out.println("file is not null");

            if(file.isEmpty() == false) {
                System.out.println("file is not empty !!!!!!!!!!!!!!!");


                try {
                    String image_path = storageService.store(file);
                    filePath = "/files/"+image_path;

                    baseTrademarkApplication.getTradeMark().setTrademarkImagePath("/files/"+image_path);
                    //model.addAttribute("markImagePath",baseTrademarkApplication.getTradeMark().getTrademarkImagePath());
                }
                catch ( StorageException ex){
                   // model.addAttribute("message", "ERROR: Mark Image upload failed due to error: "+ex );
                    // return "forward:/mark/designWithText/?trademarkID="+trademarkInternalID;
                    buildResponseEnity("420", "{error:" +"ERROR: Mark Image upload failed due to error: "+ex+"}");

                }

                // generate black and white version and store path

                try {
                    String BWimagePath = storageService.storeBW(file);

                    baseTrademarkApplication.getTradeMark().setTrademarkBWImagePath("/files/"+BWimagePath);
                    //model.addAttribute("markImagePathBW",baseTrademarkApplication.getTradeMark().getTrademarkBWImagePath());

                }
                catch ( StorageException ex){
                    //model.addAttribute("message", "ERROR: BW Mark Image upload failed due to error: "+ex );
                    // return "forward:/mark/designWithText/?trademarkID="+trademarkInternalID;
                    buildResponseEnity("420", "{error:" +"ERROR: BW Mark Image upload failed due to error: "+ex+"}");

                }
                baseTrademarkApplication.setTradeMarkUploaded(true);
                baseTradeMarkApplicationService.save(baseTrademarkApplication);
                baseTradeMarkApplicationService.save(baseTrademarkApplication);
            }

        }
        else{
            System.out.println("file object is null");
        }


        return buildResponseEnity("200", "{image-url:" +filePath+"}");

        //return ResponseEntity.ok().build();

    }




    @RequestMapping({"/application/success"})
    public String applicaitonSucces(WebRequest request, Model model, @RequestParam("trademarkID") String trademarkInternalID) {
        // get owner info


        // get email and get PTOUser object from repository
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(authentication.getName());

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserCredentials credentials = userCredentialsService.findByEmail(authentication.getName());

        model.addAttribute("user", ptoUser);
        model.addAttribute("account",credentials);
        model.addAttribute("hostBean", hostBean);
        String applcationLookupID = trademarkInternalID;
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(trademarkInternalID);


        // generate pdf file and add it to model
        // update application status
        baseTrademarkApplication.setLastViewModel("application/success/index");
        ArrayList<String> sectionStatus = baseTrademarkApplication.getSectionStatus();
        sectionStatus.set(0,"done");
        sectionStatus.set(1,"done");
        sectionStatus.set(2,"done");
        sectionStatus.set(3,"done");
        sectionStatus.set(4,"done");
        sectionStatus.set(5,"done");
        baseTrademarkApplication.setSectionStatus(sectionStatus);


        if(baseTrademarkApplication.getFilingStatus() != "Filed"){
            Document document = new Document();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font boldFontSmall = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

            try {
                PdfWriter.getInstance(document, byteArrayOutputStream);
                document.open();

                //Chunk chunk = new Chunk();
                Paragraph paragraph = new Paragraph("Filing Receipt for Trademark Service Mark Application for Registration on the Principal Register and Next Steps in the Application Process", boldFont);
                document.add(paragraph);
                paragraph = new Paragraph("Thank you for submitting your trademark application to the U.S. Patent and Trademark Office (USPTO). This filing receipt confirms your mark and serial number, describes next steps in the application process, and includes the information submitted in your application. Please read this receipt carefully and keep a copy for your records.", normalFont);
                document.add(paragraph);

                /////////////////////////////////////////////////////////////////////////////////////////////////
                Phrase phrase = new Phrase("",normalFont);
                phrase.add("For an overview of important things to know after filing your application, visit our website to read the");
                Chunk chunk = new Chunk("After You File");
                chunk.setAnchor("https://www.uspto.gov/trademarks-application-process/filing-online/after-you-file");
                phrase.add(chunk);
                phrase.add("page and watch video number 9 \"");
                phrase.add(chunk);
                document.add(phrase);

                String colorClaimString ="";
                if(baseTrademarkApplication.getTradeMark().isMarkColorClaim()){
                    colorClaimString = "with";
                }
                else {
                    colorClaimString = "without";

                }


                phrase = new Phrase("1. Your mark. ",boldFontSmall);
                paragraph = new Paragraph();
                paragraph.add(phrase);
                paragraph.add("The mark in your application is "+baseTrademarkApplication.getTradeMark().getMarkDescription()+". The mark consists of "+baseTrademarkApplication.getTradeMark().getTrademarkDesignType()+","+colorClaimString +" claim of any particular font style, size, or color.");
                document.add(paragraph);



                phrase = new Phrase("2. Your serial number. ",boldFontSmall);
                paragraph = new Paragraph();
                paragraph.add(phrase);
                paragraph.add("Your application was assigned serial number "+baseTrademarkApplication.getTrademarkName()+". You must refer to your serial number in all communications about your application.");
                document.add(paragraph);



                phrase = new Phrase("3. What happens next - legal examination. ",boldFontSmall);
                paragraph = new Paragraph();
                paragraph.add(phrase);
                paragraph.add("Your mark will not be registered automatically. In approximatelty three months, your applicaiton will be assigned to a USPTO examining attorney for review. The attorney will deterimine if your application meets all applicable legal requirements, and if it doesn't you will be notified in an email with a link to the official Office action(official letter from the USPTO). Visit our website for an explanation of application process time lines.");
                document.add(paragraph);

                phrase = new Phrase("4. Keep your address current in USPTO records",boldFontSmall);
                paragraph = new Paragraph();
                paragraph.add(phrase);
                paragraph.add("We do not extend filing deadlines if you do not receive USPTO mail or email. If your postal address or email address changes, you must update the correspondence or owner's address using the address forms on our website.");
                document.add(paragraph);


                phrase = new Phrase("5. Check your application status in our database every three to four months. ",boldFontSmall);
                paragraph = new Paragraph();
                paragraph.add(phrase);
                paragraph.add("To be sure that you don't miss an important email from us, and to avoid the possible abandonment of your application, check your application status and review your documents in our database, Trademark Status and Document Retrieval (TSDR), every three to four months.");
                document.add(paragraph);


                phrase = new Phrase("6. Warning about private companies offering trademark-related services. ",boldFontSmall);
                paragraph = new Paragraph();
                paragraph.add(phrase);
                paragraph.add("Private companies may send you communications that resemble official USPTO communications. These private companies are not associated with the USPTO. All official correspondence will be from the \"United States Patent and Trademark Office\" in Alexandria, Virginia, and from emails with the domain \"uspto.gov.\" If you are unsure about whether the correspondence is from us, check your records in our database, TSDR. Visit our website for more information on trademark-related communications that may resemble official USPTO communications.");
                document.add(paragraph);


                phrase = new Phrase("7. Questions? ",boldFontSmall);
                paragraph = new Paragraph();
                paragraph.add(phrase);
                paragraph.add("Please visit our website, email us, or call us at 1-800-786-9199 and select option 1.");
                document.add(paragraph);



                phrase = new Phrase("8. Application data. ",boldFontSmall);
                paragraph = new Paragraph();
                paragraph.add(phrase);
                paragraph.add("If you find an error in the data below, visit the After You File page on our website for information on correcting errors.");
                document.add(paragraph);


                paragraph = new Paragraph("The information submitted in the application appears below: ", boldFontSmall);
                document.add(paragraph);

                paragraph = new Paragraph("Under the Paperwork Reduction Act of 1995 no persons are required to respond to a collection of information unless it displays a valid OMB control number.", normalFont);
                document.add(paragraph);

                paragraph = new Paragraph("PTO Form 1478 (Rev 09/2006)", normalFont);
                document.add(paragraph);

                paragraph = new Paragraph("OMB No. 0651-0009 (Exp 02/28/2018)", normalFont);
                document.add(paragraph);

                paragraph = new Paragraph("Trademark/Service Mark Application, Principal Register ", normalFont);
                document.add(paragraph);

                phrase = new Phrase("Note: ",boldFontSmall);
                paragraph = new Paragraph();
                paragraph.add(phrase);
                paragraph.add("Data fields with the * are mandatory. The wording \"(if applicable)\" appears where the field is only mandatory under the facts of the particular application. ");
                document.add(paragraph);

                paragraph = new Paragraph(" ", normalFont);
                document.add(paragraph);

                paragraph = new Paragraph("The table below presents the data as entered. ", normalFont);
                document.add(paragraph);

                paragraph = new Paragraph(" ", normalFont);
                document.add(paragraph);
                paragraph = new Paragraph(" ", normalFont);
                document.add(paragraph);
                // dynamic section adding tables and rows
                PdfPTable table = new PdfPTable(2);
                addTableHeader(table);
                table.addCell("SERIAL NUMBER");
                table.addCell(baseTrademarkApplication.getTrademarkName());
                table.addCell("MARK INFORMATION");
                table.addCell("");
                table.addCell("MARK");
                table.addCell(baseTrademarkApplication.getTradeMark().getMarkDescription());
                table.addCell("STANDARD CHARACTERS");
                if(baseTrademarkApplication.getTradeMark().getTrademarkDesignType().equals("Standard Characters")){
                    table.addCell("YES");
                    table.addCell("USPTO-GENERATED IMAGE");
                    table.addCell("YES");
                }
                else {
                    table.addCell("NO");
                    table.addCell("USPTO-GENERATED IMAGE");
                    table.addCell("NO");
                }
                table.addCell("LITERAL ELEMENT");
                table.addCell(baseTrademarkApplication.getTradeMark().getMarkLiteral());


                table.addCell("MARK STATEMENT");
                table.addCell("The mark consists of "+baseTrademarkApplication.getTradeMark().getTrademarkDesignType()+","+colorClaimString +" claim of any particular font style, size, or color.");

                table.addCell("APPLICATION INFORMATION");
                table.addCell("");
                table.addCell("OWNER OF MARK");
                table.addCell(baseTrademarkApplication.getPrimaryOwner().getOwnerDisplayname());
                table.addCell("DBA/AKA/TA/Formerly");
                table.addCell("");
                table.addCell("STREET");
                table.addCell(baseTrademarkApplication.getPrimaryOwner().getAddress1());
                table.addCell("CITY");
                table.addCell(baseTrademarkApplication.getPrimaryOwner().getCity());
                table.addCell("STATE");
                table.addCell(baseTrademarkApplication.getPrimaryOwner().getState());
                table.addCell("COUNTRY");
                table.addCell(baseTrademarkApplication.getPrimaryOwner().getOwnerEntityCountryOfOrigin());
                table.addCell("ZIP/POSTAL CODE");
                table.addCell(baseTrademarkApplication.getPrimaryOwner().getZipcode());
                table.addCell("PHONE");
                table.addCell(baseTrademarkApplication.getPrimaryOwner().getPrimaryPhonenumber());


                table.addCell("LEGAL ENTITY INFORMATION");
                table.addCell("");
                table.addCell("TYPE");
                table.addCell(baseTrademarkApplication.getPrimaryOwner().getOwnersubType());
                table.addCell("STATE/COUNTRY WHERE LEGALLY ORGANIZED");
                table.addCell(baseTrademarkApplication.getPrimaryOwner().getOwnerOrganizationState());



                table.addCell("GOODS AND/OR SERVICES AND BASIS INFORMATION");
                table.addCell("");
                // run a for loop on the ordered list ...
                ArrayList<GSClassCategory> gsClassCategories = baseTrademarkApplication.getGoodAndServicesCategories();


                for(Iterator<GSClassCategory> iter = gsClassCategories.iterator(); iter.hasNext(); ) {
                    GSClassCategory current = iter.next();
                    for(Iterator<GoodAndService> iter2 = current.getGoodAndServices().iterator(); iter2.hasNext(); ) {
                        GoodAndService current2 = iter2.next();

                        table.addCell("INTERNATIONAL CLASS");
                        table.addCell(current2.getClassNumber());
                        table.addCell("IDENTIFICATION");
                        table.addCell(current2.getClassDescription());
                        table.addCell("FILING BASIS");
                        table.addCell(current2.getIdentification());

                    }


                }


                if(baseTrademarkApplication.isAttorneyFiling()) {
                    table.addCell("ATTORNEY INFORMATION");
                    table.addCell("");
                    table.addCell("NAME");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getFirstName()+" "+baseTrademarkApplication.getPrimaryLawyer().getLastName());
                    table.addCell("STREET");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getAddress1());
                    table.addCell("CITY");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getCity());
                    table.addCell("STATE");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getState());
                    table.addCell("COUNTRY");
                    table.addCell(baseTrademarkApplication.getPrimaryOwner().getOwnerEntityCountryOfOrigin());
                    table.addCell("ZIP/POSTAL CODE");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getZipcode());
                    table.addCell("PHONE");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getPrimaryPhonenumber());
                    table.addCell("FAX");
                    table.addCell("");
                    table.addCell("EMAIL ADDRESS");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getEmail());
                    table.addCell("AUTHORIZED TO COMMUNICATE VIA EMAIL");
                    table.addCell("YES");

                    table.addCell("CORRESPONDENCE INFORMATION");
                    table.addCell("");
                    table.addCell("NAME");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getFirstName()+" "+baseTrademarkApplication.getPrimaryLawyer().getLastName());
                    table.addCell("STREET");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getAddress1());
                    table.addCell("CITY");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getCity());
                    table.addCell("STATE");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getState());
                    table.addCell("COUNTRY");
                    table.addCell(baseTrademarkApplication.getPrimaryOwner().getOwnerEntityCountryOfOrigin());
                    table.addCell("ZIP/POSTAL CODE");
                    table.addCell(baseTrademarkApplication.getPrimaryLawyer().getZipcode());




                }
                else {

                    table.addCell("CORRESPONDENCE INFORMATION");
                    table.addCell("");
                    table.addCell("NAME");
                    table.addCell(baseTrademarkApplication.getPrimaryOwner().getOwnerDisplayname());
                    table.addCell("STREET");
                    table.addCell(baseTrademarkApplication.getPrimaryOwner().getAddress1());
                    table.addCell("CITY");
                    table.addCell(baseTrademarkApplication.getPrimaryOwner().getCity());
                    table.addCell("STATE");
                    table.addCell(baseTrademarkApplication.getPrimaryOwner().getState());
                    table.addCell("COUNTRY");
                    table.addCell(baseTrademarkApplication.getPrimaryOwner().getOwnerEntityCountryOfOrigin());
                    table.addCell("ZIP/POSTAL CODE");
                    table.addCell(baseTrademarkApplication.getPrimaryOwner().getZipcode());
                }














                document.add(table);

                document.close();
            }
            catch(Exception ex){
                // file not found

            }

            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            String strFilePath = trademarkInternalID+"TrademarkApplicationRecipet.pdf";
            FileOutputStream fos = null;

            File pdffile = new File(strFilePath);


            try {

                fos = new FileOutputStream(pdffile);
                fos.write(pdfBytes);
                fos.close();
            }
            catch(FileNotFoundException ex)   {
                System.out.println("FileNotFoundException : " + ex);
            }
            catch(IOException ioe)  {
                System.out.println("IOException : " + ioe);
            }


            // save pdf file object
            // return file path in model

            String recieptFilePath = storageService.storeFile(pdffile, strFilePath);

            // this will be store for each good and service that matches this

            // on building class categoreis, this value is then copied over
            // file path returned to client in response

            // server redraw should render the image file path from category object


            // baseTrademarkApplication.getc("/files/"+image_path);
            //recieptFilePath  = "/files-server/"+recieptFilePath;

            recieptFilePath  = "/files-pdf/"+recieptFilePath;

            baseTrademarkApplication.setRecieptFilePath(recieptFilePath);
            baseTrademarkApplication.setFilingStatus("Filed");
            baseTradeMarkApplicationService.save(baseTrademarkApplication);

        }




        model.addAttribute("baseTrademarkApplication", baseTrademarkApplication);

        return "application/success/index";

    }






    ////////////////////////////////////////////////
    // build response enity for REST API
    ////////////////////////////////////////////////
    ResponseEntity<String> buildResponseEnity(String status_code, String response_main) {

        //String statusCode = "404";
        String statusCode = status_code;
        //String responseMsg = "Contact with email address :"+contact_email+ "has not been set as Primary Attorney. invalid user session.";
        String responseMsg = response_main;
        responseMsg = "{status:" + statusCode + " } { msg:" + responseMsg + " }";
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setAccessControlAllowOrigin(hostBean.getHost() + hostBean.getPort());
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        return ResponseEntity.ok().headers(responseHeader).body(responseMsg);
    }


    private void addTableHeader(PdfPTable table) {
        Stream.of("Input Field", "Entered Value")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.CYAN);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }


}


