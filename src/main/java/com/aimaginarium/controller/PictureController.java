package com.aimaginarium.controller;

import com.aimaginarium.dto.PictureDto;
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


    @GetMapping("/get/{id}")
    public ResponseEntity<PictureDto> getPictureById(@PathVariable("id") final Long id) {
        PictureDto pictureDto = pictureService.getPictureById(id);
        return ResponseEntity.ok(pictureDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PictureDto>> getAllPictures() {
        List<PictureDto> pictureDtos = pictureService.getAllPictures();
        return ResponseEntity.ok(pictureDtos);
    }

    @PostMapping("/save")
    public void savePicture(@RequestBody final PictureDto pictureDto) {
        pictureService.savePicture(pictureDto);


    }

    @DeleteMapping("/delete/{id}")
    public void deletePicture(@PathVariable("id") final Long id) {
        pictureService.deletePicture(id);

    }

    @PutMapping("/update/{id}")
    public void updatePicture(@RequestBody final PictureDto dto, @PathVariable("id") final Long id) {
        pictureService.updatePicture(dto, id);


    }


}
