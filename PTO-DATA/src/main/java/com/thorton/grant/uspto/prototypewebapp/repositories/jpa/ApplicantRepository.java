package com.thorton.grant.uspto.prototypewebapp.repositories.jpa;

import com.thorton.grant.uspto.prototypewebapp.model.entities.Applicant;
import org.springframework.data.repository.CrudRepository;

public interface ApplicantRepository extends CrudRepository<Applicant, Long> {
}
