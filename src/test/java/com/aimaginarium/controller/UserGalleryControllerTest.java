package com.aimaginarium.controller;

import com.aimaginarium.dto.UserGalleryDto;
import com.aimaginarium.service.gallery.UserGalleryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.aimaginarium.utils.EndpointUris.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserGalleryController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("UserGallery Controller Tests")
class UserGalleryControllerTest {


    @MockBean
    private UserGalleryService galleryService;
    @Autowired
    private MockMvc mockMvc;

    private UserGalleryDto galleryDto;

    @BeforeEach
    void init() {
        galleryDto = UserGalleryDto.builder()
                .id(1L)
                .title("title")
                .build();
    }

    @Test
    @DisplayName("GET " + ROOT_GALLERY + GET_GALLERY)
    void getUserGalleryTest() throws Exception {
        when(galleryService.getGalleryById(anyLong())).thenReturn(galleryDto);

        mockMvc.perform(get(ROOT_GALLERY + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(galleryService, times(1)).getGalleryById(anyLong());
    }

    @Test
    @DisplayName("GET " + ROOT_GALLERY + GET_GALLERY_BY_USER)
    void getUserGalleryByUserTest() throws Exception {
        when(galleryService.getGalleryByUserId(anyLong())).thenReturn(galleryDto);

        mockMvc.perform(get(ROOT_GALLERY + "/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(galleryService, times(1)).getGalleryByUserId(anyLong());
    }

    @Test
    @DisplayName("PUT " + ROOT_GALLERY + UPDATE_GALLERY)
    void updateGalleryTitleTest() throws Exception {
        when(galleryService.updateGalleryTitle(anyLong(), anyString())).thenReturn(galleryDto);

        mockMvc.perform(put(ROOT_GALLERY + "/update/1")
                        .param("title", "title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("title"));

        verify(galleryService, times(1)).updateGalleryTitle(anyLong(), anyString());
    }

}
