package com.aimaginarium.controller;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.service.gallery.UserGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.aimaginarium.utils.EndpointUris.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ROOT_GALLERY)
public class UserGalleryController {

    private final UserGalleryService galleryService;

    @GetMapping(GET_GALLERY)
    @ResponseStatus(HttpStatus.OK)
    public UserGalleryDto getUserGallery(@PathVariable final Long id) {
        return galleryService.getGalleryById(id);
    }

    @GetMapping(GET_GALLERY_BY_USER)
    @ResponseStatus(HttpStatus.OK)
    public UserGalleryDto getUserGalleryByUser(@PathVariable final Long userId) {
        return galleryService.getGalleryByUserId(userId);
    }

    @PatchMapping(UPDATE_GALLERY)
    @ResponseStatus(HttpStatus.OK)
    public UserGalleryDto updateGalleryTitle(@PathVariable final Long id,
                                             @RequestParam final String title) {
        return galleryService.updateGalleryTitle(id, title);
    }

}
