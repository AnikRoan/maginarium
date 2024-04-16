package com.aimaginarium.controller;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.service.PictureDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/picture/details")
public class PictureDetailsController {
    private final PictureDetailsService pictureDetailService;


    @GetMapping("/get/{id}")
    public ResponseEntity<PictureDetailsDto> getPictureDetailsById(@PathVariable("id") Long id) {
        PictureDetailsDto pictureDetailsDto = pictureDetailService.getPictureDetailsById(id);
        return ResponseEntity.ok(pictureDetailsDto);
    }


    @PostMapping("/save/{pictureId}")
    public void savePictureDetails(@RequestBody PictureDetailsDto pictureDetailsDto, @PathVariable("pictureId") Long pictureId) {
        pictureDetailService.updateDetails(pictureDetailsDto, pictureId);

    }

    @DeleteMapping("/delete/{id}")
    public void deletePictureDetails(@PathVariable("id") Long id) {
        pictureDetailService.deletePictureDetails(id);

    }

    @PutMapping("/update/{id}")
    public void updatePictureDetails(@RequestBody PictureDetailsDto pictureDetailsDto, @PathVariable("id") Long id) {
        pictureDetailService.updateDetails(pictureDetailsDto, id);

    }


}
