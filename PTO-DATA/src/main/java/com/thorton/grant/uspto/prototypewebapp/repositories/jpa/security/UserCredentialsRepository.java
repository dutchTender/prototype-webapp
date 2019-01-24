package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.security;

import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import org.springframework.data.repository.CrudRepository;

public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Long> {

    public UserCredentials findByEmail(String email);
}
