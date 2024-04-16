package com.aimaginarium.controller;

import com.aimaginarium.dto.DetailsAndPictureDto;
import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.service.PictureDetailsService;
import com.aimaginarium.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/picture")
public class PictureController {
    private final PictureService pictureService;
    private final PictureDetailsService pictureDetailsService;


    @GetMapping("/get/{id}")
    public ResponseEntity<PictureDto> getPictureById(@PathVariable("id") Long id) {
        PictureDto pictureDto = pictureService.getPictureById(id);
        return ResponseEntity.ok(pictureDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PictureDto>> getAllPictures() {
        List<PictureDto> pictureDtos = pictureService.getAllPictures();
        return ResponseEntity.ok(pictureDtos);
    }


    @PostMapping("/save_picture_and_details")
    public void savePictureAndDetails(@RequestBody DetailsAndPictureDto detailsAndPictureDto) {
        pictureService.savePictureAndDetails(detailsAndPictureDto);

    }

    @DeleteMapping("/delete/{id}")
    public void deletePicture(@PathVariable("id") Long id) {
        pictureService.deletePicture(id);

    }
    @PutMapping("/update/{id}")
    public void updatePicture(@RequestBody PictureDto dto, @PathVariable("id") Long id) {
        pictureService.updatePicture(dto, id);

    }
    @PutMapping("/update_details/{id}")
    public void updatePictureDetails(@RequestBody PictureDetailsDto pictureDetailsDto, @PathVariable("id") Long id) {
        pictureDetailsService.updateDetails(pictureDetailsDto, id);

    }


}
