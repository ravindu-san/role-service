package com.siemens.roleservice.mappers.impl;

import com.siemens.roleservice.domain.dtos.PermissionDto;
import com.siemens.roleservice.domain.entities.PermissionEntity;
import com.siemens.roleservice.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapperImpl implements Mapper<PermissionEntity, PermissionDto> {
  private ModelMapper modelMapper;

  public PermissionMapperImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public PermissionDto mapTo(PermissionEntity permissionEntity) {
    return modelMapper.map(permissionEntity, PermissionDto.class);
  }

  @Override
  public PermissionEntity mapFrom(PermissionDto permissionDto) {
    return modelMapper.map(permissionDto, PermissionEntity.class);
  }
}
