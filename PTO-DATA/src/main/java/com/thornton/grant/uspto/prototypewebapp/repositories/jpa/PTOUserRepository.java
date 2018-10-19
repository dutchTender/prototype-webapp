package com.thornton.grant.uspto.prototypewebapp.repositories.jpa;

import com.thornton.grant.uspto.prototypewebapp.model.entities.PTOUser;
import org.springframework.data.repository.CrudRepository;

public interface PTOUserRepository extends CrudRepository<PTOUser, Long> {

    PTOUser findByEmail(String email);


}
