package com.aimaginarium.service.data;


import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.mapper.PictureDetailsMapper;
import com.aimaginarium.mapper.PictureMapper;
import com.aimaginarium.model.Picture;
import com.aimaginarium.model.PictureDetails;
import com.aimaginarium.repository.PictureDetailsRepository;
import com.aimaginarium.repository.PictureRepository;
import com.aimaginarium.service.picture.data.PictureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PictureServiceImplTest {
    private static final Long id = 1L;
    @InjectMocks
    private PictureServiceImpl pictureServiceImpl;
    @Mock
    private PictureRepository mockPictureRepository;
    @Mock
    private PictureDetailsRepository mockPictureDetailsRepository;
    @Mock
    private PictureMapper mockPictureMapper;
    @Mock
    private PictureDetailsMapper mockPictureDetailsMapper;
    @Mock
    private Picture mockPicture;
    @Mock
    private PictureDetails mockPictureDetails;
    @Mock
    private List<Picture> mockListPictures;

    private PictureDto pictureDto;
    private PictureDetailsDto pictureDetailsDto;

    @BeforeEach
    public void init() {
        pictureDto = new PictureDto();
        pictureDto.setId(id);
        pictureDto.setS3Link("s3Link");
        pictureDto.setPrivateFlag(true);
        pictureDto.setDeletedFlag(true);

        pictureDetailsDto = new PictureDetailsDto();
        pictureDetailsDto.setId(id);
        pictureDetailsDto.setTitle("title");
        pictureDetailsDto.setPrompt("prompt");
        pictureDetailsDto.setWidth(100);
        pictureDetailsDto.setStyles("styles");

    }


    @Test
    void getPictureByIdTest() {
        when(mockPictureRepository.findById(anyLong())).thenReturn(Optional.of(mockPicture));
        when(mockPictureDetailsRepository.findById(anyLong())).thenReturn(Optional.of(mockPictureDetails));
        when(mockPictureMapper.toDto(mockPicture)).thenReturn(pictureDto);
        when(mockPictureDetailsMapper.toDto(mockPictureDetails)).thenReturn(pictureDetailsDto);

        PictureDto result = pictureServiceImpl.getPictureById(id);

        verify(mockPictureRepository).findById(id);
        verify(mockPictureMapper).toDto(mockPicture);

        assertEquals(pictureDto, result);
    }

    @Test
    void pictureNotFoundTest() {
        when(mockPictureRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            pictureServiceImpl.getPictureById(id);
        });
    }

    @Test
    void getAllPicturesTest() {
        List<PictureDto> dtos = mockListPictures.stream()
                .map(mockPictureMapper::toDto)
                .collect(Collectors.toList());

        when(mockPictureRepository.findAll()).thenReturn(mockListPictures);
        when(mockPictureMapper.toDtos(mockListPictures)).thenReturn(dtos);

        List<PictureDto> result = pictureServiceImpl.getAllPictures();

        verify(mockPictureRepository).findAll();
        verify(mockPictureMapper).toDtos(mockListPictures);

        assertNotNull(result);
        assertEquals(dtos.size(), result.size());
        assertEquals(dtos, result);

    }

    @Test
    void updatePictureTest() {
        when(mockPictureRepository.findById(id)).thenReturn(Optional.of(mockPicture));

        pictureServiceImpl.updatePicture(pictureDto);

        verify(mockPictureRepository).findById(id);
        verify(mockPicture).setS3Link(pictureDto.getS3Link());
        verify(mockPicture).setPrivateFlag(pictureDto.isPrivateFlag());
        verify(mockPicture).setDeletedFlag(pictureDto.isDeletedFlag());
    }


    @Test
    void savePictureTest() {
        when(mockPictureMapper.toEntity(pictureDto)).thenReturn(mockPicture);
        when(mockPictureDetailsMapper.toEntity(pictureDetailsDto)).thenReturn(mockPictureDetails);

        pictureDto.setPictureDetailsDto(pictureDetailsDto);
        pictureServiceImpl.savePicture(pictureDto);

        verify(mockPictureMapper).toEntity(pictureDto);
        verify(mockPictureDetailsMapper).toEntity(pictureDetailsDto);
        verify(mockPicture).setPictureDetails(mockPictureDetails);
        verify(mockPictureDetails).setPicture(mockPicture);


    }

    @Test
    void deletePictureTest() {
        when(mockPictureRepository.findById(id)).thenReturn(Optional.of(mockPicture));

        pictureServiceImpl.getPictureById(id);
        pictureServiceImpl.deletePicture(id);

        verify(mockPictureRepository).findById(id);
        verify(mockPictureRepository).deleteById(id);
    }
}