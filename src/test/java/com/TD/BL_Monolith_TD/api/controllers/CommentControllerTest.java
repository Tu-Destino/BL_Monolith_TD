package com.TD.BL_Monolith_TD.api.controllers;

import com.TD.BL_Monolith_TD.api.dto.requests.CommentRequest;
import com.TD.BL_Monolith_TD.api.dto.response.CommentResponse;
import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.api.dto.response.UserResponse;
import com.TD.BL_Monolith_TD.config.TestSecurityConfig;
import com.TD.BL_Monolith_TD.config.WebConfig;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.ICommentService;
import com.TD.BL_Monolith_TD.util.enums.RoleUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@Import({TestSecurityConfig.class, WebConfig.class})
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllComments() throws Exception {
        CommentResponse comment1 = new CommentResponse();
        comment1.setId(1L);
        comment1.setComment("This is the first comment.");
        comment1.setDate(LocalDate.now());
        comment1.setUser(new UserResponse("User1","test","test@example.com", RoleUser.USER));
        comment1.setPlace(null);

        CommentResponse comment2 = new CommentResponse();
        comment2.setId(2L);
        comment2.setComment("This is the second comment.");
        comment2.setDate(LocalDate.now());
        comment2.setUser(new UserResponse("User1","test","test@example.com", RoleUser.USER));
        comment2.setPlace(null);

        List<CommentResponse> comments = Arrays.asList(comment1, comment2);

        when(commentService.getAll()).thenReturn(comments);

        MvcResult result = mockMvc.perform(get("/comment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(commentService).getAll();
        assertThat(result.getResponse().getContentAsString()).contains("This is the first comment.", "This is the second comment.");
    }

    @Test
    public void testGetCommentById() throws Exception {
        Long commentId = 1L;
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(commentId);
        commentResponse.setComment("This is a specific comment.");
        commentResponse.setDate(LocalDate.now());
        commentResponse.setUser(new UserResponse("User1","test","test@example.com", RoleUser.USER));
        commentResponse.setPlace(null);

        when(commentService.find(commentId)).thenReturn(commentResponse);

        MvcResult result = mockMvc.perform(get("/comment/{id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(commentService).find(commentId);
        assertThat(result.getResponse().getContentAsString()).contains(commentId.toString(), "This is a specific comment.");
    }

    @Test
    public void testGetCommentsByIdPlace() throws Exception {
        Long placeId = 1L;

        CommentResponse comment1 = new CommentResponse();
        comment1.setId(1L);
        comment1.setComment("This is the first comment for the place.");
        comment1.setDate(LocalDate.now());
        comment1.setUser(new UserResponse("User1","test","test@example.com", RoleUser.USER));
        comment1.setPlace(null);

        CommentResponse comment2 = new CommentResponse();
        comment2.setId(2L);
        comment2.setComment("This is the second comment for the place.");
        comment2.setDate(LocalDate.now());
        comment2.setUser(new UserResponse("User1","test","test@example.com", RoleUser.USER));
        comment2.setPlace(null);

        List<CommentResponse> comments = Arrays.asList(comment1, comment2);

        when(commentService.getAllByIdPlace(placeId)).thenReturn(comments);

        MvcResult result = mockMvc.perform(get("/comment/place/{id}", placeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(commentService).getAllByIdPlace(placeId);
        assertThat(result.getResponse().getContentAsString()).contains("This is the first comment for the place.", "This is the second comment for the place.");
    }

    @Test
    public void testInsertComment() throws Exception {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setComment("This is a new comment.");

        commentRequest.setUser_id("User1");
        commentRequest.setPlace_id(1L);

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(1L);
        commentResponse.setComment("This is a new comment.");
        commentResponse.setDate(LocalDate.now());
        commentResponse.setUser(new UserResponse("User1","test","test@example.com", RoleUser.USER));
        commentResponse.setPlace(null);

        when(commentService.create(any(CommentRequest.class))).thenReturn(commentResponse);

        MvcResult result = mockMvc.perform(post("/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk())
                .andReturn();

        verify(commentService).create(any(CommentRequest.class));
        assertThat(result.getResponse().getContentAsString()).contains("This is a new comment.");
    }

    @Test
    public void testDeleteComment() throws Exception {
        Long commentId = 1L;

        doNothing().when(commentService).delete(commentId);

        MvcResult result = mockMvc.perform(delete("/comment/{id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(commentService).delete(commentId);
        assertThat(result.getResponse().getContentAsString()).contains("{\"message\":\"Se elimino el post correctamente\"}");
    }

    @Test
    public void testUpdateComment() throws Exception {
        Long commentId = 1L;
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setComment("This is an updated comment.");
        commentRequest.setUser_id("User1");
        commentRequest.setPlace_id(1L);

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(commentId);
        commentResponse.setComment("This is an updated comment.");
        commentResponse.setDate(LocalDate.now());
        commentResponse.setUser(new UserResponse("User1","test","test@example.com", RoleUser.USER));
        commentResponse.setPlace(null);

        when(commentService.update(any(Long.class), any(CommentRequest.class))).thenReturn(commentResponse);

        MvcResult result = mockMvc.perform(put("/comment/{id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk())
                .andReturn();

        verify(commentService).update(any(Long.class), any(CommentRequest.class));
        assertThat(result.getResponse().getContentAsString()).contains("This is an updated comment.");
    }

}
