package com.thorton.grant.uspto.prototypewebapp.repositories.jpa;

import com.thorton.grant.uspto.prototypewebapp.model.entities.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository <UserRole, Long> {
}
