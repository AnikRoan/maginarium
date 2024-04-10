package com.aimaginarium.service;

import com.aimaginarium.dto.PictureDetailsDto;

import java.util.List;

public interface PictureDetailsService {
    void updateDetails(PictureDetailsDto pictureDetailsDto,Long id);
    PictureDetailsDto getPictureDetailsById(Long id);

    List<PictureDetailsDto> getAllPictureDetails();

    void savePictureDetails(PictureDetailsDto pictureDetailsDto);

    void deletePictureDetails(Long id);
}
