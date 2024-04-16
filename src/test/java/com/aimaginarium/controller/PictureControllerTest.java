package com.aimaginarium.controller;

import com.aimaginarium.dto.PictureDto;
import com.aimaginarium.service.PictureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PictureControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PictureService mockPictureService;

    @Test
    void getPictureById() throws Exception {
        Long id = 1L;
        PictureDto mockPictureDto = new PictureDto();
        mockPictureDto.setId(id);
        mockPictureDto.setS3Link("s3Link");
        mockPictureDto.setPrivateFlag(true);
        mockPictureDto.setDeletedFlag(true);

        when(mockPictureService.getPictureById(id)).thenReturn(mockPictureDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/picture/get/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        PictureDto pictureDto = objectMapper.readValue(result.getResponse().getContentAsString(), PictureDto.class);
        assertEquals(id, pictureDto.getId());
    }

    @Test
    void getAllPictures() {
    }

    @Test
    void savePictureAndDetails() {
    }

    @Test
    void deletePicture() {
    }

    @Test
    void updatePicture() {
    }

    @Test
    void updatePictureDetails() {
    }
}