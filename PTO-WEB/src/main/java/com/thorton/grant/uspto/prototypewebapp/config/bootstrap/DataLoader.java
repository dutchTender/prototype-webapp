package com.thorton.grant.uspto.prototypewebapp.config.bootstrap;

import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserRole;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>         {

    private  final ServiceBeanFactory serviceBeanFactory;

    public DataLoader(ServiceBeanFactory serviceBeanFactory) {
        this.serviceBeanFactory = serviceBeanFactory;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


        PTOUserService myPTOUserService = serviceBeanFactory.getPTOUserService();


        UserCredentialsService userCredentialsService = serviceBeanFactory.getUserCredentialsService();

        UserRoleService userRoleService = serviceBeanFactory.getUserRoleService();


        PTOUser PTOUser1 = new PTOUser();
        PTOUser1.setFirstName("Li");
        PTOUser1.setLastName("Zhang");
        PTOUser1.setAddress("1115 Reserve Champion Drive");
        PTOUser1.setCity("Rockville");
        PTOUser1.setTelephone("571-839-3730");

        // set username, password, email and role
        UserCredentials ownerCreds = new UserCredentials();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // follow the same convention from the save method and save role for test user 1

        ownerCreds.setUsername("zhangl");
        ownerCreds.setPassword(bCryptPasswordEncoder.encode("xxxxx"));
        ownerCreds.setPasswordConfirm(bCryptPasswordEncoder.encode("xxxxx"));
        ownerCreds.setEmail("lzhang421@gmail.com");
        ownerCreds.setActive(1);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_ADMIN");
        //myRoleService.save(userRole);
        ownerCreds.setUserRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
        // set credentails to active

        ownerCreds.setActive(1);
        // create bi-directional relationship between credentials and owner
        ownerCreds.setUserPersonalData(PTOUser1);
        PTOUser1.setUserCredentials(ownerCreds);
        PTOUser1.setEmail(ownerCreds.getEmail());
        myPTOUserService.save(PTOUser1);
        userRoleService.save(userRole);
        userCredentialsService.save(ownerCreds);
       // userRoleService.save(userRole);



        // now add objects to repository, owner and user

/*

        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        HashSet<Privilege>   adminPrivileges= new HashSet<Privilege>(Arrays.asList(
                readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", new HashSet<Privilege>(Arrays.asList(readPrivilege)));

        Role adminRole = myRoleService.findByRole("ROLE_ADMIN");
        User user = new User();
        //user.setFirstName("Test");
        //user.setLastName("Test");
        user.setUsername("test_user");
        user.setPassword(bCryptPasswordEncoder.encode("test123"));
        user.setEmail("test@test.com");
        user.setRoles( new HashSet<Role>(Arrays.asList(adminRole)));
        user.setActive(1);
        myUsersService.save(user);



*/




    }
}
