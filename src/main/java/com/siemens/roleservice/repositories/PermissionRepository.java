package com.siemens.roleservice.repositories;

import com.siemens.roleservice.domain.entities.PermissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<PermissionEntity, String> {
}
