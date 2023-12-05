package com.siemens.roleservice.repositories;

import com.siemens.roleservice.domain.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, String> {
}
