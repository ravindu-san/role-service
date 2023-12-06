package com.siemens.roleservice.domain.dtos;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleDto {
  private String id;
  private String name;
  private Set<PermissionDto> permissions;
}
