package com.siemens.roleservice.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleDto {
    private String id;
    private String name;
    private Set<PermissionDto> permissions;
}
