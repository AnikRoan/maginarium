package com.aimaginarium.convertors;

import com.aimaginarium.dtos.UserDto;
import com.aimaginarium.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    public UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
    public User convertToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

    public User convertUpdateToEntity(User user, UserDto userDto) {
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }
}
