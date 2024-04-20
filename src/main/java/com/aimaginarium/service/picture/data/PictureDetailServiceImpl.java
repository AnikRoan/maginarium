package com.aimaginarium.service.picture.data;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.exception.ErrorMessage;
import com.aimaginarium.exception.PictureNotFoundException;
import com.aimaginarium.mapper.PictureDetailsMapper;

import com.aimaginarium.model.Picture;
import com.aimaginarium.model.PictureDetails;
import com.aimaginarium.repository.PictureRepository;
import com.aimaginarium.service.picture.PictureDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PictureDetailServiceImpl implements PictureDetailsService {
    private final PictureDetailsMapper pictureDetailsMapper;
    private final PictureRepository pictureRepository;

    @Override
    public void updateDetails(PictureDetailsDto pictureDetailsDto) {
        Picture picture = pictureRepository.findById(pictureDetailsDto.getId()).orElseThrow(()
                -> new PictureNotFoundException(format(ErrorMessage.PICTURE_NOT_FOUND, pictureDetailsDto.getId())));
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

    private void buildNewDetails(PictureDetailsDto pictureDetailsDto, Picture picture) {
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
            picture.getPictureDetails().setHeight(setPictureHeight(pictureDetailsDto.getWidth()));
        }


    }
    public static int setPictureHeight(int width) {
       int height  = (int) Math.round((double)width * 4 / 3);
        return height;
    }


}
