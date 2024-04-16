package com.aimaginarium.aimaginariumapp.controller;

import com.aimaginarium.controller.UserGalleryController;
import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.service.UserGalleryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserGalleryController.class)
@DisplayName("UserGallery Controller Tests")
class UserGalleryControllerTest {

    private final String BASE_URL = "/v1/gallery";

    @MockBean
    private UserGalleryService galleryService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private UserGalleryDto galleryDto;

    @BeforeEach
    void init() {
        galleryDto = UserGalleryDto.builder()
                .id(1L)
                .title("title")
                .build();
    }

    @Test
    @DisplayName("GET /v1/gallery/{id}")
    void getUserGalleryTest() throws Exception {
        when(galleryService.getGalleryById(anyLong())).thenReturn(galleryDto);

        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(galleryService, times(1)).getGalleryById(anyLong());
    }

    @Test
    @DisplayName("GET /v1/gallery/user/{userId}")
    void getUserGalleryByUserTest() throws Exception {
        when(galleryService.getGalleryByUserId(anyLong())).thenReturn(galleryDto);

        mockMvc.perform(get(BASE_URL + "/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(galleryService, times(1)).getGalleryByUserId(anyLong());
    }

    @Test
    @DisplayName("PUT /v1/gallery")
    void updateGalleryTitleTest() throws Exception {
        when(galleryService.updateGalleryTitle(any())).thenReturn(galleryDto);

        String requestBody = objectMapper.writeValueAsString(galleryDto);
        mockMvc.perform(put(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("title"));

        verify(galleryService, times(1)).updateGalleryTitle(any());
    }

}
