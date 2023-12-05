package com.siemens.roleservice.domain.dtos;

import com.siemens.roleservice.domain.entities.PermissionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDto {
    private String id;
    private String name;
    private Set<PermissionEntity> permissions;
}
