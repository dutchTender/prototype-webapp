package com.thorton.grant.uspto.prototypewebapp.repositories.jpa;

import com.thorton.grant.uspto.prototypewebapp.model.entities.UserCredentials;
import org.springframework.data.repository.CrudRepository;

public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Long> {
}
