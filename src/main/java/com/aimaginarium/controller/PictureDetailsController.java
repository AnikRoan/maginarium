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
    public ResponseEntity<PictureDetailsDto> getPictureDetailsById(@PathVariable("id") final Long id) {
        PictureDetailsDto pictureDetailsDto = pictureDetailService.getPictureDetailsById(id);
        return ResponseEntity.ok(pictureDetailsDto);
    }


    @PostMapping("/save/{pictureId}")
    public void savePictureDetails(@RequestBody final PictureDetailsDto pictureDetailsDto,
                                   @PathVariable("pictureId") final Long pictureId) {
        pictureDetailService.updateDetails(pictureDetailsDto, pictureId);

    }

    @DeleteMapping("/delete/{id}")
    public void deletePictureDetails(@PathVariable("id") final Long id) {
        pictureDetailService.deletePictureDetails(id);

    }

    @PutMapping("/update/{id}")
    public void updatePictureDetails(@RequestBody final PictureDetailsDto pictureDetailsDto,
                                     @PathVariable("id") final Long id) {
        pictureDetailService.updateDetails(pictureDetailsDto, id);

    }


}
