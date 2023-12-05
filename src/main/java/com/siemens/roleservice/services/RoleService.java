package com.siemens.roleservice.services;

import com.siemens.roleservice.domain.entities.RoleEntity;

import java.util.List;

public interface RoleService {
    List<RoleEntity> findAll();
}
