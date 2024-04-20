package com.aimaginarium.service.gallery.data;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.exception.GalleryExistsException;
import com.aimaginarium.exception.GalleryNotFoundException;
import com.aimaginarium.mapper.UserGalleryMapper;
import com.aimaginarium.mapper.UserMapper;
import com.aimaginarium.model.User;
import com.aimaginarium.model.UserGallery;
import com.aimaginarium.repository.UserGalleryRepository;
import com.aimaginarium.service.gallery.UserGalleryService;
import com.aimaginarium.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.aimaginarium.exception.ErrorMessage.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserGalleryServiceImpl implements UserGalleryService {

    private final UserGalleryRepository galleryRepository;
    private final UserGalleryMapper galleryMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserGalleryDto saveGallery(final UserGalleryDto userGalleryDto, final Long userId) {
        User user = userMapper.toEntity(userService.findUserById(userId));
        UserGallery gallery = galleryMapper.toEntity(userGalleryDto);
        if (user.getUserGallery() != null) {
            throw new GalleryExistsException(GALLERY_ALREADY_EXIST, userId);
        }
        gallery.setCreatedAt(LocalDateTime.now());
        gallery.setUser(user);
        return galleryMapper.toDto(galleryRepository.save(gallery));
    }

    @Override
    public UserGalleryDto updateGalleryTitle(final Long id, final String title) {
        UserGallery gallery = getGallery(id);
        gallery.setTitle(title);
        return galleryMapper.toDto(galleryRepository.save(gallery));
    }

    @Override
    public UserGalleryDto getGalleryById(final Long id) {
        return galleryMapper.toDto(getGallery(id));
    }

    @Override
    public UserGalleryDto getGalleryByUserId(final Long userId) {
        UserGallery userGallery = galleryRepository.findUserGalleryByUserId(userId)
                .orElseThrow(() -> new GalleryNotFoundException(GALLERY_NOT_FOUND_BY_USER, userId));
        return galleryMapper.toDto(userGallery);
    }

    private UserGallery getGallery(final Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new GalleryNotFoundException(GALLERY_NOT_FOUND, id));
    }
}
