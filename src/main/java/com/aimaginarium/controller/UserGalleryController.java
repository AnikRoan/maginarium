package com.aimaginarium.controller;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.service.gallery.UserGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.aimaginarium.utils.EndpointUris.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ROOT_GALLERY)
public class UserGalleryController {

    private final UserGalleryService galleryService;

    @GetMapping(GET_GALLERY)
    public UserGalleryDto getUserGallery(@PathVariable final Long id) {
        return galleryService.getGalleryById(id);
    }

    @GetMapping(GET_GALLERY_BY_USER)
    public UserGalleryDto getUserGalleryByUser(@PathVariable final Long userId) {
        return galleryService.getGalleryByUserId(userId);
    }

    @PutMapping(UPDATE_GALLERY)
    public UserGalleryDto updateGalleryTitle(@PathVariable final Long id,
                                             @RequestParam final String title) {
        return galleryService.updateGalleryTitle(id, title);
    }

}
