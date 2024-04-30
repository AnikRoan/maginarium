package com.aimaginarium.controller;

import com.aimaginarium.dto.ChangePasswordDto;
import com.aimaginarium.dto.UserDto;
import com.aimaginarium.dto.UserProfileDto;
import com.aimaginarium.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.aimaginarium.utils.EndpointUris.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ROOT_USER)
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get user by id",
            description = "This method retrieves a user by its id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "user not found")
            })

    @GetMapping(GET_USER)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable final Long id) {
        return userService.findUserById(id);
    }

    @Operation(
            summary = "Get user profile by id",
            description = "This method retrieves a user profile by its id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "user not found")
            })
    @GetMapping(GET_PROFILE)
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto getUserProfileById(@PathVariable final Long id) {
        return userService.findUserProfileByUserId(id);
    }

    @Operation(
            summary = "Delete user by id",
            description = "This method deletes a user by its id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "user not found")
            })
    @DeleteMapping(DELETE_USER)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable final Long id) {
        userService.deleteUserById(id);
    }

    @Operation(
            summary = "Change user email",
            description = "This method changes a user email by its id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "user not found")
            })
    @PutMapping(CHANGE_EMAIL)
    @ResponseStatus(HttpStatus.OK)
    public String changeUserEmail(@PathVariable final Long id, final String email) {
        userService.changeUserEmail(id, email);
        return email;
    }

    @Operation(
            summary = "Change user phone number",
            description = "This method changes a user phone number by its id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "user not found")
            })
    @PutMapping(CHANGE_NUMBER)
    @ResponseStatus(HttpStatus.OK)
    public String changeUserPhoneNumber(@PathVariable final Long id, final String phoneNumber) {
        userService.changeUserPhoneNumber(id, phoneNumber);
        return phoneNumber;
    }

    @Operation(
            summary = "Change user username",
            description = "This method changes a user username by its id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "user not found")
            })
    @PutMapping(CHANGE_USERNAME)
    @ResponseStatus(HttpStatus.OK)
    public String changeUsername(@PathVariable final Long id, final String username) {
        userService.changeUsername(id, username);
        return username;
    }

    @Operation(
            summary = "Change user password",
            description = "This method changes a user password by its id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "user not found")
            })
    @PutMapping(CHANGE_PASSWORD)
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@PathVariable final Long id,
                               @RequestBody ChangePasswordDto passportDto) {
        userService.changePassword(id, passportDto);

    }
}
