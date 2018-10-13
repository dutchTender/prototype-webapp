package com.thorton.grant.uspto.prototypewebapp.repositories.jpa;

import com.thorton.grant.uspto.prototypewebapp.model.entities.RolePrivilege;
import org.springframework.data.repository.CrudRepository;

public interface RolePrivilegeRepository  extends CrudRepository<RolePrivilege, Long> {
}
