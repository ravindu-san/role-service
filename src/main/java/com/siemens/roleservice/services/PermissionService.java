package com.siemens.roleservice.services;

import com.siemens.roleservice.domain.entities.PermissionEntity;

import java.util.List;

public interface PermissionService {
    List<PermissionEntity> findAll();
}
