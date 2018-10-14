package com.thorton.grant.uspto.prototypewebapp.interfaces;

import com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.UserCredentialsDTO;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserCredentials;

public interface IUserService {
    UserCredentials registerNewUserAccount( UserCredentialsDTO  accountDto);
}