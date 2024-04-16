package com.aimaginarium.service;

import com.aimaginarium.dto.DetailsAndPictureDto;
import com.aimaginarium.dto.PictureDto;

import java.util.List;

public interface PictureService {
    public void savePictureAndDetails(DetailsAndPictureDto detailsAndPictureDto);
    void updatePicture(PictureDto pictureDto,Long id);
    PictureDto getPictureById(Long id);

    List<PictureDto> getAllPictures();

    //void savePicture(PictureDto pictureDto);

    void deletePicture(Long id);


}
