package com.aimaginarium.service.data;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.mapper.PictureDetailsMapper;

import com.aimaginarium.model.Picture;
import com.aimaginarium.model.PictureDetails;
import com.aimaginarium.repository.PictureDetailsRepository;
import com.aimaginarium.repository.PictureRepository;
import com.aimaginarium.service.PictureDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PictureDetailServiceImpl implements PictureDetailsService {
    private final PictureDetailsMapper pictureDetailsMapper;
    private final PictureRepository pictureRepository;

    @Override
    public void updateDetails(PictureDetailsDto pictureDetailsDto, Long id) {
        Picture picture = pictureRepository.findById(id).orElseThrow(() -> new RuntimeException("Picture not found"));
        if (picture.getPictureDetails() != null) {
            buildNewDetails(pictureDetailsDto, picture);

        } else {
            PictureDetails pictureDetails = pictureDetailsMapper.toEntity(pictureDetailsDto);
            pictureDetails.setId(picture.getId());
            pictureDetails.setPicture(picture);
            picture.setPictureDetails(pictureDetails);
        }

        pictureRepository.save(picture);
    }

    private Picture buildNewDetails(PictureDetailsDto pictureDetailsDto, Picture picture) {
        if (pictureDetailsDto.getTitle() != null) {
            picture.getPictureDetails().setTitle(pictureDetailsDto.getTitle());
        }
        if (pictureDetailsDto.getPrompt() != null) {
            picture.getPictureDetails().setPrompt(pictureDetailsDto.getPrompt());
        }
        if (pictureDetailsDto.getStyles() != null) {
            picture.getPictureDetails().setStyles(pictureDetailsDto.getStyles());
        }
        if (pictureDetailsDto.getWidth() != null) {
            picture.getPictureDetails().setWidth(pictureDetailsDto.getWidth());
            picture.getPictureDetails().setHeight(pictureDetailsDto.setPictureHeight());
        }

        return picture;


    }









}
