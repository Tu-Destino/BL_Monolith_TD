package com.TD.BL_Monolith_TD.api.controllers;

import com.TD.BL_Monolith_TD.api.dto.requests.UserRequest;
import com.TD.BL_Monolith_TD.api.dto.response.UserResponse;
import com.TD.BL_Monolith_TD.config.TestSecurityConfig;
import com.TD.BL_Monolith_TD.config.WebConfig;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IUserService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import({TestSecurityConfig.class, WebConfig.class})
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllUsers() throws Exception {
        UserResponse user1 = new UserResponse();
        user1.setId("1");
        UserResponse user2 = new UserResponse();
        user2.setId("2");

        List<UserResponse> users = Arrays.asList(user1, user2);

        when(userService.getAll()).thenReturn(users);

        MvcResult result = mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        verify(userService).getAll();

        assertThat(result.getResponse().getContentAsString()).contains("1", "2");
    }

    @Test
    public void testGetById() throws Exception {
        String userId = "1";
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);
        userResponse.setName("Test User");
        userResponse.setEmail("test@example.com");
        userResponse.setEnum_rol(RoleUser.USER);

        when(userService.find(userId)).thenReturn(userResponse);

        MvcResult result = mockMvc.perform(get("/user/{id}", userId).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        verify(userService).find(userId);
        verify(userService).find(userId); assertThat(result.getResponse().getContentAsString()).contains(userId, "Test User", "test@example.com", "USER");
    }

    @Test public void testInsertUser() throws Exception { UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");
        userRequest.setPassword("123");
        userRequest.setEnum_rol(RoleUser.USER);

        UserResponse userResponse = new UserResponse();
        userResponse.setId("1");


        when(userService.create(any(UserRequest.class))).thenReturn(userResponse);

        MvcResult result = mockMvc.perform(post("/user") .contentType(MediaType.APPLICATION_JSON) .content(objectMapper.writeValueAsString(userRequest))) .andExpect(status().isOk()) .andReturn();

        verify(userService).create(any(UserRequest.class));
        assertThat(result.getResponse().getContentAsString()).contains("1");}

    @Test
    public void testDeleteUser() throws Exception{
        String userId = "1";

        doNothing().when(userService).delete(userId);

        mockMvc.perform(delete("/user/{id}", userId).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        verify(userService).delete(userId);
    }

    @Test
    public void testUpdateUser() throws Exception {
        String userId = "1";
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");
        userRequest.setPassword("123");
        userRequest.setEnum_rol(RoleUser.USER);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);

        when(userService.update(any(String.class), any(UserRequest.class))).thenReturn(userResponse);

        MvcResult result = mockMvc.perform(put("/user/{id}", userId) .contentType(MediaType.APPLICATION_JSON) .content(objectMapper.writeValueAsString(userRequest))) .andExpect(status().isOk()) .andReturn();

        verify(userService).update(any(String.class),any(UserRequest.class));

        assertThat(result.getResponse().getContentAsString()).contains(userId);
    }
}
