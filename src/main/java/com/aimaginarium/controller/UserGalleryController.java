package com.aimaginarium.controller;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.service.gallery.UserGalleryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.aimaginarium.utils.EndpointUris.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ROOT_GALLERY)
@Tag(name = "User gallery")
public class UserGalleryController {
    private final UserGalleryService galleryService;

    @Operation(
            summary = "Get user gallery by id",
            description = "This method retrieves a user gallery by its id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "gallery not found")
            })
    @GetMapping(GET_GALLERY)
    @ResponseStatus(HttpStatus.OK)
    public UserGalleryDto getUserGallery(@PathVariable final Long id) {
        return galleryService.getGalleryById(id);
    }

    @Operation(
            summary = "Get user gallery by user id",
            description = "This method retrieves a user gallery by its user id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "user not found")
            })
    @GetMapping(GET_GALLERY_BY_USER)
    @ResponseStatus(HttpStatus.OK)
    public UserGalleryDto getUserGalleryByUser(@PathVariable final Long userId) {
        return galleryService.getGalleryByUserId(userId);
    }

    @Operation(
            summary = "Update user gallery title",
            description = "This method updates a user gallery title.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "gallery not found")
            })
    @PatchMapping(UPDATE_GALLERY)
    @ResponseStatus(HttpStatus.OK)
    public UserGalleryDto updateGalleryTitle(@PathVariable final Long id,
                                             @RequestParam final String title) {
        return galleryService.updateGalleryTitle(id, title);
    }

}
