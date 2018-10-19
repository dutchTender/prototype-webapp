package com.thornton.grant.uspto.prototypewebapp.interfaces;

import com.thornton.grant.uspto.prototypewebapp.model.entities.DTO.UserCredentialsDTO;
import com.thornton.grant.uspto.prototypewebapp.model.entities.UserCredentials;

public interface IUserService {
    UserCredentials registerNewUserAccount( UserCredentialsDTO  accountDto);
}