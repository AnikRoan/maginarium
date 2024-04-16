package com.aimaginarium.service.data;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.mapper.PictureDetailsMapper;
import com.aimaginarium.model.Picture;
import com.aimaginarium.model.PictureDetails;
import com.aimaginarium.repository.PictureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PictureDetailServiceImplTest {
    private static final Long id = 1L;
    @InjectMocks
    private PictureDetailServiceImpl pictureService;
    @Mock
    private PictureRepository mockPictureRepository;
    @Mock
    private PictureDetailsMapper mockPictureDetailsMapper;
    @Mock
    private Picture mockPicture;
    @Mock
    private PictureDetails mockPictureDetails;
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
        pictureDetailsDto.setWidth(1);
        pictureDetailsDto.setStyles("styles");
    }

    @Test
    void updateDetails() {
        when(mockPictureRepository.findById(id)).thenReturn(Optional.of(mockPicture));
        when(mockPictureDetailsMapper.toEntity(pictureDetailsDto)).thenReturn(mockPictureDetails);

        pictureService.updateDetails(pictureDetailsDto, id);

        verify(mockPictureRepository).findById(id);
        verify(mockPictureDetailsMapper).toEntity(pictureDetailsDto);
        verify(mockPictureRepository).save(mockPicture);
    }
}