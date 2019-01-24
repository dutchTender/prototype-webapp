package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.security;

import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository <UserRole, Long> {
}
