package com.aimaginarium.service.data;

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

import java.util.ArrayList;
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
        Picture picture = pictureRepository.findById(id).orElse(null);
        if (picture != null) {
            picture.setS3Link(pictureDto.getS3Link());
            picture.setPrivateFlag(pictureDto.isPrivateFlag());
            picture.setDeletedFlag(pictureDto.isDeletedFlag());
            pictureRepository.save(picture);
        }

    }

    @Override
    public PictureDto getPictureById(Long id) {
        Picture picture = pictureRepository.findById(id).orElse(null);
        PictureDetails pictureDetails = pictureDetailsRepository.findById(picture.getId()).orElse(null);

        PictureDto pictureDto = pictureMapper.toDto(picture);
        pictureDto.setPictureDetailsDto(pictureDetailsMapper.toDto(pictureDetails));


        return pictureDto;
    }

    @Override
    public List<PictureDto> getAllPictures() {
        List<Picture> pictures = pictureRepository.findAll();
        return pictureMapper.toDtos(pictures);
    }


    @Override
    public void savePicture(PictureDto pictureDto) {
        Picture picture = pictureMapper.toEntity(pictureDto);
        pictureRepository.save(picture);


    }

    @Override
    public void deletePicture(Long id) {
        pictureRepository.deleteById(id);

    }
}
