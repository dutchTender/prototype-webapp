package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.application.participants;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository <Owner, Long> {

    Owner findByEmail(String email);
}
