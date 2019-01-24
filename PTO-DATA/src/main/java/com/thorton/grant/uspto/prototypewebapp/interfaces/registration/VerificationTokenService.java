package com.thorton.grant.uspto.prototypewebapp.interfaces.registration;

import com.thorton.grant.uspto.prototypewebapp.interfaces.base.CrudService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;

public interface VerificationTokenService extends CrudService<VerificationToken, Long> {

    VerificationToken findByVerificationToken(String verificationToken);



}
