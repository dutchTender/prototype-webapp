package com.thorton.grant.uspto.prototypewebapp.factories;

import com.thorton.grant.uspto.prototypewebapp.interfaces.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.UserRoleService;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Component;

@Component
public class ServiceBeanFactory {

    private final PTOUserService PTOUserService;
    private final UserCredentialsService userCredentialsService;
    private final UserRoleService userRoleService;




    public ServiceBeanFactory(com.thorton.grant.uspto.prototypewebapp.interfaces.PTOUserService PTOUserService, UserCredentialsService userCredentialsService, UserRoleService userRoleService) {
        this.PTOUserService = PTOUserService;
        this.userCredentialsService = userCredentialsService;
        this.userRoleService = userRoleService;
    }

    public com.thorton.grant.uspto.prototypewebapp.interfaces.PTOUserService getPTOUserService() {
        return PTOUserService;
    }

    public UserCredentialsService getUserCredentialsService() {
        return userCredentialsService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }
}
