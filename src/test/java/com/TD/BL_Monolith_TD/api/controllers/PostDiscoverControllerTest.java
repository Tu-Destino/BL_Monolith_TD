package com.TD.BL_Monolith_TD.api.controllers;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.TD.BL_Monolith_TD.api.dto.requests.LabelsRequest;
import com.TD.BL_Monolith_TD.api.dto.requests.PostDiscoverRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PostDiscoverResponse;
import com.TD.BL_Monolith_TD.config.TestSecurityConfig;
import com.TD.BL_Monolith_TD.config.WebConfig;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPostDiscoverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(PostDiscoverController.class)
@Import({TestSecurityConfig.class, WebConfig.class})
public class PostDiscoverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPostDiscoverService postDiscoverService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllPostDiscoveries() throws Exception {
        PostDiscoverResponse post1 = new PostDiscoverResponse();
        post1.setId("1");
        post1.setTitle("Post One");
        post1.setDescription("Description of post one.");
        post1.setTags("Tag1, Tag2");
        post1.setUrlImg("http://example.com/img1.jpg");

        PostDiscoverResponse post2 = new PostDiscoverResponse();
        post2.setId("2");
        post2.setTitle("Post Two");
        post2.setDescription("Description of post two.");
        post2.setTags("Tag3, Tag4");
        post2.setUrlImg("http://example.com/img2.jpg");

        List<PostDiscoverResponse> posts = Arrays.asList(post1, post2);

        when(postDiscoverService.getAll()).thenReturn(posts);

        MvcResult result = mockMvc.perform(get("/postDiscover")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(postDiscoverService).getAll();
        assertThat(result.getResponse().getContentAsString()).contains("Post One", "Post Two");
    }

    @Test
    public void testGetById() throws Exception {
        String postId = "1";
        PostDiscoverResponse postResponse = new PostDiscoverResponse();
        postResponse.setId(postId);
        postResponse.setTitle("Post One");
        postResponse.setDescription("Description of post one.");
        postResponse.setTags("Tag1, Tag2");
        postResponse.setUrlImg("http://example.com/img1.jpg");

        when(postDiscoverService.find(postId)).thenReturn(postResponse);

        MvcResult result = mockMvc.perform(get("/postDiscover/{id}", postId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(postDiscoverService).find(postId);
        assertThat(result.getResponse().getContentAsString()).contains(postId, "Post One", "Description of post one.");
    }

    @Test
    public void testInsertPostDiscover() throws Exception {
        PostDiscoverRequest postRequest = new PostDiscoverRequest();
        postRequest.setTitle("New Post Discovery");
        postRequest.setDescription("This is a new post discovery.");
        postRequest.setTags("Tag1, Tag2");
        postRequest.setUrlImg("http://example.com/img.jpg");
        postRequest.setPlace_id(1L);
        postRequest.setUser_id("1");

        PostDiscoverResponse postResponse = new PostDiscoverResponse();
        postResponse.setId("1");

        when(postDiscoverService.create(any(PostDiscoverRequest.class))).thenReturn(postResponse);

        MvcResult result = mockMvc.perform(post("/postDiscover")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postRequest)))
                .andExpect(status().isOk())
                .andReturn();

        verify(postDiscoverService).create(any(PostDiscoverRequest.class));
        assertThat(result.getResponse().getContentAsString()).contains("1");
    }

    @Test
    public void testDeletePostDiscover() throws Exception {
        String postId = "1";

        doNothing().when(postDiscoverService).delete(postId);

        MvcResult result = mockMvc.perform(delete("/postDiscover/{id}", postId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(postDiscoverService).delete(postId);
        assertThat(result.getResponse().getContentAsString()).contains("Se elimino el post correctamente");
    }

    @Test
    public void testUpdatePostDiscover() throws Exception {
        String postId = "1";
        PostDiscoverRequest postRequest = new PostDiscoverRequest();
        postRequest.setTitle("Updated Post Discovery");
        postRequest.setDescription("This is an updated post discovery.");
        postRequest.setTags("UpdatedTag1, UpdatedTag2");
        postRequest.setUrlImg("http://example.com/updated-img.jpg");
        postRequest.setPlace_id(1L);
        postRequest.setUser_id("1");

        PostDiscoverResponse postResponse = new PostDiscoverResponse();
        postResponse.setId(postId);

        when(postDiscoverService.update(any(String.class), any(PostDiscoverRequest.class))).thenReturn(postResponse);

        MvcResult result = mockMvc.perform(put("/postDiscover/{id}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postRequest)))
                .andExpect(status().isOk())
                .andReturn();

        verify(postDiscoverService).update(any(String.class), any(PostDiscoverRequest.class));
        assertThat(result.getResponse().getContentAsString()).contains(postId);
    }

    @Test
    public void testGetByTags() throws Exception {
        LabelsRequest labelsRequest = new LabelsRequest();
        labelsRequest.setArray( "Historia,Cultura");

        PostDiscoverResponse post1 = new PostDiscoverResponse();
        post1.setId("1");
        post1.setTitle("Post One");
        post1.setDescription("Description of post one.");
        post1.setTags("Historia,Cultura");
        post1.setUrlImg("http://example.com/img1.jpg");

        PostDiscoverResponse post2 = new PostDiscoverResponse();
        post2.setId("2");
        post2.setTitle("Post Two");
        post2.setDescription("Description of post two.");
        post2.setTags("Historia,Cultura");
        post2.setUrlImg("http://example.com/img2.jpg");

        List<PostDiscoverResponse> posts = Arrays.asList(post1, post2);

        when(postDiscoverService.findByTags(any(LabelsRequest.class))).thenReturn(posts);

        MvcResult result = mockMvc.perform(post("/postDiscover/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(labelsRequest)))
                .andExpect(status().isOk())
                .andReturn();

        verify(postDiscoverService).findByTags(any(LabelsRequest.class));
        assertThat(result.getResponse().getContentAsString()).contains("Post One", "Post Two");
    }

    @Test
    public void testGetAllTags() throws Exception {
        List<String> tags = Arrays.asList("Tag1", "Tag2", "Tag3");

        when(postDiscoverService.getTags()).thenReturn(tags);

        MvcResult result = mockMvc.perform(get("/postDiscover/AllTags")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(postDiscoverService).getTags();
        assertThat(result.getResponse().getContentAsString()).contains("Tag1", "Tag2", "Tag3");
    }

    @Test
    public void testGetUrlImgByTitle() throws Exception {
        String title = "Post One";
        List<String> urlImgs = Arrays.asList("http://example.com/img1.jpg", "http://example.com/img2.jpg");

        when(postDiscoverService.getUrlImgByTitle(title)).thenReturn(urlImgs);

        MvcResult result = mockMvc.perform(get("/postDiscover/getUrlImg/{title}", title)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(postDiscoverService).getUrlImgByTitle(title);
        assertThat(result.getResponse().getContentAsString()).contains("http://example.com/img1.jpg", "http://example.com/img2.jpg");
    }


}

