package com.thorton.grant.uspto.prototypewebapp.service;

import com.thorton.grant.uspto.prototypewebapp.interfaces.IUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.UserCredentialsDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;


@Service
public class UserRegistrationService implements IUserService {


    private final PTOUserService ptoUserService;
    private final UserCredentialsService userCredentialsService;
    private final UserRoleService userRoleService;
    private final  BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRegistrationService(PTOUserService ptoUserService, UserCredentialsService userCredentialsService, UserRoleService userRoleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.ptoUserService = ptoUserService;
        this.userCredentialsService = userCredentialsService;
        this.userRoleService = userRoleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserCredentials registerNewUserAccount(UserCredentialsDTO accountDto) {

        UserCredentials newUserCredentials = new UserCredentials();
        PTOUser newUser = new PTOUser();
        UserRole defaultRole = new UserRole();
        defaultRole.setRoleName("ROLE_FILER");
        userRoleService.save(defaultRole);

        newUser.setFirstName(accountDto.getFirstName());
        newUser.setLastName(accountDto.getLastName());
        newUser.setEmail(accountDto.getEmail());
        ptoUserService.save(newUser);

        newUserCredentials.setEmail(accountDto.getEmail());
        newUserCredentials.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        newUserCredentials.setPasswordConfirm(bCryptPasswordEncoder.encode(accountDto.getMatchingPassword()));
        newUserCredentials.setUserRoles(new HashSet<UserRole>(Arrays.asList(defaultRole)));
        newUserCredentials.setUserPersonalData(newUser);
        newUserCredentials.setActive(1);

        userCredentialsService.save(newUserCredentials);




        return newUserCredentials;
    }
}
