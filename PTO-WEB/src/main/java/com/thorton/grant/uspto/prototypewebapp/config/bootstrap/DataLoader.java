package com.thorton.grant.uspto.prototypewebapp.config.bootstrap;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserRole;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>         {

    private  final ServiceBeanFactory serviceBeanFactory;

    public DataLoader(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        ////////////////////////////////////////////////////////////////////////////////
        // inject services from factory
        ////////////////////////////////////////////////////////////////////////////////
        PTOUserService myPTOUserService = serviceBeanFactory.getPTOUserService();
        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        UserRoleService userRoleService = serviceBeanFactory.getUserRoleService();
        BaseTradeMarkApplicationService tradeMarkApplicationService = serviceBeanFactory.getBaseTradeMarkApplicationService();
        LawyerService lawyerService = serviceBeanFactory.getLawyerService();

        ////////////////////////////////////////////////////////////////////////////////


        PTOUser PTOUser1 = new PTOUser();
        PTOUser1.setFirstName("test");
        PTOUser1.setLastName("user");
        PTOUser1.setAddress("1115 Reserve Champion Drive");
        PTOUser1.setCity("Rockville");
        PTOUser1.setState("MD");
        PTOUser1.setZipcode("20850");
        PTOUser1.setCountry("X1"); // country code for united states
        PTOUser1.setPrimaryPhonenumber("571-839-3730");
        PTOUser1.setProfileComplete(true);
        /////////////////////////////////////////////////////////////////////////////////
        // set username, password, email and role
        /////////////////////////////////////////////////////////////////////////////////
        UserCredentials ownerCreds = new UserCredentials();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        /////////////////////////////////////////////////////////////////////////////////
        // follow the same convention from the save method and save role for test user 1
        /////////////////////////////////////////////////////////////////////////////////

        ownerCreds.setUsername("test.user");
        ownerCreds.setPassword(bCryptPasswordEncoder.encode("xxxxx"));
        ownerCreds.setPasswordConfirm(bCryptPasswordEncoder.encode("xxxxx"));
        ownerCreds.setEmail("lzhang422@gmail.com");
        ownerCreds.setActive(1);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_ADMIN");
        //myRoleService.save(userRole);
        ownerCreds.setUserRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
        //////////////////////////////////////////////////////////////////////////////////
        // set credentails to active
        //////////////////////////////////////////////////////////////////////////////////
        ownerCreds.setActive(1);
        //////////////////////////////////////////////////////////////////////////////////
        // create bi-directional relationship between credentials and owner
        //////////////////////////////////////////////////////////////////////////////////
        ownerCreds.setUserPersonalData(PTOUser1);
        PTOUser1.setUserCredentials(ownerCreds);
        PTOUser1.setEmail(ownerCreds.getEmail());
        // let us create an application and add it to PTOUser1


        /////////////////////////////////////////////////////////////////////////////
        BaseTrademarkApplication trademarkApplication = new BaseTrademarkApplication();
        trademarkApplication.setPtoUser(PTOUser1);

        //trademarkApplication.setLastViewModel("application/MarkDetailsStart");
        //trademarkApplication.setLastViewModel("application/additional/additionalInfo");
        trademarkApplication.setLastViewModel("application/mark/MarkDetailsDesignWText");
        //trademarkApplication.setLastViewModel("application/goods_services/GoodsServicesStart");
        //trademarkApplication.setLastViewModel("application/AttorneyStart");
        trademarkApplication.setAttorneySet(true);
        trademarkApplication.setAttorneyFiling(true);


        // tradeMark application needs an internal id that tieto the ptoUser ...
        // or examine how is application tied to the ptoUser

        //PTOUser1.addApplication(trademarkApplication);
        //////////////////////////////////////////////////////////////////
        Lawyer newLawyer = new Lawyer();
        newLawyer.setClient(PTOUser1);
        newLawyer.setLawFirmName("Grant Thornton, LLC");
        //newLawyer.setPoolMember(trademarkApplication);
        newLawyer.setBarLicense("DC234567889");
        newLawyer.setDocketNumber("100000000111");
        newLawyer.setBarJurisdiction("Washington DC Bar Association");
        newLawyer.setFirstName("test");
        newLawyer.setLastName("lawyer");
        newLawyer.setEmail("li.zhang@us.gt.com");
        newLawyer.setAddress("333 Carlyle Ave");
        newLawyer.setAddress1("333 Carlyle Ave");
        newLawyer.setCity("Alexandria");
        newLawyer.setState("VA");
        newLawyer.setZipcode("22222");
        newLawyer.setPrimaryPhonenumber("333-333-3333");


        PTOUser1.addLawyer(newLawyer);


        // add default managed contact

        ManagedContact managedContact = createCopyPTOUserInfo4ManagedContactAttorney(PTOUser1);


        PTOUser1.addManagedContact(managedContact);


        ///////////////////////////////////////////////////////////////////////////////////////////////
        // we need a copy constructor ...so that trademark Application lawyers are not the same ones
        // saved by PTOUser ...
        // as this will allow PTOUser to delete the application with out deleting his/hers lawyers.
        ///////////////////////////////////////////////////////////////////////////////////////////////

        //trademarkApplication.setOwnerEmail(PTOUser1.getEmail());
        //Owner testOwner = new Owner();
        //testOwner.setClient(PTOUser1);
        //testOwner.setAddress1(PTOUser1.getAddress());
       // testOwner.setAddress(PTOUser1.getAddress());
        //testOwner.setFirstName(PTOUser1.getFirstName());
        //testOwner.setLastName(PTOUser1.getLastName());
        //testOwner.setCitizenShip(PTOUser1.getCountry());
        //testOwner.setOwnerEnityType("US");
        //testOwner.setOwnersubType("Individual");
        // GoverningEntity testPartner = new GoverningEntity();
       // testPartner.setPartnerLastName("ike");
         //testPartner.setPartnerFirstName("mike");
        //testPartner.setPartnerCitizenship(PTOUser1.getCountry());
        //testOwner.addPartner(testPartner);

       // OwnerService ownerService = serviceBeanFactory.getOwnerService();

        //ownerService.save(testOwner);



        //Owner owner = new Owner();
        //owner.setOwnerType("individual");
        //owner.setEmail(PTOUser1.getEmail());
        //owner.setAddress(PTOUser1.getAddress());
        //owner.setFirstName(PTOUser1.getFirstName());
        //owner.setLastName(PTOUser1.getLastName());
        // owner.setCity(PTOUser1.getCity());
        //owner.setState(PTOUser1.getState());

        //trademarkApplication.setOwner(owner);
        /////////////////////////////////////////////////////////////////////////////////
        // add a method to PTOUser to just add one application
        /////////////////////////////////////////////////////////////////////////////////


        PTOUser1.addApplication(trademarkApplication);
        myPTOUserService.save(PTOUser1);
        userRoleService.save(userRole);
        userCredentialsService.save(ownerCreds);
        lawyerService.save(newLawyer);
        tradeMarkApplicationService.save(trademarkApplication);
        trademarkApplication.setTrademarkName("3000000");
        trademarkApplication.setApplicationInternalID(trademarkApplication.getTrademarkName()+trademarkApplication.getId());

        tradeMarkApplicationService.save(trademarkApplication);


        // create another user
        Set<PTOUser> managedContactsAttorneys = new HashSet<>();
        Set<PTOUser> managedContactsOwners = new HashSet<>();
        PTOUser Jackie = createUser("Jackie", "Babos", "333 Carlyle ave", "Alexendria", "VA", "22222", "X1", "333-333-3333", "Jackie.Babos@us.gt.com","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser Jacob = createUser("Jacob", "Goldstein", "333 Carlyle ave", "Alexendria", "VA", "22222", "X1", "444-444-444", "Jacob.Goldstein@us.gt.com","12345",managedContactsAttorneys,managedContactsOwners);
        PTOUser Avo = createUser("Avo", "Reed", "333 Carlyle ave", "Alexendria", "VA", "22222", "X1", "222-22-2222", "Avo.Reid@us.gt.com","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser Li = createUser("Li", "Zhang", "333 Carlyle ave", "Alexendria", "VA", "22222", "X1", "555-555-5555", "li.zhang@us.gt.com","12345", managedContactsAttorneys, managedContactsOwners);
        PTOUser lynn = createUser("Lynn", "Istanikmas", "333 Carlyle ave", "Alexendria", "VA", "22222", "X1", "555-555-5555", "lynn.stanikmas@us.gt.com","12345", managedContactsAttorneys, managedContactsOwners);

        managedContactsAttorneys.add(Jackie);
        managedContactsAttorneys.add(Jacob);
        managedContactsAttorneys.add(Avo);
        managedContactsOwners.add(lynn);
        managedContactsOwners.add(Li);


        PTOUser Lynn = createUser("Lynn", "Istanikmas", "600 Dulany Street", "Alexendria", "VA", "22222", "X1", "333-333-3333", "lstanikmas@gmail.com","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser Tina = createUser("Tina", "Donbeck", "600 Dulany Street", "Alexendria", "VA", "22222", "X1", "333-333-3333", "Tina.Donbeck@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser Al = createUser("Albert", "Young", "600 Dulany Street", "Alexendria", "VA", "22222", "X1", "333-333-3333", "Albert.Young@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser keyte = createUser("Keyte", "Ernst", "600 Dulany Street", "Alexendria", "VA", "22222", "X1", "333-333-3333", "Keyte.Ernst@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser shelly = createUser("Shelly", "Matte", "600 Dulany Street", "Alexendria", "VA", "22222", "X1", "333-333-3333", "Shelly.Matte@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);
        PTOUser Vi = createUser("Tuong-Vi", "Nguyen", "600 Dulany Street", "Alexendria", "VA", "22222", "X1", "333-333-3333", "Tuong-Vi.Nguyen@uspto.gov","12345",managedContactsAttorneys, managedContactsOwners);





    }




    // create helper function add user
    //////////////////////////////////////////////////
    public PTOUser createUser(String firstName, String lastName, String address, String city, String state, String zipcode, String country, String phone, String email, String password, Set<PTOUser> managedContactsAttorneys, Set<PTOUser> managedContactsOwners){


        PTOUser PTOUser1 = new PTOUser();
        PTOUser1.setFirstName(firstName);
        PTOUser1.setLastName(lastName);
        PTOUser1.setAddress(address);
        PTOUser1.setCity(city);
        PTOUser1.setState(state);
        PTOUser1.setZipcode(zipcode);
        PTOUser1.setCountry(country); // country code for united states
        PTOUser1.setPrimaryPhonenumber(phone);
        PTOUser1.setProfileComplete(true);
        /////////////////////////////////////////////////////////////////////////////////
        // set username, password, email and role
        /////////////////////////////////////////////////////////////////////////////////
        UserCredentials ownerCreds = new UserCredentials();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        /////////////////////////////////////////////////////////////////////////////////
        // follow the same convention from the save method and save role for test user 1
        /////////////////////////////////////////////////////////////////////////////////

        ownerCreds.setUsername(firstName+"."+lastName);
        ownerCreds.setPassword(bCryptPasswordEncoder.encode(password));
        ownerCreds.setPasswordConfirm(bCryptPasswordEncoder.encode(password));
        ownerCreds.setEmail(email);
        ownerCreds.setActive(1);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        //myRoleService.save(userRole);
        ownerCreds.setUserRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
        //////////////////////////////////////////////////////////////////////////////////
        // set credentails to active
        //////////////////////////////////////////////////////////////////////////////////
        ownerCreds.setActive(1);
        //////////////////////////////////////////////////////////////////////////////////
        // create bi-directional relationship between credentials and owner
        //////////////////////////////////////////////////////////////////////////////////
        ownerCreds.setUserPersonalData(PTOUser1);
        PTOUser1.setUserCredentials(ownerCreds);
        PTOUser1.setEmail(ownerCreds.getEmail());

        ManagedContact contact = createCopyPTOUserInfo4ManagedOwner(PTOUser1);
        PTOUser1.addManagedContact(contact);

        for(Iterator<PTOUser> iter = managedContactsOwners.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            PTOUser current = iter.next();
            PTOUser1.addManagedContact(createCopyPTOUserInfo4ManagedOwner(current));

        }


        for(Iterator<PTOUser> iter = managedContactsAttorneys.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            PTOUser current = iter.next();
            PTOUser1.addManagedContact(createCopyPTOUserInfo4ManagedContactAttorney(current));

        }



        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        ptoUserService.save(PTOUser1);
        UserRoleService  userRoleService = serviceBeanFactory.getUserRoleService();
        userRoleService.save(userRole);

        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();
        userCredentialsService.save(ownerCreds);

        return PTOUser1;

    }
    //////////////////////////////////////////////////////////////////////////////////



     //////////////////////////////////////////////////////////////////////////////////


     //////////////////////////////////////////////////////////////////////////////////
     // deep copy of field value to managedContact object
     //////////////////////////////////////////////////////////////////////////////////
     private ManagedContact createCopyPTOUserInfo4ManagedContactAttorney(PTOUser ptoUser){

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
    private ManagedContact createCopyPTOUserInfo4ManagedOwner(PTOUser ptoUser){

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
