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
    public ResponseEntity<PictureDto> getPictureById(@PathVariable("id") Long id) {
        PictureDto pictureDto = pictureService.getPictureById(id);
        return ResponseEntity.ok(pictureDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PictureDto>> getAllPictures() {
        List<PictureDto> pictureDtos = pictureService.getAllPictures();
        return ResponseEntity.ok(pictureDtos);
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePicture(@RequestBody PictureDto pictureDto) {
       pictureService.savePicture(pictureDto);
       return ResponseEntity.ok("Picture saved");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePicture(@PathVariable("id") Long id) {
        pictureService.deletePicture(id);
        return ResponseEntity.ok("Picture deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePicture(@RequestBody PictureDto dto,@PathVariable("id") Long id) {
        pictureService.updatePicture(dto,id);

        return ResponseEntity.ok("Picture updated");

    }


}
