package com.siemens.roleservice.services;

import com.siemens.roleservice.domain.entities.PermissionEntity;
import com.siemens.roleservice.domain.entities.RoleEntity;
import com.siemens.roleservice.repositories.RoleRepository;
import com.siemens.roleservice.services.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Test
    void testFindAll() {
        RoleEntity roleEntity1 = RoleEntity.builder().id("123abc").name("user").build();
        RoleEntity roleEntity2 = RoleEntity.builder().id("789xyz").name("administrator").build();
        when(roleRepository.findAll()).thenReturn(List.of(roleEntity1, roleEntity2));

        List<RoleEntity> roles = roleService.findAll();
        assertEquals(roles.size(), 2);
        assertEquals(roles.get(0), roleEntity1);
        assertEquals(roles.get(1), roleEntity2);
    }

    @Test
    void testSetPermissionsForRole() {
        String roleId = "789xyz";
        String roleName = "administrator";

        RoleEntity roleEntityExistingInDB = RoleEntity.builder().id(roleId).name(roleName).build();
        when(roleRepository.findById(roleId)).thenReturn(Optional.ofNullable(roleEntityExistingInDB));

        String permissionId = "per1";

        PermissionEntity permissionToSetForRole = PermissionEntity.builder().id(permissionId).build();
        RoleEntity roleEntityWithPermissionsToSave = RoleEntity.builder().id(roleId).name(roleName).permissions(Set.of(permissionToSetForRole)).build();
        PermissionEntity permissionAfterSave = PermissionEntity.builder().id(permissionId).name("delete").build();
        RoleEntity roleEntityAfterSave = RoleEntity.builder().id(roleId).name(roleName).permissions(Set.of(permissionAfterSave)).build();
        when(roleRepository.save(roleEntityWithPermissionsToSave)).thenReturn(roleEntityAfterSave);

        RoleEntity roleEntityWithPermissionsToSet = RoleEntity.builder().id(roleId).permissions(Set.of(permissionToSetForRole)).build();
        RoleEntity updatedRoleEntity = roleService.setPermissionsForRole(roleId, roleEntityWithPermissionsToSet);
        assertEquals(updatedRoleEntity, roleEntityAfterSave);
    }

    @Test
    void testIsExist() {
        String roleId = "role123";
        when(roleRepository.existsById(roleId)).thenReturn(true);

        boolean isRoleExist = roleService.isExist(roleId);
        assertTrue(isRoleExist);
    }
}
