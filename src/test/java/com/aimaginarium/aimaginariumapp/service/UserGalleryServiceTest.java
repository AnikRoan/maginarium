package com.aimaginarium.aimaginariumapp.service;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.dto.UserGalleryRequestDto;
import com.aimaginarium.dto.UserGalleryUpdateDto;
import com.aimaginarium.mapper.UserGalleryMapper;
import com.aimaginarium.mapper.UserGalleryRequestMapper;
import com.aimaginarium.model.User;
import com.aimaginarium.model.UserGallery;
import com.aimaginarium.repository.UserGalleryRepository;
import com.aimaginarium.repository.UserRepository;
import com.aimaginarium.service.data.UserGalleryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserGallery Service Tests")
class UserGalleryServiceTest {

    @Mock
    private UserGalleryRepository galleryRepository;
    @Mock
    private UserGalleryMapper galleryMapper;
    @Mock
    private UserGalleryRequestMapper galleryRequestMapper;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserGalleryServiceImpl userGalleryService;

    private final Long ID = 1L;
    private User user;
    private UserGallery gallery;
    private UserGalleryRequestDto galleryRequestDto;
    private UserGalleryUpdateDto galleryUpdateDto;
    private UserGalleryDto galleryDto;

    @BeforeEach
    void init() {
        String title = "title";

        user = User.builder()
                .id(ID)
                .email("email")
                .password("pass")
                .build();

        gallery = UserGallery.builder()
                .id(ID)
                .title(title)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        galleryRequestDto = UserGalleryRequestDto.builder()
                .user(user)
                .title(title)
                .build();

        galleryUpdateDto = UserGalleryUpdateDto.builder()
                .id(ID)
                .title(title)
                .build();

        galleryDto = UserGalleryDto.builder()
                .id(ID)
                .title(title)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("Save Gallery Test")
    void saveUserGalleryTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(galleryRequestMapper.toEntity(any(UserGalleryRequestDto.class))).thenReturn(gallery);
        when(galleryRepository.save(any())).thenReturn(gallery);
        when(galleryMapper.toDto(any(UserGallery.class))).thenReturn(galleryDto);

        UserGalleryDto savedUserGalleryDto = userGalleryService.saveGallery(galleryRequestDto, ID);

        assertNotNull(savedUserGalleryDto);
        assertEquals(galleryRequestDto.title(), savedUserGalleryDto.title());
    }

    @Test
    @DisplayName("Update Gallery Title Test")
    void updateUserGalleryTitleTest() {
        when(galleryRepository.findById(anyLong())).thenReturn(Optional.of(gallery));
        when(galleryRepository.save(any())).thenReturn(gallery);
        when(galleryMapper.toDto(any(UserGallery.class))).thenReturn(galleryDto);

        UserGalleryDto udatedUserGalleryDto = userGalleryService.updateGalleryTitle(galleryUpdateDto);

        assertNotNull(udatedUserGalleryDto);
        assertEquals(galleryUpdateDto.title(), udatedUserGalleryDto.title());
    }

    @Test
    @DisplayName("Get Gallery By ID Test")
    void getUserGalleryByIdTest() {
        when(galleryRepository.findById(anyLong())).thenReturn(Optional.of(gallery));
        when(galleryRepository.findById(null)).thenThrow(EntityNotFoundException.class);
        when(galleryMapper.toDto(any(UserGallery.class))).thenReturn(galleryDto);

        UserGalleryDto recievedUserGalleryDto = userGalleryService.getGalleryById(ID);

        assertNotNull(recievedUserGalleryDto);
        assertEquals(galleryDto.id(), recievedUserGalleryDto.id());
        assertEquals(galleryDto.title(), recievedUserGalleryDto.title());
        assertEquals(galleryDto.createdAt(), recievedUserGalleryDto.createdAt());
        assertThrows(EntityNotFoundException.class, () -> userGalleryService.getGalleryById(null));
    }


    @Test
    @DisplayName("Get Gallery By User ID Test")
    void getUserGalleryByUserIdTest() {
        when(galleryRepository.findUserGalleryByUser_Id(anyLong())).thenReturn(Optional.of(gallery));
        when(galleryRepository.findUserGalleryByUser_Id(null)).thenThrow(EntityNotFoundException.class);
        when(galleryMapper.toDto(any(UserGallery.class))).thenReturn(galleryDto);

        UserGalleryDto recievedUserGalleryDto = userGalleryService.getGalleryByUserId(ID);

        assertNotNull(recievedUserGalleryDto);
        assertEquals(galleryDto.id(), recievedUserGalleryDto.id());
        assertEquals(galleryDto.title(), recievedUserGalleryDto.title());
        assertEquals(galleryDto.createdAt(), recievedUserGalleryDto.createdAt());
        assertThrows(EntityNotFoundException.class, () -> userGalleryService.getGalleryByUserId(null));
    }

}
