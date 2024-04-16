package com.aimaginarium.service.data;

import com.aimaginarium.dto.DetailsAndPictureDto;
import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.mapper.PictureDetailsMapper;
import com.aimaginarium.mapper.PictureMapper;

import com.aimaginarium.model.Picture;
import com.aimaginarium.model.PictureDetails;
import com.aimaginarium.repository.PictureDetailsRepository;
import com.aimaginarium.repository.PictureRepository;
import com.aimaginarium.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final PictureMapper pictureMapper;
    private final PictureRepository pictureRepository;
    private final PictureDetailsRepository pictureDetailsRepository;
    private final PictureDetailsMapper pictureDetailsMapper;

    @Override
    public void updatePicture(PictureDto pictureDto, Long id) {
        Picture picture = pictureRepository.findById(id).orElseThrow(() -> new RuntimeException("Picture not found"));
        if (pictureDto.getS3Link() != null) {
            picture.setS3Link(pictureDto.getS3Link());
        }
        if (pictureDto.isPrivateFlag()) {
            picture.setPrivateFlag(true);
        }
        if(pictureDto.isDeletedFlag()) {
            picture.setDeletedFlag(true);
        }

        pictureRepository.save(picture);
    }



    @Override
    public PictureDto getPictureById(Long id) {
        Picture picture = pictureRepository.findById(id).orElseThrow(() -> new RuntimeException("Picture not found"));
        PictureDetails pictureDetails = pictureDetailsRepository.findById(picture.getId()).orElse(null);
        PictureDto pictureDto = pictureMapper.toDto(picture);
        if (pictureDetails != null) {
            pictureDto.setPictureDetailsDto(pictureDetailsMapper.toDto(pictureDetails));
        }

        return pictureDto;
    }

    @Override
    public List<PictureDto> getAllPictures() {
        List<PictureDto> pictureDtos = pictureMapper.toDtos(pictureRepository.findAll());
        for (PictureDto dto : pictureDtos) {
            dto.setPictureDetailsDto(pictureDetailsMapper.toDto(pictureDetailsRepository.findById(dto.getId()).orElse(null)));
        }
        return pictureDtos;
    }


    @Override
    public void savePictureAndDetails(DetailsAndPictureDto detailsAndPictureDto) {
        PictureDto pictureDto = detailsAndPictureDto.getPictureDto();
        PictureDetailsDto pictureDetailsDto = detailsAndPictureDto.getPictureDetailsDto();
        pictureDetailsDto.setPictureHeight();
        pictureDetailsDto.setCreatedAt(LocalDateTime.now());

        Picture picture = pictureMapper.toEntity(pictureDto);
        PictureDetails pictureDetails = pictureDetailsMapper.toEntity(pictureDetailsDto);
        picture.setPictureDetails(pictureDetails);
        pictureDetails.setPicture(picture);
        pictureRepository.save(picture);


    }

    @Override
    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);

    }
}
