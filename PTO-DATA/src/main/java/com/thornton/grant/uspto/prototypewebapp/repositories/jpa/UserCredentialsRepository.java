package com.thornton.grant.uspto.prototypewebapp.repositories.jpa;

import com.thornton.grant.uspto.prototypewebapp.model.entities.UserCredentials;
import org.springframework.data.repository.CrudRepository;

public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Long> {
}
