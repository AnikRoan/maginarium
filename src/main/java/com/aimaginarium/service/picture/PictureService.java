package com.aimaginarium.service.picture;

import com.aimaginarium.dto.PictureDto;

import java.util.List;

public interface PictureService {
    void savePicture(PictureDto pictureDto);

    void updatePicture(PictureDto pictureDto);

    PictureDto getPictureById(Long id);

    List<PictureDto> getAllPictures();

    void deletePicture(Long id);


}
