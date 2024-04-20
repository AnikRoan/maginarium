package com.aimaginarium.controller;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.service.picture.PictureDetailsService;
import com.aimaginarium.service.picture.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aimaginarium.utils.PictureEndpointUris.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ROOT_PICTURE)
public class PictureController {
    private final PictureService pictureService;
    private final PictureDetailsService pictureDetailsService;


    @GetMapping(GET_PICTURE)
    public ResponseEntity<PictureDto> getPictureById(@PathVariable("id") final Long id) {
        PictureDto pictureDto = pictureService.getPictureById(id);
        return ResponseEntity.ok(pictureDto);
    }

    @GetMapping(GET_ALL_PICTURES)
    public ResponseEntity<List<PictureDto>> getAllPictures() {
        List<PictureDto> pictureDtos = pictureService.getAllPictures();
        return ResponseEntity.ok(pictureDtos);
    }


    @PostMapping(SAVE_PICTURE)
    public ResponseEntity<PictureDto> savePictureAndDetails(@RequestBody final PictureDto dto) {
        pictureService.savePicture(dto);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(DELETE_PICTURE)
    public void deletePicture(@PathVariable("id") final Long id) {
        pictureService.deletePicture(id);

    }

    @PutMapping(UPDATE_PICTURE)
    public ResponseEntity<PictureDto> updatePicture(@RequestBody final PictureDto dto) {
        pictureService.updatePicture(dto);
        return ResponseEntity.ok(pictureService.getPictureById(dto.getId()));

    }

    @PutMapping(UPDATE_PICTURE_DETAILS)
    public void updatePictureDetails(@RequestBody final PictureDetailsDto pictureDetailsDto) {
        pictureDetailsService.updateDetails(pictureDetailsDto);

    }


}
