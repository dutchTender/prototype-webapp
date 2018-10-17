package com.thorton.grant.uspto.prototypewebapp.repositories.jpa;

import com.thorton.grant.uspto.prototypewebapp.model.entities.PTOUser;
import org.springframework.data.repository.CrudRepository;

public interface PTOUserRepository extends CrudRepository<PTOUser, Long> {

    PTOUser findByEmail(String email);


}
