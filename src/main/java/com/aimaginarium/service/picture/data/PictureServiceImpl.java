package com.aimaginarium.service.picture.data;


import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.exception.ErrorMessage;
import com.aimaginarium.exception.PictureNotFoundException;
import com.aimaginarium.mapper.PictureDetailsMapper;
import com.aimaginarium.mapper.PictureMapper;

import com.aimaginarium.model.Picture;
import com.aimaginarium.model.PictureDetails;
import com.aimaginarium.repository.PictureDetailsRepository;
import com.aimaginarium.repository.PictureRepository;
import com.aimaginarium.service.picture.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.aimaginarium.service.picture.data.PictureDetailServiceImpl.setPictureHeight;


@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final PictureMapper pictureMapper;
    private final PictureRepository pictureRepository;
    private final PictureDetailsRepository pictureDetailsRepository;
    private final PictureDetailsMapper pictureDetailsMapper;

    @Override
    public void updatePicture(final PictureDto pictureDto) {
        Picture picture = pictureRepository.findById(pictureDto.getId()).orElseThrow(()
                -> new PictureNotFoundException(ErrorMessage.PICTURE_NOT_FOUND, pictureDto.getId()));
        if (pictureDto.isDeletedFlag()) {
            throw new PictureNotFoundException(ErrorMessage.PICTURE_UPDATE_EXCEPTION, pictureDto.getId());
        }
        if (Objects.nonNull(pictureDto.getS3Link())) {
            picture.setS3Link(pictureDto.getS3Link());
        }
        if (pictureDto.isPrivateFlag()) {
            picture.setPrivateFlag(true);
        }

        pictureRepository.save(picture);
    }


    @Override
    public PictureDto getPictureById(final Long id) {
        Picture picture = pictureRepository.findById(id).orElseThrow(()
                -> new PictureNotFoundException(ErrorMessage.PICTURE_NOT_FOUND, id));
        PictureDetails pictureDetails = pictureDetailsRepository.findById(picture.getId()).orElseThrow(()
                -> new PictureNotFoundException(ErrorMessage.PICTURE_DETAILS_NOT_FOUND, id));
        PictureDto pictureDto = pictureMapper.toDto(picture);
        if (Objects.nonNull(pictureDetails)) {
            pictureDto.setPictureDetailsDto(pictureDetailsMapper.toDto(pictureDetails));
        }

        return pictureDto;
    }

    @Override
    public List<PictureDto> getAllPictures() {
        List<PictureDto> pictureDtos = pictureMapper.toDtos(pictureRepository.findAll());
        for (PictureDto dto : pictureDtos) {
            dto.setPictureDetailsDto(pictureDetailsMapper.toDto(pictureDetailsRepository.findById(dto.getId())
                    .orElseThrow(() -> new PictureNotFoundException(
                            ErrorMessage.PICTURE_DETAILS_NOT_FOUND, dto.getId()))));
        }
        return pictureDtos;
    }


    @Override
    public void savePicture(final PictureDto dto) {
        dto.getPictureDetailsDto().setHeight(setPictureHeight(dto.getPictureDetailsDto().getWidth()));
        dto.getPictureDetailsDto().setCreatedAt(LocalDateTime.now());
        Picture picture = pictureMapper.toEntity(dto);
        PictureDetails pictureDetails = pictureDetailsMapper.toEntity(dto.getPictureDetailsDto());
        picture.setPictureDetails(pictureDetails);
        pictureDetails.setPicture(picture);
        pictureRepository.save(picture);
    }

    @Override
    public void deletePicture(final Long id) {
        pictureRepository.deleteById(id);

    }
}
