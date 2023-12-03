package com.siemens.roleservice.controllers;

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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PermissionControllerTest {
    private MockMvc mockMvc;


    @Autowired
    public PermissionControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testGetPermissionsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/permissions")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPermissionsReturnsListOfPermissions() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/permissions")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect((MockMvcResultMatchers.jsonPath("$[0].id").isString())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("706ee8e3-6034-4f27-ab20-4397ad874a09")
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Read Data"));
    }
}
