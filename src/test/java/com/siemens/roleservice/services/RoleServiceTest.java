package com.siemens.roleservice.services;

import com.siemens.roleservice.domain.entities.RoleEntity;
import com.siemens.roleservice.repositories.RoleRepository;
import com.siemens.roleservice.services.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
