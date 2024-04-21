package com.aimaginarium.controller;

import com.aimaginarium.dto.PictureDetailsDto;
import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.service.picture.PictureDetailsService;
import com.aimaginarium.service.picture.PictureService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PictureControllerTest {
    private final String PATH = "/api/v1/picture";
    private final Long id = 1L;
    private PictureDto pictureDto;
    private PictureDetailsDto pictureDetailsDto;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PictureService mockPictureService;
    @MockBean
    private PictureDetailsService mockPictureDetailsService;


    @BeforeEach
    void init() {
        pictureDto = new PictureDto();
        pictureDto.setId(id);
        pictureDto.setS3Link("s3Link");
        pictureDto.setPrivateFlag(true);
        pictureDto.setDeletedFlag(true);

        pictureDetailsDto = new PictureDetailsDto();
        pictureDetailsDto.setId(id);
        pictureDetailsDto.setTitle("title");
        pictureDetailsDto.setPrompt("prompt");
        pictureDetailsDto.setWidth(1);
        pictureDetailsDto.setStyles("styles");

    }

    @Test
    void getPictureById() throws Exception {
        when(mockPictureService.getPictureById(id)).thenReturn(pictureDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/get/{id}", id))
                .andExpect(status().isOk())
                .andReturn();

        PictureDto pictureDto = objectMapper.readValue(result.getResponse().getContentAsString(), PictureDto.class);
        assertEquals(id, pictureDto.getId());
    }

    @Test
    void getAllPictures() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/all"))
                .andExpect(status().isOk())
                .andReturn();
        List<PictureDto> pictureDtos = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<PictureDto>>() {
        });
        assertNotNull(pictureDtos);
    }

    @Test
    void savePicture() throws Exception {
        pictureDto.setPictureDetailsDto(pictureDetailsDto);

        mockMvc.perform(post(PATH + "/save_picture")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pictureDto)))
                .andExpect(status().isOk());
    }


    @Test
    void deletePicture() throws Exception {
        mockMvc.perform(delete(PATH + "/delete/{id}", id))
                .andExpect(status().isOk());

        verify(mockPictureService, times(1)).deletePicture(id);
    }

    @Test
    void updatePicture() throws Exception {
        mockMvc.perform(put(PATH + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pictureDto)))
                .andExpect(status().isOk());

        verify(mockPictureService, times(1)).updatePicture(pictureDto);
    }

    @Test
    void updatePictureDetails() throws Exception {
        mockMvc.perform(put(PATH + "/update_details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pictureDetailsDto)))
                .andExpect(status().isOk());

        verify(mockPictureDetailsService, times(1)).updateDetails(pictureDetailsDto);
    }
}