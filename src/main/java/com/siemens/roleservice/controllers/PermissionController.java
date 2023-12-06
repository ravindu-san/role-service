package com.siemens.roleservice.controllers;

import com.siemens.roleservice.domain.dtos.PermissionDto;
import com.siemens.roleservice.domain.entities.PermissionEntity;
import com.siemens.roleservice.mappers.Mapper;
import com.siemens.roleservice.services.PermissionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {

  private final PermissionService permissionService;
  private final Mapper<PermissionEntity, PermissionDto> permissionMapper;

  public PermissionController(
      PermissionService permissionService,
      Mapper<PermissionEntity, PermissionDto> permissionMapper) {
    this.permissionService = permissionService;
    this.permissionMapper = permissionMapper;
  }

  @GetMapping(path = "/permissions")
  public List<PermissionDto> getPermissions() {
    List<PermissionEntity> permissions = permissionService.findAll();
    return permissions.stream().map(permissionMapper::mapTo).collect(Collectors.toList());
  }
}
