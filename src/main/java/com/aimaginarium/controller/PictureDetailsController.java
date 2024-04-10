package com.aimaginarium.controller;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.model.Picture;
import com.aimaginarium.service.PictureDetailsService;
import com.aimaginarium.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/picture/details")
public class PictureDetailsController {
    private final PictureDetailsService pictureDetailService;
    private final PictureService pictureService;

    @GetMapping("/get/{id}")
    public ResponseEntity<PictureDetailsDto> getPictureDetailsById(@PathVariable("id") Long id) {
        PictureDetailsDto pictureDetailsDto = pictureDetailService.getPictureDetailsById(id);
        return ResponseEntity.ok(pictureDetailsDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PictureDetailsDto>> getAllPictureDetails() {
        List<PictureDetailsDto> pictureDetailsDtos = pictureDetailService.getAllPictureDetails();
        return ResponseEntity.ok(pictureDetailsDtos);
    }

    @PostMapping("/save/{pictureId}")
    public ResponseEntity<String> savePictureDetails(@RequestBody PictureDetailsDto pictureDetailsDto,@PathVariable("pictureId") Long pictureId) {
        PictureDto picture = pictureService.getPictureById(pictureId);
        picture.setPictureDetailsDto(pictureDetailsDto);
        pictureService.savePicture(picture);
       // pictureDetailService.savePictureDetails(pictureDetailsDto);

        return ResponseEntity.ok("Picture details saved");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePictureDetails(@PathVariable("id") Long id) {
        pictureDetailService.deletePictureDetails(id);
        return ResponseEntity.ok("Picture details deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePictureDetails(@RequestBody PictureDetailsDto pictureDetailsDto,@PathVariable("id") Long id) {
        PictureDetailsDto pictureDetails = pictureDetailService.getPictureDetailsById(id);
        pictureDetails.setTitle(pictureDetailsDto.getTitle());
        pictureDetails.setPrompt(pictureDetailsDto.getPrompt());
        pictureDetails.setWidth(pictureDetailsDto.getWidth());
        pictureDetails.setHeight(pictureDetailsDto.getHeight());
        pictureDetails.setStyles(pictureDetailsDto.getStyles());
        pictureDetailService.savePictureDetails(pictureDetails);
        return ResponseEntity.ok("Picture details updated");
    }


}
