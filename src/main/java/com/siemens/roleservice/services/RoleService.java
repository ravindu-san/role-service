package com.siemens.roleservice.services;

import com.siemens.roleservice.domain.entities.RoleEntity;
import java.util.List;

public interface RoleService {
  List<RoleEntity> findAll();

  RoleEntity setPermissionsForRole(String roleId, RoleEntity roleEntity);

  boolean isExist(String roleId);
}
