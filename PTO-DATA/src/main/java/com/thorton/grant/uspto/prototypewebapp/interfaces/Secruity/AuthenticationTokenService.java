package com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity;

import com.thorton.grant.uspto.prototypewebapp.interfaces.base.CrudService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.AuthenticationToken;

public interface AuthenticationTokenService extends CrudService<AuthenticationToken, Long> {

    AuthenticationToken findByToken(String token);
}
