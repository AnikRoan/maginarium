package com.aimaginarium.service.data;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.dto.UserGalleryRequestDto;
import com.aimaginarium.dto.UserGalleryUpdateDto;
import com.aimaginarium.mapper.UserGalleryMapper;
import com.aimaginarium.mapper.UserGalleryRequestMapper;
import com.aimaginarium.model.User;
import com.aimaginarium.model.UserGallery;
import com.aimaginarium.repository.UserGalleryRepository;
import com.aimaginarium.repository.UserRepository;
import com.aimaginarium.service.UserGalleryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserGalleryServiceImpl implements UserGalleryService {

    private final UserGalleryRepository galleryRepository;
    private final UserGalleryMapper galleryMapper;
    private final UserGalleryRequestMapper galleryRequestMapper;
    private final UserRepository userRepository;

    @Override
    public UserGalleryDto saveGallery(UserGalleryRequestDto userGalleryDto, Long userId) {
        // This string will be changed using UserService
        User user = userRepository.findById(userId).orElseThrow();
        UserGallery gallery = galleryRequestMapper.toEntity(userGalleryDto);
        if (user.getUserGallery() != null) {
            throw new RuntimeException("Gallery already exist by user id=" + userId);
        }
        gallery.setCreatedAt(LocalDateTime.now());
        gallery.setUser(user);
        UserGallery savedGallery = galleryRepository.save(gallery);
        return galleryMapper.toDto(savedGallery);
    }

    @Override
    public UserGalleryDto updateGalleryTitle(UserGalleryUpdateDto galleryDto) {
        UserGallery gallery = getGallery(galleryDto.id());
        gallery.setTitle(galleryDto.title());
        return galleryMapper.toDto(galleryRepository.save(gallery));
    }

    @Override
    public UserGalleryDto getGalleryById(Long id) {
        return galleryMapper.toDto(getGallery(id));
    }

    @Override
    public UserGalleryDto getGalleryByUserId(Long userId) {
        UserGallery userGallery = galleryRepository.findUserGalleryByUser_Id(userId)
                .orElseThrow(() -> new EntityNotFoundException("Gallery with user id=" + userId + " not found"));
        return galleryMapper.toDto(userGallery);
    }

    private UserGallery getGallery(Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gallery with id=" + id + " not found"));
    }
}
