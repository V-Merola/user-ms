package com.vincenzomerola.user_ms.controller;

import com.vincenzomerola.user_ms.RoleEnum.Role;
import com.vincenzomerola.user_ms.dto.UpdateUserRequest;
import com.vincenzomerola.user_ms.dto.UserDTO;
import com.vincenzomerola.user_ms.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetUserById() throws Exception {
        UserDTO user = new UserDTO(1L, "John Doe", "john.doe@example.com", Role.ADMIN);

        when(userService.getUser(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateUser() throws Exception {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("John Doe", "john.doe@example.com");
        UserDTO updatedUser = new UserDTO(1L, "John Doe", "john.doe@example.com", Role.ADMIN);

        when(userService.updateUser(any(Long.class), any(UpdateUserRequest.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullName\": \"John Doe\", \"email\": \"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
