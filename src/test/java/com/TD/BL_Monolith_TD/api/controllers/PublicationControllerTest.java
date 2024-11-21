package com.TD.BL_Monolith_TD.api.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.TD.BL_Monolith_TD.api.dto.requests.PublicationRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PublicationResponse;
import com.TD.BL_Monolith_TD.api.dto.response.UserResponse;
import com.TD.BL_Monolith_TD.config.TestSecurityConfig;
import com.TD.BL_Monolith_TD.config.WebConfig;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPublicationService;
import com.TD.BL_Monolith_TD.util.enums.RoleUser;
import com.TD.BL_Monolith_TD.util.enums.StatusPublication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@WebMvcTest(PublicationController.class)
@Import({TestSecurityConfig.class, WebConfig.class})
public class
PublicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPublicationService publicationService ;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testGetAllPublications() throws Exception {
        PublicationResponse pub1 = new PublicationResponse();
        pub1.setId(1L);
        pub1.setTitle("Publication One");
        pub1.setDescription("Description of publication one.");
        pub1.setTags("Tag1, Tag2");
        pub1.setUrl_img("http://example.com/img1.jpg");
        pub1.setPublication_date(LocalDateTime.now());
        pub1.setEnum_status(StatusPublication.PENDIENTE);
        pub1.setUser(new UserResponse("User1","test","test@example.com", RoleUser.USER));

        PublicationResponse pub2 = new PublicationResponse();
        pub2.setId(2L);
        pub2.setTitle("Publication Two");
        pub2.setDescription("Description of publication two.");
        pub2.setTags("Tag3, Tag4");
        pub2.setUrl_img("http://example.com/img2.jpg");
        pub2.setPublication_date(LocalDateTime.now());
        pub2.setEnum_status(StatusPublication.PENDIENTE);
        pub2.setUser(new UserResponse("User2","test","test@example.com", RoleUser.USER));

        List<PublicationResponse> publications = Arrays.asList(pub1, pub2);

        when(publicationService.getAll()).thenReturn(publications);

        MvcResult result = mockMvc.perform(get("/publication")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(publicationService).getAll();
        assertThat(result.getResponse().getContentAsString()).contains("Publication One", "Publication Two");
    }

    @Test
    public void testGetById() throws Exception {
        Long publicationId = 1L;
        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setId(publicationId);
        publicationResponse.setTitle("Publication One");
        publicationResponse.setDescription("Description of publication one.");
        publicationResponse.setTags("Tag1, Tag2");
        publicationResponse.setUrl_img("http://example.com/img1.jpg");
        publicationResponse.setPublication_date(LocalDateTime.now());
        publicationResponse.setEnum_status(StatusPublication.PENDIENTE);
        publicationResponse.setUser(new UserResponse("User1","test","test@example.com", RoleUser.USER));;

        when(publicationService.find(publicationId)).thenReturn(publicationResponse);

        MvcResult result = mockMvc.perform(get("/publication/{id}", publicationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(publicationService).find(publicationId);
        assertThat(result.getResponse().getContentAsString()).contains(publicationId.toString(), "Publication One", "Description of publication one.");
    }

    @Test
    public void testInsertPublication() throws Exception {
        PublicationRequest publicationRequest = new PublicationRequest();
        publicationRequest.setTitle("New Publication");
        publicationRequest.setDescription("This is a new publication.");
        publicationRequest.setTags("Tag1, Tag2");
        publicationRequest.setUrl_img("http://example.com/img.jpg");
        publicationRequest.setUser_id("User1");
        publicationRequest.setEnum_status(StatusPublication.PENDIENTE);

        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setId(1L);

        when(publicationService.create(any(PublicationRequest.class))).thenReturn(publicationResponse);

        MvcResult result = mockMvc.perform(post("/publication")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publicationRequest)))
                .andExpect(status().isOk())
                .andReturn();

        verify(publicationService).create(any(PublicationRequest.class));
        assertThat(result.getResponse().getContentAsString()).contains("1");
    }

    @Test
    public void testDeletePublication() throws Exception {
        Long publicationId = 1L;

        doNothing().when(publicationService).delete(publicationId);

        MvcResult result = mockMvc.perform(delete("/publication/{id}", publicationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(publicationService).delete(publicationId);
        assertThat(result.getResponse().getContentAsString()).isEqualTo("{\"message\":\"Se elimino el publicaciÃ³n correctamente\"}");
    }


    @Test
    public void testUpdatePublication() throws Exception {
        Long publicationId = 1L;
        PublicationRequest publicationRequest = new PublicationRequest();
        publicationRequest.setTitle("Updated Publication");
        publicationRequest.setDescription("This is an updated publication.");
        publicationRequest.setTags("UpdatedTag1, UpdatedTag2");
        publicationRequest.setUrl_img("http://example.com/updated-img.jpg");
        publicationRequest.setUser_id("User1");
        publicationRequest.setEnum_status(StatusPublication.PENDIENTE);

        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setId(publicationId);

        when(publicationService.update(any(Long.class), any(PublicationRequest.class))).thenReturn(publicationResponse);

        MvcResult result = mockMvc.perform(put("/publication/{id}", publicationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publicationRequest)))
                .andExpect(status().isOk())
                .andReturn();

        verify(publicationService).update(any(Long.class), any(PublicationRequest.class));
        assertThat(result.getResponse().getContentAsString()).contains(publicationId.toString());
    }


}
