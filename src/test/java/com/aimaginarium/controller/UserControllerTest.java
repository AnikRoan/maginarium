package com.aimaginarium.controller;

import com.aimaginarium.dto.ChangePasswordDto;
import com.aimaginarium.dto.UserDto;
import com.aimaginarium.dto.UserProfileDto;
import com.aimaginarium.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @SneakyThrows
    void getUserById() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("test@example.com");
        given(userService.findUserById(1L)).willReturn(userDto);
        mockMvc.perform(get("/api/v1/users/get/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userDto.getId()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }

    @Test
    @SneakyThrows
    void getUserProfileById() {
        UserProfileDto profileDto = new UserProfileDto();
        profileDto.setId(1L);
        profileDto.setFullName("John Doe");
        given(userService.findUserProfileByUserId(1L)).willReturn(profileDto);
        mockMvc.perform(get("/api/v1/users/getProfile/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(profileDto.getId()))
                .andExpect(jsonPath("$.fullName").value(profileDto.getFullName()));
    }

    @Test
    @SneakyThrows
    void deleteUserById() {
        mockMvc.perform(delete("/api/v1/users/delete/{id}", 1))
                .andExpect(status().isOk());

        verify(userService).deleteUserById(1L);
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "testuser", password = "password", roles = "USER")
    void changeUserEmail() {
        String newEmail = "new@example.com";
        mockMvc.perform(put("/api/v1/users/changeEmail/{id}", 1).param("email", newEmail))
                .andExpect(status().isOk());

        verify(userService).changeUserEmail(1L, newEmail);
    }

    @Test
    @SneakyThrows
    void changeUserPhoneNumber() {
        String newPhoneNumber = "6547382910";
        mockMvc.perform(put("/api/v1/users/changePhoneNumber/{id}", 1).param("phoneNumber", newPhoneNumber))
                .andExpect(status().isOk());

        verify(userService).changeUserPhoneNumber(1L, newPhoneNumber);
    }

    @Test
    @SneakyThrows
    void changeUsername() {
        String newUsername = "Jane Doe";
        mockMvc.perform(put("/api/v1/users/changeUsername/{id}", 1).param("username", newUsername))
                .andExpect(status().isOk());

        verify(userService).changeUsername(1L, newUsername);
    }

    @Test
    @SneakyThrows
    void changePassword() {
        ChangePasswordDto passwordDto = new ChangePasswordDto("oldPassword", "newPassword");
        mockMvc.perform(put("/api/v1/users/changePassword/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passwordDto)))
                .andExpect(status().isOk());
        verify(userService).changePassword(1L, passwordDto);
    }
}