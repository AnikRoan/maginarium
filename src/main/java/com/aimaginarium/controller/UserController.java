package com.aimaginarium.controller;

import com.aimaginarium.dto.ChangePasswordDto;
import com.aimaginarium.dto.UserDto;
import com.aimaginarium.dto.UserProfileDto;
import com.aimaginarium.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.aimaginarium.utils.EndpointUris.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ROOT_USER)
public class UserController {
    private final UserService userService;

    @GetMapping(GET_USER)
    public ResponseEntity<UserDto> getUserById(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping(GET_PROFILE)
    public ResponseEntity<UserProfileDto> getUserProfileById(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.findUserProfileByUserId(id));
    }

    @DeleteMapping(DELETE_USER)
    public ResponseEntity<Void> deleteUserById(@PathVariable final Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(CHANGE_EMAIL)
    public ResponseEntity<Void> changeUserEmail(@PathVariable final Long id, final String email) {
        userService.changeUserEmail(id, email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(CHANGE_NUMBER)
    public ResponseEntity<Void> changeUserPhoneNumber(@PathVariable final Long id, final String phoneNumber) {
        userService.changeUserPhoneNumber(id, phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(CHANGE_USERNAME)
    public ResponseEntity<Void> changeUsername(@PathVariable final Long id, final String username) {
        userService.changeUsername(id, username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(CHANGE_PASSWORD)
    public ResponseEntity<Void> changePassword(@PathVariable final Long id,
                                               @RequestBody ChangePasswordDto passportDto) {
        userService.changePassword(id, passportDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
