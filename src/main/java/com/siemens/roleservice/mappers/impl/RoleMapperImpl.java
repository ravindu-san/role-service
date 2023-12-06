package com.siemens.roleservice.mappers.impl;

import com.siemens.roleservice.domain.dtos.RoleDto;
import com.siemens.roleservice.domain.entities.RoleEntity;
import com.siemens.roleservice.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements Mapper<RoleEntity, RoleDto> {
  private ModelMapper modelMapper;

  public RoleMapperImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public RoleDto mapTo(RoleEntity roleEntity) {
    return modelMapper.map(roleEntity, RoleDto.class);
  }

  @Override
  public RoleEntity mapFrom(RoleDto roleDto) {
    return modelMapper.map(roleDto, RoleEntity.class);
  }
}
