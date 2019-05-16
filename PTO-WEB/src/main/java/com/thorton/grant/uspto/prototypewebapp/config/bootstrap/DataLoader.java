package com.thorton.grant.uspto.prototypewebapp.config.bootstrap;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.actions.PetitionService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.Petition;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserRole;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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

        PetitionService petitionService = serviceBeanFactory.getPetitionService();

        ////////////////////////////////////////////////////////////////////////////////








        // start scheduler for job that runs nightly to udpate filing statuses and check expiration dates for actions ...etc
        Timer timer = new Timer();


        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111");
        FilingStatusUpdateTask filingStatusUpdateTask = new FilingStatusUpdateTask(serviceBeanFactory);

        System.out.println("99999999999999999999999999999999999999999999999999999999999999999999999999999");
        Date date = new Date();

        // in production. duration will be set to 30 minutes, and delay will be set to 23.5 hours
        timer.schedule(filingStatusUpdateTask, Long.valueOf(60000*2), Long.valueOf(60000*1));

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
