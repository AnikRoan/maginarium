package com.aimaginarium.service.user.data;

import com.aimaginarium.dto.UserDto;
import com.aimaginarium.dto.UserProfileDto;
import com.aimaginarium.exception.UserNotFoundException;
import com.aimaginarium.mapper.UserMapper;
import com.aimaginarium.mapper.UserProfileMapper;
import com.aimaginarium.model.User;
import com.aimaginarium.repository.UserGalleryRepository;
import com.aimaginarium.repository.UserProfileRepository;
import com.aimaginarium.repository.UserRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserGalleryRepository userGalleryRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    private UserDto savedUserDto;

    @BeforeEach
    public void setUp() throws Exception {
        UserDto userDto = getUserDto();
        savedUserDto = userService.saveUser(userDto);
    }

    @Test
    public void testSaveUser() {
        assertNotNull(savedUserDto.getId());
        assertEquals("test", savedUserDto.getEmail());
        User savedUser = userRepository.findById(savedUserDto.getId()).orElse(null);
        assertNotNull(savedUser);
        assertEquals("test", savedUser.getEmail());
    }


    @Test
    void findUserById() {
        UserDto user = userService.findUserById(savedUserDto.getId());
        assertEquals("test", user.getEmail());
    }

    @Test
    void findUserProfileByUserId() {
        UserProfileDto profile = userService.findUserProfileByUserId(savedUserDto.getId());
        assertEquals("1234567890", profile.getPhoneNumber());
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(savedUserDto.getId());
        assertThrows(UserNotFoundException.class, () -> userService.findUserById(savedUserDto.getId()));
    }

    @Test
    void changeUserEmail() {
        userService.changeUserEmail(savedUserDto.getId(), "test3");
        UserDto user = userService.findUserById(savedUserDto.getId());
        assertEquals("test3", user.getEmail());
    }

    @Test
    void changeUserPhoneNumber() {
        userService.changeUserPhoneNumber(savedUserDto.getId(), "0987654321");
        UserDto user = userService.findUserById(savedUserDto.getId());
        assertEquals("0987654321", user.getUserProfile().getPhoneNumber());
    }

    @Test
    void changeUsername() {
        userService.changeUsername(savedUserDto.getId(), "New Name");
        UserDto user = userService.findUserById(savedUserDto.getId());
        assertEquals("New Name", user.getUserProfile().getFullName());
    }

    @Test
    void lockUser() {
        userService.lockUser(savedUserDto.getId());
        UserDto user = userService.findUserById(savedUserDto.getId());
        assertTrue(user.getIsLock());
    }

    @Test
    void unlockUserById() {
        userService.lockUser(savedUserDto.getId());
        UserDto user = userService.findUserById(savedUserDto.getId());
        assertTrue(user.getIsLock());

        userService.unlockUserById(savedUserDto.getId());
        UserDto userUnlock = userService.findUserById(savedUserDto.getId());
        assertFalse(userUnlock.getIsLock());
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setRole("U");
        userDto.setEmail("test");
        userDto.setPassword("12345");
        userDto.setIsLock(false);
        userDto.setUserProfile(UserProfileDto.builder()
                .createdAt(LocalDateTime.now())
                .login("www")
                .fullName("test")
                .phoneNumber("1234567890")
                .build());
        return userDto;
    }
}