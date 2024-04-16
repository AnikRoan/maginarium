package com.aimaginarium.controller;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.dto.UserGalleryUpdateDto;
import com.aimaginarium.service.UserGalleryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/gallery")
public class UserGalleryController {

    private final UserGalleryService galleryService;

    @GetMapping("/{id}")
    public UserGalleryDto getUserGallery(@PathVariable Long id) {
        return galleryService.getGalleryById(id);
    }

    @GetMapping("/user/{userId}")
    public UserGalleryDto getUserGalleryByUser(@PathVariable Long userId) {
        return galleryService.getGalleryByUserId(userId);
    }

    @PutMapping
    public UserGalleryDto updateGalleryTitle(@RequestBody @Valid UserGalleryUpdateDto userGalleryDto) {
        return galleryService.updateGalleryTitle(userGalleryDto);
    }

}
