package com.thorton.grant.uspto.prototypewebapp.config.security.aws;

public class CognitoClientBean implements IdentityProviderClient{





    @Override
    public String getClientPassWord() {
        return null;
    }

    @Override
    public String getClientEmail() {
        return null;
    }

    @Override
    public boolean getClientAccountEnabledStatus() {
        return false;
    }

    @Override
    public boolean getClientAccountActiveStatus() {
        return false;
    }

    @Override
    public boolean getClientAccountLockedStatus() {
        return false;
    }

    @Override
    public boolean getClientAccountGrantedAuthorities() {
        return false;
    }
}
