package com.aimaginarium.service.data;

import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.mapper.PictureMapper;

import com.aimaginarium.model.Picture;
import com.aimaginarium.repository.PictureRepository;
import com.aimaginarium.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureMapper pictureMapper;
    private final PictureRepository pictureRepository;

    @Override
    public PictureDto getPictureById(Long id) {
        Picture picture = pictureRepository.findById(id).orElse(null);
        return pictureMapper.toDto(picture);
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
