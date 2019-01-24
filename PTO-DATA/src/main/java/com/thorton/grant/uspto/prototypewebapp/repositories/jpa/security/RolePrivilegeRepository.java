package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.security;

import com.thorton.grant.uspto.prototypewebapp.model.entities.security.RolePrivilege;
import org.springframework.data.repository.CrudRepository;

public interface RolePrivilegeRepository  extends CrudRepository<RolePrivilege, Long> {
}
