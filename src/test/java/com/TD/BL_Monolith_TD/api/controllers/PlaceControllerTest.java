package com.TD.BL_Monolith_TD.api.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.TD.BL_Monolith_TD.api.dto.requests.PlaceRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.config.TestSecurityConfig;
import com.TD.BL_Monolith_TD.config.WebConfig;
import com.TD.BL_Monolith_TD.infrastructure.abstract_services.IPlaceService;
import com.TD.BL_Monolith_TD.util.enums.Enum_Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(PlaceController.class)
@Import({TestSecurityConfig.class, WebConfig.class})
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPlaceService placeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllPlaces() throws Exception {
        PlaceResponse place1 = new PlaceResponse();
        place1.setId(1L);
        place1.setEnum_type(Enum_Type.CULTURA);
        place1.setTitle("Place One");
        place1.setDetails("Details of place one.");
        place1.setPrice("Free");
        place1.setSchedule("Always open");
        place1.setAddress("123 Main St");
        place1.setCoordinates("http://placeone.com");
        place1.setVr("http://placeone-vr.com");
        place1.setWeb("http://placeone-web.com");
        place1.setPhone("555-1234");
        place1.setRate(4.5);
        place1.setInformation("Information about place one.");
        place1.setBtn_url("http://placeone-button.com");

        PlaceResponse place2 = new PlaceResponse();
        place2.setId(2L);
        place2.setEnum_type(Enum_Type.CULTURA);
        place2.setTitle("Place Two");
        place2.setDetails("Details of place two.");
        place2.setPrice("Free");
        place2.setSchedule("Always open");
        place2.setAddress("456 Another St");
        place2.setCoordinates("http://placetwo.com");
        place2.setVr("http://placetwo-vr.com");
        place2.setWeb("http://placetwo-web.com");
        place2.setPhone("555-5678");
        place2.setRate(4.8); place2.setInformation("Information about place two.");
        place2.setBtn_url("http://placetwo-button.com");

        List<PlaceResponse> places = Arrays.asList(place1, place2);

        when(placeService.getAll()).thenReturn(places);

        MvcResult result = mockMvc.perform(get("/place")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(placeService).getAll();
        assertThat(result.getResponse().getContentAsString()).contains("Place One", "Place Two");
    }

    @Test
    public void testGetById() throws Exception {
        Long placeId = 1L;
        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setId(placeId);
        placeResponse.setTitle("Test Place");
        placeResponse.setAddress("Test Location");

        when(placeService.find(placeId)).thenReturn(placeResponse);

        MvcResult result = mockMvc.perform(get("/place/{id}", placeId) .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) .andReturn();

        verify(placeService).find(placeId);

        assertThat(result.getResponse().getContentAsString()).contains("1", "Test Place", "Test Location"); }

    @Test
    public void testFindByTitle() throws Exception {
        String placeTitle = "Test Place";
        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setId(1L);
        placeResponse.setTitle(placeTitle);
        placeResponse.setAddress("Test Location");

        when(placeService.findByPlaceName(placeTitle)).thenReturn(placeResponse);
        MvcResult result = mockMvc.perform(get("/place/findTitle/{title}", placeTitle) .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) .andReturn();

        verify(placeService).findByPlaceName(placeTitle);
        assertThat(result.getResponse().getContentAsString()).contains("1", placeTitle, "Test Location"); }

    @Test
    public void testGetListTitle() throws Exception {
        List<String> placeTitles = Arrays.asList("Place One", "Place Two", "Place Three");

        when(placeService.getListNamePlace()).thenReturn(placeTitles);

        MvcResult result = mockMvc.perform(get("/place/getListTitle") .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) .andReturn();

        verify(placeService).getListNamePlace();

        assertThat(result.getResponse().getContentAsString()).contains("Place One", "Place Two", "Place Three"); }

    @Test
    public void testInsertPlace() throws Exception { PlaceRequest placeRequest = new PlaceRequest();
        placeRequest.setEnum_type(Enum_Type.CULTURA);
        placeRequest.setTitle("Test Place Title");
        placeRequest.setDetails("This is a detailed description of the test place.");
        placeRequest.setPrice("100 USD");
        placeRequest.setSchedule("9 AM - 5 PM");
        placeRequest.setAddress("123 Test Street");
        placeRequest.setCoordinates("http://testlink.com");
        placeRequest.setVr("http://vrlink.com");
        placeRequest.setWeb("http://weblink.com");
        placeRequest.setPhone("123-456-7890");
        placeRequest.setRate(4.5);
        placeRequest.setInformation("This is additional information about the test place.");
        placeRequest.setBtn_url("http://buttonlink.com");

        PlaceResponse placeResponse = new PlaceResponse(); placeResponse.setId(1L);

        when(placeService.create(any(PlaceRequest.class))).thenReturn(placeResponse);

        MvcResult result = mockMvc.perform(post("/place") .contentType(MediaType.APPLICATION_JSON) .content(objectMapper.writeValueAsString(placeRequest))) .andExpect(status().isOk()) .andReturn();

        verify(placeService).create(any(PlaceRequest.class));
        assertThat(result.getResponse().getContentAsString()).contains("1"); }

    @Test
    public void testDeletePlace() throws Exception {
        Long placeId = 1L;
        doNothing().when(placeService).delete(placeId); mockMvc.perform(delete("/place/{id}", placeId) .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isNoContent());

        verify(placeService).delete(placeId); }

    @Test
    public void testUpdatePlace() throws Exception {
        Long placeId = 1L;
        PlaceRequest placeRequest = new PlaceRequest();
        placeRequest.setEnum_type(Enum_Type.CULTURA);
        placeRequest.setTitle("Updated Place Title");
        placeRequest.setDetails("This is an updated detailed description of the place.");
        placeRequest.setPrice("150 USD");
        placeRequest.setSchedule("10 AM - 6 PM");
        placeRequest.setAddress("456 Updated Street");
        placeRequest.setCoordinates("http://updatedlink.com");
        placeRequest.setVr("http://updated-vr.com");
        placeRequest.setWeb("http://updated-web.com");
        placeRequest.setPhone("987-654-3210"); placeRequest.setRate(4.7);
        placeRequest.setInformation("This is updated information about the place.");
        placeRequest.setBtn_url("http://updatedbuttonlink.com");

        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setId(placeId);

        when(placeService.update(any(Long.class), any(PlaceRequest.class))).thenReturn(placeResponse);

        MvcResult result = mockMvc.perform(put("/place/{id}", placeId) .contentType(MediaType.APPLICATION_JSON) .content(objectMapper.writeValueAsString(placeRequest))) .andExpect(status().isOk()) .andReturn();

        verify(placeService).update(any(Long.class), any(PlaceRequest.class));

        assertThat(result.getResponse().getContentAsString()).contains(placeId.toString()); }

    @Test
    public void testInsertList() throws Exception {
        PlaceRequest placeRequest1 = new PlaceRequest();
        placeRequest1.setEnum_type(Enum_Type.CULTURA);
        placeRequest1.setTitle("Place One");
        placeRequest1.setDetails("Details of place one.");
        placeRequest1.setPrice("Free");
        placeRequest1.setSchedule("Always open");
        placeRequest1.setAddress("123 Main St");
        placeRequest1.setCoordinates("http://placeone.com");
        placeRequest1.setVr("http://placeone-vr.com");
        placeRequest1.setWeb("http://placeone-web.com");
        placeRequest1.setPhone("555-1234");
        placeRequest1.setRate(4.5);
        placeRequest1.setInformation("Information about place one.");
        placeRequest1.setBtn_url("http://placeone-button.com");


    PlaceRequest placeRequest2 = new PlaceRequest();
    placeRequest2.setEnum_type(Enum_Type.CULTURA);
    placeRequest2.setTitle("Place Two");
    placeRequest2.setDetails("Details of place two.");
    placeRequest2.setPrice("Free");
    placeRequest2.setSchedule("Always open");
    placeRequest2.setAddress("456 Another St");
    placeRequest2.setCoordinates("http://placetwo.com");
    placeRequest2.setVr("http://placetwo-vr.com");
    placeRequest2.setWeb("http://placetwo-web.com");
    placeRequest2.setPhone("555-5678");
    placeRequest2.setRate(4.8);
    placeRequest2.setInformation("Information about place two.");
    placeRequest2.setBtn_url("http://placetwo-button.com");

    List<PlaceRequest> placeRequests = Arrays.asList(placeRequest1, placeRequest2);

    PlaceResponse placeResponse1 = new PlaceResponse();
    placeResponse1.setId(1L);
    PlaceResponse placeResponse2 = new PlaceResponse();
    placeResponse2.setId(2L);
    List<PlaceResponse> placeResponses = Arrays.asList(placeResponse1, placeResponse2);

    when(placeService.CreateList(anyList())).thenReturn(placeResponses);

    MvcResult result = mockMvc.perform(post("/place/list") .contentType(MediaType.APPLICATION_JSON) .content(objectMapper.writeValueAsString(placeRequests))) .andExpect(status().isOk()) .andReturn();

    verify(placeService).CreateList(anyList());

    assertThat(result.getResponse().getContentAsString()).contains("1", "2"); }

    @Test
    public void testGetIDByTitle() throws Exception {
        String placeTitle = "Test Place";
        Long placeId = 1L;

        when(placeService.getIdByTitle(placeTitle)).thenReturn(placeId);

        MvcResult result = mockMvc.perform(get("/place/getIdByTitle/{title}", placeTitle) .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) .andReturn();

        verify(placeService).getIdByTitle(placeTitle);

        assertThat(result.getResponse().getContentAsString()).contains(placeId.toString()); }

}
