package com.aimaginarium.service.user;

import com.aimaginarium.dto.ChangePasswordDto;
import com.aimaginarium.dto.UserDto;
import com.aimaginarium.dto.UserProfileDto;

public interface UserService {

    UserDto saveUser(UserDto user);

    UserDto findUserById(Long id);

    UserProfileDto findUserProfileByUserId(Long userId);

    void deleteUserById(Long id);

    void changeUserEmail(Long userId, String email);

    void changeUserPhoneNumber(Long userId, String phoneNumber);

    void changeUsername(Long userId, String username);

    void changePassword(Long userId, ChangePasswordDto passportDto);

    void lockUser(Long userId);

    void unlockUserById(Long userId);
}
