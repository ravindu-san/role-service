package com.siemens.roleservice.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    private String id;
    private String name;
    @OneToMany
    private Set<PermissionEntity> permissions;
}
