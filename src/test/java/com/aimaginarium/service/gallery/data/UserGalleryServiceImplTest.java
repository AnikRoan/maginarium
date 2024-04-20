package com.aimaginarium.service.gallery.data;

import com.aimaginarium.dto.UserDto;
import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.mapper.UserGalleryMapper;
import com.aimaginarium.mapper.UserMapper;
import com.aimaginarium.model.User;
import com.aimaginarium.model.UserGallery;
import com.aimaginarium.repository.UserGalleryRepository;
import com.aimaginarium.service.user.UserService;
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
class UserGalleryServiceImplTest {

    @Mock
    private UserGalleryRepository galleryRepository;
    @Mock
    private UserGalleryMapper galleryMapper;
    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserGalleryServiceImpl userGalleryService;

    private final Long ID = 1L;
    private final String title = "title";
    private User user;
    private UserDto userDto;
    private UserGallery gallery;
    private UserGalleryDto galleryDto;

    @BeforeEach
    void init() {

        user = User.builder()
                .id(ID)
                .email("email")
                .password("pass")
                .build();

        userDto = UserDto.builder()
                .id(ID)
                .email("email")
                .password("pass")
                .build();

        gallery = UserGallery.builder()
                .id(ID)
                .title(title)
                .createdAt(LocalDateTime.now())
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
        when(userService.findUserById(anyLong())).thenReturn(userDto);
        when(userMapper.toEntity(any(UserDto.class))).thenReturn(user);
        when(galleryMapper.toEntity(any(UserGalleryDto.class))).thenReturn(gallery);
        when(galleryRepository.save(any())).thenReturn(gallery);
        when(galleryMapper.toDto(any(UserGallery.class))).thenReturn(galleryDto);

        UserGalleryDto savedUserGalleryDto = userGalleryService.saveGallery(galleryDto, ID);

        assertNotNull(savedUserGalleryDto);
        assertEquals(galleryDto.title(), savedUserGalleryDto.title());
    }

    @Test
    @DisplayName("Update Gallery Title Test")
    void updateUserGalleryTitleTest() {
        when(galleryRepository.findById(anyLong())).thenReturn(Optional.of(gallery));
        when(galleryRepository.save(any())).thenReturn(gallery);
        when(galleryMapper.toDto(any(UserGallery.class))).thenReturn(galleryDto);

        UserGalleryDto udatedUserGalleryDto = userGalleryService.updateGalleryTitle(ID, title);

        assertNotNull(udatedUserGalleryDto);
        assertEquals(ID, udatedUserGalleryDto.id());
        assertEquals(title, udatedUserGalleryDto.title());
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
        when(galleryRepository.findUserGalleryByUserId(anyLong())).thenReturn(Optional.of(gallery));
        when(galleryRepository.findUserGalleryByUserId(null)).thenThrow(EntityNotFoundException.class);
        when(galleryMapper.toDto(any(UserGallery.class))).thenReturn(galleryDto);

        UserGalleryDto recievedUserGalleryDto = userGalleryService.getGalleryByUserId(ID);

        assertNotNull(recievedUserGalleryDto);
        assertEquals(galleryDto.id(), recievedUserGalleryDto.id());
        assertEquals(galleryDto.title(), recievedUserGalleryDto.title());
        assertEquals(galleryDto.createdAt(), recievedUserGalleryDto.createdAt());
        assertThrows(EntityNotFoundException.class, () -> userGalleryService.getGalleryByUserId(null));
    }

}
