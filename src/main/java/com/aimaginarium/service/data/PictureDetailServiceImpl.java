package com.aimaginarium.service.data;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.mapper.PictureDetailsMapperImpl;
import com.aimaginarium.model.PictureDetails;
import com.aimaginarium.repository.PictureDetailsRepository;
import com.aimaginarium.service.PictureDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PictureDetailServiceImpl implements PictureDetailsService {
    private final PictureDetailsMapperImpl pictureDetailsMapper;
    private final PictureDetailsRepository pictureDetailsRepository;
    @Override
    public PictureDetailsDto getPictureDetailsById(Long id) {
        PictureDetails pictureDetails = pictureDetailsRepository.findById(id).orElse(null);
        return pictureDetailsMapper.toDto(pictureDetails);
    }

    @Override
    public List<PictureDetailsDto> getAllPictureDetails() {
        List<PictureDetails> pictureDetails = pictureDetailsRepository.findAll();
        return pictureDetailsMapper.toDtos(pictureDetails);
    }


    @Override
    public void savePictureDetails(PictureDetailsDto pictureDetailsDto) {
        PictureDetails pictureDetails = pictureDetailsMapper.toEntity(pictureDetailsDto);
        pictureDetailsRepository.save(pictureDetails);


    }

    @Override
    public void deletePictureDetails(Long id) {
        pictureDetailsRepository.deleteById(id);

    }
}
