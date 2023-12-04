package com.siemens.roleservice.services.impl;

import com.siemens.roleservice.domain.entities.PermissionEntity;
import com.siemens.roleservice.repositories.PermissionRepository;
import com.siemens.roleservice.services.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PermissionServiceImpl implements PermissionService {
    private PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<PermissionEntity> findAll() {
        return StreamSupport.stream(permissionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
