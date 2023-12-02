package com.siemens.roleservice.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permission")
public class PermissionEntity {
    @Id
    private String id;
    private String name;
}
