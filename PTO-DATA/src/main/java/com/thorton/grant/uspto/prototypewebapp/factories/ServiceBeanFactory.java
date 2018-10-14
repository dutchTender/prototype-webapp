package com.thorton.grant.uspto.prototypewebapp.factories;

import com.thorton.grant.uspto.prototypewebapp.interfaces.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.UserCredentialsService;
import org.springframework.stereotype.Component;

@Component
public class ServiceBeanFactory {

    private final PTOUserService PTOUserService;
    private final UserCredentialsService userCredentialsService;

    public ServiceBeanFactory(PTOUserService PTOUserService, UserCredentialsService userCredentialsService) {
        this.PTOUserService = PTOUserService;
        this.userCredentialsService = userCredentialsService;
    }

    public PTOUserService getPTOUserService() {
        return PTOUserService;
    }

    public UserCredentialsService getUserCredentialsService() {
        return userCredentialsService;
    }

}
