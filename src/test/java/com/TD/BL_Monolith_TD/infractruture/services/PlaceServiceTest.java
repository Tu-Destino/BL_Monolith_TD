package com.TD.BL_Monolith_TD.infractruture.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.TD.BL_Monolith_TD.api.dto.requests.PlaceRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.domain.entities.Place;
import com.TD.BL_Monolith_TD.domain.repositories.PlaceRepository;
import com.TD.BL_Monolith_TD.infrastructure.helpers.SupportService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.mappers.PlaceMapper;
import com.TD.BL_Monolith_TD.infrastructure.services.PlaceService;
import com.TD.BL_Monolith_TD.util.enums.Enum_Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;

public class PlaceServiceTest {

    @InjectMocks
    private PlaceService placeService;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private SupportService<Place> placeSupportService;
    @Mock
    private PlaceMapper placeMapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        PlaceRequest request = new PlaceRequest();
        request.setEnum_type(Enum_Type.NATURALEZA);
        request.setTitle("Parque Arví");
        request.setDetails("Es un impresionante parque natural...");
        request.setPrice("$5.200 - $23.000");
        request.setSchedule("8 AM - 6 PM");
        request.setAddress("Via a Piedras Blancas, Medellín, Antioquia");
        request.setCoordinates("https://maps.app.goo.gl/wHo7Dnnh4mj9EkcJA");
        request.setVr("https://www.google.com/maps/@6.2814291,-75.5026375");
        request.setWeb("https://www.parquearvi.org/");
        request.setPhone("(604) 4442979");
        request.setRate(4.7);
        request.setInformation("Descubierto en 1541 por el mariscal español...");
        request.setBtn_url("https://es.wikipedia.org/wiki/Parque_Arví");

        Place place = new Place();
        place.setEnum_type(Enum_Type.NATURALEZA);
        place.setTitle("Parque Arví");
        place.setDetails("Es un impresionante parque natural...");
        place.setPrice("$5.200 - $23.000");
        place.setSchedule("8 AM - 6 PM");
        place.setAddress("Via a Piedras Blancas, Medellín, Antioquia");
        place.setCoordinates("https://maps.app.goo.gl/wHo7Dnnh4mj9EkcJA");
        place.setVr("https://www.google.com/maps/@6.2814291,-75.5026375");
        place.setWeb("https://www.parquearvi.org/");
        place.setPhone("(604) 4442979");
        place.setRate(4.7);
        place.setInformation("Descubierto en 1541 por el mariscal español...");
        place.setBtn_url("https://es.wikipedia.org/wiki/Parque_Arví");

        Place savedPlace = new Place();
        savedPlace.setId(1L);
        savedPlace.setEnum_type(Enum_Type.NATURALEZA);
        savedPlace.setTitle("Parque Arví");
        savedPlace.setDetails("Es un impresionante parque natural...");
        savedPlace.setPrice("$5.200 - $23.000");
        savedPlace.setSchedule("8 AM - 6 PM");
        savedPlace.setAddress("Via a Piedras Blancas, Medellín, Antioquia");
        savedPlace.setCoordinates("https://maps.app.goo.gl/wHo7Dnnh4mj9EkcJA");
        savedPlace.setVr("https://www.google.com/maps/@6.2814291,-75.5026375");
        savedPlace.setWeb("https://www.parquearvi.org/");
        savedPlace.setPhone("(604) 4442979");
        savedPlace.setRate(4.7);
        savedPlace.setInformation("Descubierto en 1541 por el mariscal español...");
        savedPlace.setBtn_url("https://es.wikipedia.org/wiki/Parque_Arví");

        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setId(1L);
        placeResponse.setEnum_type(Enum_Type.NATURALEZA);
        placeResponse.setTitle("Parque Arví");
        placeResponse.setDetails("Es un impresionante parque natural...");
        placeResponse.setPrice("$5.200 - $23.000");
        placeResponse.setSchedule("8 AM - 6 PM");
        placeResponse.setAddress("Via a Piedras Blancas, Medellín, Antioquia");
        placeResponse.setCoordinates("https://maps.app.goo.gl/wHo7Dnnh4mj9EkcJA");
        placeResponse.setVr("https://www.google.com/maps/@6.2814291,-75.5026375");
        placeResponse.setWeb("https://www.parquearvi.org/");
        placeResponse.setPhone("(604) 4442979");
        placeResponse.setRate(4.7);
        placeResponse.setInformation("Descubierto en 1541 por el mariscal español...");
        placeResponse.setBtn_url("https://es.wikipedia.org/wiki/Parque_Arví");

        when(placeMapper.toEntity(request)).thenReturn(place);
        when(placeRepository.save(any(Place.class))).thenReturn(savedPlace);
        when(placeMapper.toResponse(any(Place.class))).thenReturn(placeResponse);

        PlaceResponse result = placeService.create(request);

        verify(placeMapper).toEntity(request);
        verify(placeRepository).save(place);
        verify(placeMapper).toResponse(savedPlace);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Parque Arví");
        assertThat(result.getRate()).isEqualTo(4.7);
    }

    @Test
    public void testGetAll(){
        Place place1=new Place();
        place1.setId(1L);
        place1.setTitle("place 1");

        Place place2=new Place();
        place2.setId(2L);
        place2.setTitle("place 2");

        List<Place> places = Arrays.asList(place1,place2);

        PlaceResponse placeResponse1 = new PlaceResponse();
        placeResponse1.setId(1L);
        placeResponse1.setTitle("place 1");

        PlaceResponse placeResponse2 = new PlaceResponse();
        placeResponse2.setId(2L);
        placeResponse2.setTitle("place 2");

        List<PlaceResponse> placeResponses = Arrays.asList(placeResponse1,placeResponse2);

        when(placeRepository.findAll()).thenReturn(places);
        when(placeMapper.toListResponse(places)).thenReturn(placeResponses);
        List<PlaceResponse> result = placeService.getAll();

        assertThat(result).isEqualTo(placeResponses);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getTitle()).isEqualTo("place 1");
        assertThat(result.get(1).getTitle()).isEqualTo("place 2");

    }

    @Test
    public void testFind(){
        Long placeId = 1L;

        Place place = new Place();
        place.setId(1L);
        place.setTitle("Place 1");

        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setId(1L);
        placeResponse.setTitle("Place 1");

        when(placeSupportService.findByID(eq(placeRepository), eq(placeId), eq("Place"))) .thenReturn(place);
        when(placeMapper.toResponse(place)).thenReturn(placeResponse);

        PlaceResponse result = placeService.find(placeId);

        verify(placeSupportService).findByID(eq(placeRepository), eq(placeId), eq("Place"));

        verify(placeMapper).toResponse(place);

        assertThat(result).isEqualTo(placeResponse);
        assertThat(result.getId()).isEqualTo(placeId);
        assertThat(result.getTitle()).isEqualTo("Place 1");
    }

    @Test
    public void testUpdate() {
        Long placeId = 1L;
        PlaceRequest request = new PlaceRequest();
        request.setRate(4.7);
        request.setTitle("Update Place");

        Place place = new Place();
        place.setId(placeId);
        request.setRate(3.1);
        request.setTitle("Original Place");

        Place updatedPlace = new Place();
        BeanUtils.copyProperties(request, updatedPlace);
        updatedPlace.setId(placeId);

        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setId(placeId);
        placeResponse.setRate(4.7);
        placeResponse.setTitle("Updated Place");

        when(placeSupportService.findByID(eq(placeRepository), eq(placeId), eq("Place"))) .thenReturn(place);
        when(placeRepository.save(any(Place.class))).thenReturn(updatedPlace);
        when(placeMapper.toResponse(updatedPlace)).thenReturn(placeResponse);

        PlaceResponse result = placeService.update(placeId, request);

        verify(placeSupportService).findByID(eq(placeRepository), eq(placeId), eq("Place"));
        verify(placeRepository).save(place);
        verify(placeMapper).toResponse(updatedPlace);

        assertThat(result).isEqualTo(placeResponse);
        assertThat(result.getId()).isEqualTo(placeId);
        assertThat(result.getRate()).isEqualTo(4.7);
        assertThat(result.getTitle()).isEqualTo("Updated Place");}

    @Test
    public void testDelete() {
        Long placeId = 1L;

        Place place = new Place();
        place.setId(placeId);

        when(placeSupportService.findByID(eq(placeRepository), eq(placeId), eq("Place"))) .thenReturn(place);
        placeService.delete(placeId);

        verify(placeSupportService).findByID(eq(placeRepository), eq(placeId), eq("Place"));
        verify(placeRepository).delete(place); }

    @Test
    public void testGetListNamePlace() {
        List<String> expectedTitles = Arrays.asList("Place 1", "Place 2", "Place 3");

        when(placeRepository.findAllTitles()).thenReturn(expectedTitles);

        List<String> result = placeService.getListNamePlace();

        verify(placeRepository).findAllTitles();

        assertThat(result).isEqualTo(expectedTitles); }

    @Test
    public void testFindByPlaceName() {
        String placeName = "Place 1";

        Place place = new Place();
        place.setId(1L);
        place.setTitle(placeName);

        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setId(1L);
        placeResponse.setTitle(placeName);

        when(placeRepository.findByTitle(eq(placeName))).thenReturn(place);
        when(placeMapper.toResponse(place)).thenReturn(placeResponse);

        PlaceResponse result = placeService.findByPlaceName(placeName);

        verify(placeRepository).findByTitle(eq(placeName));
        verify(placeMapper).toResponse(place);

        assertThat(result).isEqualTo(placeResponse);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo(placeName); }

    @Test
    public void testCreateList() {
        PlaceRequest placeRequest1 = new PlaceRequest();
        placeRequest1.setTitle("Place 1");

        PlaceRequest placeRequest2 = new PlaceRequest();
        placeRequest2.setTitle("Place 2");

        List<PlaceRequest> placeRequests = Arrays.asList(placeRequest1, placeRequest2);

        Place place1 = new Place();
        place1.setId(1L);
        place1.setTitle("Place 1");

        Place place2 = new Place();
        place2.setId(2L);
        place2.setTitle("Place 2");

        PlaceResponse placeResponse1 = new PlaceResponse();
        placeResponse1.setId(1L);
        placeResponse1.setTitle("Place 1");

        PlaceResponse placeResponse2 = new PlaceResponse();
        placeResponse2.setId(2L);
        placeResponse2.setTitle("Place 2");

        when(placeMapper.toEntity(placeRequest1)).thenReturn(place1);
        when(placeMapper.toEntity(placeRequest2)).thenReturn(place2);
        when(placeRepository.save(place1)).thenReturn(place1);
        when(placeRepository.save(place2)).thenReturn(place2);
        when(placeMapper.toResponse(place1)).thenReturn(placeResponse1);
        when(placeMapper.toResponse(place2)).thenReturn(placeResponse2);

        List<PlaceResponse> result = placeService.CreateList(placeRequests);

        verify(placeMapper).toEntity(placeRequest1);
        verify(placeMapper).toEntity(placeRequest2);
        verify(placeRepository).save(place1);
        verify(placeRepository).save(place2);
        verify(placeMapper).toResponse(place1);
        verify(placeMapper).toResponse(place2);

        assertThat(result).containsExactlyInAnyOrder(placeResponse1, placeResponse2); }

    @Test
    public void testGetIdByTitle() {
        String title = "Place 1";
        Long expectedId = 1L;

        when(placeRepository.findIdByTitle(eq(title))).thenReturn(expectedId);

        Long result = placeService.getIdByTitle(title);

        verify(placeRepository).findIdByTitle(eq(title));

        assertThat(result).isEqualTo(expectedId);
    }

}


