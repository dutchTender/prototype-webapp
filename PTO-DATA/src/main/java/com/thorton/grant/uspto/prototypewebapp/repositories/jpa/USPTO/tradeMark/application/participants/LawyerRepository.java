package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.application.participants;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import org.springframework.data.repository.CrudRepository;

public interface LawyerRepository extends CrudRepository <Lawyer, Long> {

    Lawyer findByEmail(String email);

}
