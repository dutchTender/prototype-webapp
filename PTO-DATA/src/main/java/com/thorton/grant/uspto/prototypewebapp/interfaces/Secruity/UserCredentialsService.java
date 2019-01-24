package com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity;

import com.thorton.grant.uspto.prototypewebapp.interfaces.base.CrudService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;

public interface UserCredentialsService extends CrudService<UserCredentials, Long> {
    public UserCredentials findByEmail(String email);
}
