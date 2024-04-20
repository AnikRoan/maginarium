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

import java.util.Objects;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PictureDetailServiceImpl implements PictureDetailsService {
    private final PictureRepository pictureRepository;

    @Override
    public void updateDetails(final PictureDetailsDto pictureDetailsDto) {
        Picture picture = pictureRepository.findById(pictureDetailsDto.getId()).orElseThrow(()
                -> new PictureNotFoundException(format(ErrorMessage.PICTURE_NOT_FOUND, pictureDetailsDto.getId())));
        if (picture.isDeletedFlag()) {
            throw new PictureNotFoundException(format(ErrorMessage.PICTURE_DELETED, picture.getId()));
        }
        if (picture.getPictureDetails() == null) {
            throw new PictureNotFoundException(format(ErrorMessage.PICTURE_DETAILS_NOT_FOUND, pictureDetailsDto.getId()));

        } else {
            updateDetails(pictureDetailsDto, picture);
        }
        pictureRepository.save(picture);
    }

    private void updateDetails(final PictureDetailsDto pictureDetailsDto, Picture picture) {
        if (Objects.nonNull(pictureDetailsDto.getTitle())) {
            picture.getPictureDetails().setTitle(pictureDetailsDto.getTitle());
        }
        if (Objects.nonNull(pictureDetailsDto.getPrompt())) {
            picture.getPictureDetails().setPrompt(pictureDetailsDto.getPrompt());
        }
        if (Objects.nonNull(pictureDetailsDto.getStyles())) {
            picture.getPictureDetails().setStyles(pictureDetailsDto.getStyles());
        }
        if (Objects.nonNull(pictureDetailsDto.getWidth())) {
            picture.getPictureDetails().setWidth(pictureDetailsDto.getWidth());
            picture.getPictureDetails().setHeight(setPictureHeight(pictureDetailsDto.getWidth()));
        }
    }

    public static int setPictureHeight(int width) {
        return (int) Math.round((double) width * 4 / 3);
    }

}
