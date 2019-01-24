package com.thorton.grant.uspto.prototypewebapp.config.security.aws;

public interface IdentityProviderClient {

    public String getClientPassWord();
    public String getClientEmail();
    public boolean getClientAccountEnabledStatus();
    public boolean getClientAccountActiveStatus();
    public boolean getClientAccountLockedStatus();
    public boolean getClientAccountGrantedAuthorities();

}
