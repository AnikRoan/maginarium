package com.aimaginarium.service.user.data;

import com.aimaginarium.dto.ChangePasswordDto;
import com.aimaginarium.dto.UserDto;
import com.aimaginarium.dto.UserProfileDto;
import com.aimaginarium.exception.ErrorMessage;
import com.aimaginarium.exception.InvalidPasswordException;
import com.aimaginarium.exception.UserNotFoundException;
import com.aimaginarium.mapper.UserMapper;
import com.aimaginarium.mapper.UserProfileMapper;
import com.aimaginarium.model.User;
import com.aimaginarium.model.UserGallery;
import com.aimaginarium.model.UserProfile;
import com.aimaginarium.repository.UserGalleryRepository;
import com.aimaginarium.repository.UserProfileRepository;
import com.aimaginarium.repository.UserRepository;
import com.aimaginarium.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserGalleryRepository userGalleryRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(final UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        // will use gallery service
        setUserGallery(user);
        setUserProfile(user);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    private void setUserGallery(final User user) {
        UserGallery userGallery = UserGallery.builder()
                .user(user)
                .title(user.getEmail())
                .createdAt(LocalDateTime.now())
                .pictures(Collections.emptyList())
                .build();
        user.setUserGallery(userGallery);
        userGalleryRepository.save(userGallery);
    }

    private void setUserProfile(final User user) {
        UserProfile userProfile = user.getUserProfile();
        userProfile.setCreatedAt(LocalDateTime.now());
        userProfile.setUser(user);
        userProfileRepository.save(userProfile);
    }

    @Override
    public UserDto findUserById(final Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(format(ErrorMessage.USER_NOT_FOUND, id))));
    }

    @Override
    public UserProfileDto findUserProfileByUserId(final Long userId) {
        return userProfileMapper.toDto(userProfileRepository
                .findUserProfileByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(format(ErrorMessage.USER_DETAILS_NOT_FOUND, userId))));
    }

    @Override
    public void deleteUserById(final Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changePassword(Long userId, ChangePasswordDto passwordDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(format(ErrorMessage.USER_DETAILS_NOT_FOUND, userId)));
        if (!passwordEncoder.matches(passwordDto.oldPassword(), user.getPassword())) {
            throw new InvalidPasswordException(ErrorMessage.INVALID_PASSWORD);
        }
        String hashedPassword = passwordEncoder.encode(passwordDto.newPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public void changeUserEmail(final Long userId, final String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(format(ErrorMessage.USER_DETAILS_NOT_FOUND, userId)));
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void changeUserPhoneNumber(final Long userId, final String phoneNumber) {
        UserProfile userProfile = userProfileRepository.findUserProfileByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(format(ErrorMessage.USER_DETAILS_NOT_FOUND, userId)));
        userProfile.setPhoneNumber(phoneNumber);
        userProfileRepository.save(userProfile);
    }

    @Override
    public void changeUsername(final Long userId, final String username) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(format(ErrorMessage.USER_DETAILS_NOT_FOUND, userId)));
        UserProfile userProfile = user.getUserProfile();
        userProfile.setFullName(username);
        userRepository.save(user);
    }

    @Override
    public void lockUser(final Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(format(ErrorMessage.USER_DETAILS_NOT_FOUND, userId)));
        user.setIsLock(true);
        userRepository.save(user);
    }

    @Override
    public void unlockUserById(final Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(format(ErrorMessage.USER_DETAILS_NOT_FOUND, userId)));
        user.setIsLock(false);
        userRepository.save(user);
    }
}
