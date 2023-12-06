package com.siemens.roleservice.controllers;

import com.siemens.roleservice.domain.dtos.RoleDto;
import com.siemens.roleservice.domain.entities.RoleEntity;
import com.siemens.roleservice.mappers.Mapper;
import com.siemens.roleservice.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/roles/{id}")
    public ResponseEntity<RoleDto> setPermissionsForRole(@PathVariable("id") String roleId, @RequestBody RoleDto roleDto) {
        if (!roleService.isExist(roleId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RoleEntity roleEntity = roleMapper.mapFrom(roleDto);
        RoleEntity updatedRole = roleService.setPermissionsForRole(roleId, roleEntity);
        return new ResponseEntity<>(roleMapper.mapTo(updatedRole), HttpStatus.OK);
    }
}
