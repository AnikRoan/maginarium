package com.aimaginarium.service;

import com.aimaginarium.dto.PictureDto;

import java.util.List;

public interface PictureService {
    PictureDto getPictureById(Long id);

    List<PictureDto> getAllPictures();

    void savePicture(PictureDto pictureDto);

    void deletePicture(Long id);


}
