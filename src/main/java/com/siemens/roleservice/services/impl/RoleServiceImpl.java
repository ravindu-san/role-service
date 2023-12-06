package com.siemens.roleservice.services.impl;

import com.siemens.roleservice.domain.entities.RoleEntity;
import com.siemens.roleservice.repositories.RoleRepository;
import com.siemens.roleservice.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleEntity> findAll() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public RoleEntity setPermissionsForRole(String roleId, RoleEntity roleEntity) {
        return roleRepository.findById(roleId)
                .map(existingRole -> {
                    existingRole.setPermissions(roleEntity.getPermissions());
                    return roleRepository.save(existingRole);
                }).orElseThrow();
    }

    @Override
    public boolean isExist(String roleId) {
        return roleRepository.existsById(roleId);
    }
}
