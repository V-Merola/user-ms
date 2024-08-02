package com.vincenzomerola.user_ms.service;

import com.vincenzomerola.user_ms.RoleEnum.Role;
import com.vincenzomerola.user_ms.dto.UpdateUserRequest;
import com.vincenzomerola.user_ms.dto.UserDTO;
import com.vincenzomerola.user_ms.exception.UserNotFoundException;
import com.vincenzomerola.user_ms.model.User;
import com.vincenzomerola.user_ms.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUser() {
        User user = new User("John Doe", "john.doe@example.com", Role.ADMIN);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.getUser(1L);

        assertEquals("John Doe", userDTO.getFullName());
    }

    @Test
    public void testUpdateUser() {
        User user = new User("John Doe", "john.doe@example.com", Role.ADMIN);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UpdateUserRequest updateUserRequest = new UpdateUserRequest("John Updated", "john.updated@example.com");
        UserDTO updatedUser = userService.updateUser(1L, updateUserRequest);

        assertEquals("John Updated", updatedUser.getFullName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User("John Doe", "john.doe@example.com", Role.ADMIN);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> userService.deleteUser(1L));
    }
}
