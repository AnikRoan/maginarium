package com.aimaginarium.controller;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.service.picture.PictureDetailsService;
import com.aimaginarium.service.picture.PictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aimaginarium.utils.PictureEndpointUris.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ROOT_PICTURE)
@Tag(name = "Picture")
public class PictureController {
    private final PictureService pictureService;
    private final PictureDetailsService pictureDetailsService;

    @Operation(summary = "Get picture by id",
            description = "This method retrieves a picture by its id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "picture not found")
            })
    @GetMapping(GET_PICTURE)
    @ResponseStatus(HttpStatus.OK)
    public PictureDto getPictureById(@PathVariable("id") final Long id) {
        return pictureService.getPictureById(id);
    }


    @Operation(summary = "Get all pictures",
            description = "This method retrieves all pictures stored in the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "pictures not found")
            })
    @GetMapping(GET_ALL_PICTURES)
    @ResponseStatus(HttpStatus.OK)
    public List<PictureDto> getAllPictures() {
        List<PictureDto> pictureDtos = pictureService.getAllPictures();
        return pictureDtos;
    }

    @Operation(summary = "Save picture",
            description = "This method saves a picture and details in the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
            })
    @PostMapping(SAVE_PICTURE)
    @ResponseStatus(HttpStatus.CREATED)
    public PictureDto savePictureAndDetails(@RequestBody final PictureDto dto) {
        pictureService.savePicture(dto);
        return pictureService.getPictureById(dto.getId());
    }

    @Operation(summary = "Delete picture by id",
            description = "This method deletes a picture by  id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "picture not found")
            })
    @DeleteMapping(DELETE_PICTURE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePicture(@PathVariable("id") final Long id) {
        pictureService.deletePicture(id);
    }

    @Operation(summary = "Update picture",
            description = "This method updates a picture in the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "picture not found")
            })
    @PutMapping(UPDATE_PICTURE)
    @ResponseStatus(HttpStatus.OK)
    public PictureDto updatePicture(@RequestBody final PictureDto dto) {
        pictureService.updatePicture(dto);
        return pictureService.getPictureById(dto.getId());
    }

    @Operation(summary = "Update picture details",
            description = "This method updates a picture details in the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "picture not found")
            })
    @PutMapping(UPDATE_PICTURE_DETAILS)
    @ResponseStatus(HttpStatus.OK)
    public PictureDetailsDto updatePictureDetails(@RequestBody final PictureDetailsDto pictureDetailsDto) {
        pictureDetailsService.updateDetails(pictureDetailsDto);
        return pictureDetailsDto;
    }


}
