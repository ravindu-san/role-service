package com.siemens.roleservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.roleservice.domain.dtos.PermissionDto;
import com.siemens.roleservice.domain.dtos.RoleDto;
import com.siemens.roleservice.domain.entities.PermissionEntity;
import com.siemens.roleservice.domain.entities.RoleEntity;
import com.siemens.roleservice.repositories.PermissionRepository;
import com.siemens.roleservice.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class RoleControllerTest {
    private MockMvc mockMvc;
    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public RoleControllerTest(MockMvc mockMvc, RoleRepository roleRepository, PermissionRepository permissionRepository, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testGetRolesReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/roles")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetRolesReturnsListOfRoles() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/roles")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect((MockMvcResultMatchers.jsonPath("$[0].id").isString())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("9faaf9ba-464e-4c68-a901-630fc4de123b")
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("User"));
    }

    @Test
    public void testSetPermissionsForRoleReturnsHttpStatus200() throws Exception {
        String roleId = "role123";
        RoleEntity roleEntity = RoleEntity.builder().id(roleId).name("Viewer").build();
        RoleEntity existingRole = roleRepository.save(roleEntity);

        String permissionId = "per123";
        PermissionEntity permissionEntity = PermissionEntity.builder().id(permissionId).name("View").build();
        PermissionEntity existingPermission = permissionRepository.save(permissionEntity);

        PermissionDto permission = PermissionDto.builder().id(permissionId).build();
        Set<PermissionDto> rolePermissions = Set.of(permission);
        RoleDto roleDto = RoleDto.builder().id(roleId).permissions(rolePermissions).build();
        String roleDtoJson = objectMapper.writeValueAsString(roleDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/roles/" + roleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(roleDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSetPermissionsForRoleReturnsUpdatedRole() throws Exception {
        String roleId = "role123";
        RoleEntity roleEntity = RoleEntity.builder().id(roleId).name("Viewer").build();
        RoleEntity existingRole = roleRepository.save(roleEntity);

        String permissionId = "per123";
        PermissionEntity permissionEntity = PermissionEntity.builder().id(permissionId).name("View").build();
        PermissionEntity existingPermission = permissionRepository.save(permissionEntity);

        PermissionDto permission = PermissionDto.builder().id(permissionId).build();
        Set<PermissionDto> rolePermissions = Set.of(permission);
        RoleDto roleDto = RoleDto.builder().id(roleId).permissions(rolePermissions).build();
        String roleDtoJson = objectMapper.writeValueAsString(roleDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/roles/" + roleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(roleDtoJson)
        ).andExpect((MockMvcResultMatchers.jsonPath("$.id").isString())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Viewer")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.permissions[0].id").value(permissionId));
    }
}
