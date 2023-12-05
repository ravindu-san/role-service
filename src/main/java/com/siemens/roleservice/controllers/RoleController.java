package com.siemens.roleservice.controllers;

import com.siemens.roleservice.domain.dtos.RoleDto;
import com.siemens.roleservice.domain.entities.RoleEntity;
import com.siemens.roleservice.mappers.Mapper;
import com.siemens.roleservice.services.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoleController {

    private final RoleService roleService;
    private final Mapper<RoleEntity, RoleDto> roleMapper;

    public RoleController(RoleService roleService, Mapper<RoleEntity, RoleDto> roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @GetMapping("/roles")
    public List<RoleDto> getRoles() {
        List<RoleEntity> roles = roleService.findAll();
        return roles.stream()
                .map(roleMapper::mapTo)
                .collect(Collectors.toList());
    }
}
