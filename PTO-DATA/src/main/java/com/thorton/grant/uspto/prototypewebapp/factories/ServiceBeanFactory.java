package com.thorton.grant.uspto.prototypewebapp.factories;

import com.thorton.grant.uspto.prototypewebapp.interfaces.ApplicantService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.UserCredentialsService;
import org.springframework.stereotype.Component;

@Component
public class ServiceBeanFactory {

    private final ApplicantService applicantService;
    private final UserCredentialsService userCredentialsService;

    public ServiceBeanFactory(ApplicantService applicantService, UserCredentialsService userCredentialsService) {
        this.applicantService = applicantService;
        this.userCredentialsService = userCredentialsService;
    }

    public ApplicantService getApplicantService() {
        return applicantService;
    }

    public UserCredentialsService getUserCredentialsService() {
        return userCredentialsService;
    }

}
