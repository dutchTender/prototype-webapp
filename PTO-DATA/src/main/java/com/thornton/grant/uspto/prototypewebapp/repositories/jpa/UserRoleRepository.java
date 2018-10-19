package com.thornton.grant.uspto.prototypewebapp.repositories.jpa;

import com.thornton.grant.uspto.prototypewebapp.model.entities.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository <UserRole, Long> {
}
