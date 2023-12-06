package com.siemens.roleservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.siemens.roleservice.domain.entities.PermissionEntity;
import com.siemens.roleservice.repositories.PermissionRepository;
import com.siemens.roleservice.services.impl.PermissionServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceTest {
  @InjectMocks private PermissionServiceImpl permissionService;

  @Mock private PermissionRepository permissionRepository;

  @Test
  void testFindAll() {
    PermissionEntity permissionEntity1 =
        PermissionEntity.builder().id("123abc").name("read permission").build();
    PermissionEntity permissionEntity2 =
        PermissionEntity.builder().id("789xyz").name("write permission").build();
    when(permissionRepository.findAll()).thenReturn(List.of(permissionEntity1, permissionEntity2));

    List<PermissionEntity> permissions = permissionService.findAll();
    assertEquals(permissions.size(), 2);
    assertEquals(permissions.get(0), permissionEntity1);
    assertEquals(permissions.get(1), permissionEntity2);
  }
}
