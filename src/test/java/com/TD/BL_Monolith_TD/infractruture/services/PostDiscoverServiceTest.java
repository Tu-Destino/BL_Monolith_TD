package com.TD.BL_Monolith_TD.infractruture.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.TD.BL_Monolith_TD.api.dto.requests.LabelsRequest;
import com.TD.BL_Monolith_TD.api.dto.requests.PostDiscoverRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PostDiscoverResponse;
import com.TD.BL_Monolith_TD.domain.entities.Place;
import com.TD.BL_Monolith_TD.domain.entities.PostDiscover;
import com.TD.BL_Monolith_TD.domain.entities.User;
import com.TD.BL_Monolith_TD.domain.repositories.PlaceRepository;
import com.TD.BL_Monolith_TD.domain.repositories.PostDiscoverRepository;
import com.TD.BL_Monolith_TD.domain.repositories.UserRepository;

import com.TD.BL_Monolith_TD.infrastructure.helpers.SupportService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.mappers.PostDiscoverMapper;
import com.TD.BL_Monolith_TD.infrastructure.services.PostDiscoverService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class PostDiscoverServiceTest {

    @InjectMocks
    private PostDiscoverService postDiscoverService;

    @Mock
    private PostDiscoverRepository postDiscoverRepository;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private UserRepository userRepository;
    @Mock(name = "SPA")
    private SupportService<Place> placeSupportService;
    @Mock(name = "SUP" )
    private SupportService<User> userSupportService;

    @Mock(name = "Spo")
    private SupportService<PostDiscover> postDiscoverSupport;

    @Mock
    private PostDiscoverMapper postDiscoverMapper;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;
    @BeforeEach public void setUp() {
        MockitoAnnotations.openMocks(this);

    }
//    @Test
//    public void testFindByTags() {
//        // Arrange
//        LabelsRequest labelsRequest = new LabelsRequest();
//        labelsRequest.setArray("Historia,Cultura");
//
//        PostDiscover postDiscover1 = new PostDiscover();
//        postDiscover1.setId(UUID.randomUUID().toString());
//        postDiscover1.setTags("Historia,Arte");
//
//        PostDiscover postDiscover2 = new PostDiscover();
//        postDiscover2.setId(UUID.randomUUID().toString());
//        postDiscover2.setTags("Cultura,Monumento");
//
//        List<Object[]> postDiscovers = Arrays.asList(
//                new Object[]{postDiscover1, 2},
//                new Object[]{postDiscover2, 2}
//        );
//
//        PostDiscoverResponse response1 = new PostDiscoverResponse();
//        response1.setId(postDiscover1.getId());
//
//        PostDiscoverResponse response2 = new PostDiscoverResponse();
//        response2.setId(postDiscover2.getId());
//
//        List<PostDiscoverResponse> expectedResponses = Arrays.asList(response1, response2);
//
//
//
//        // Mocking
//        when(entityManager.createQuery(argThat((ArgumentMatcher<String>) query -> query.startsWith("SELECT p")))).thenReturn(query);
//        when(query.setParameter(eq("tag0"), eq("%Historia%"))).thenReturn(query);
//        when(query.setParameter(eq("tag1"), eq("%Cultura%"))).thenReturn(query);
//        when(query.getResultList()).thenReturn(postDiscovers);
//        when(postDiscoverMapper.toListResponse(anyList())).thenReturn(expectedResponses);
//
//        // Act
//        List<PostDiscoverResponse> result = postDiscoverService.findByTags(labelsRequest);
//// Assert
//        (entityManager).createQuery(argThat((ArgumentMatcher<String>) query -> query.startsWith("SELECT p")));
//        verify(query).setParameter(eq("tag0"), eq("%Historia%"));
//        verify(query).setParameter(eq("tag1"), eq("%Cultura%"));
//        verify(query).getResultList();
//        verify(postDiscoverMapper).toListResponse(anyList());
//
//        assertThat(result).isEqualTo(expectedResponses);
//    }


//    @Test
//    public void testCreate() {
//        String userId = UUID.randomUUID().toString();
//        String postDiscoverId = UUID.randomUUID().toString();
//        Long placeId = 1L;
//        PostDiscoverRequest request = new PostDiscoverRequest();
//        request.setPlace_id(placeId);
//        request.setUser_id(userId);
//
//        Place place = new Place();
//        place.setId(placeId);
//
//        User user = new User();
//        user.setId(userId);
//
//        PostDiscover postDiscover = new PostDiscover();
//
//        PostDiscover savedPostDiscover = new PostDiscover();
//        savedPostDiscover.setId(postDiscoverId);
//
//        PostDiscoverResponse postDiscoverResponse = new PostDiscoverResponse();
//        postDiscoverResponse.setId(postDiscoverId);
//
//        when(placeSupportService.findByID(eq(placeRepository), eq(placeId), eq("Place"))).thenReturn(place);
//        when(userSupportService.findByUUID(eq(userRepository), eq(userId), eq("User"))).thenReturn(user);
//        when(postDiscoverMapper.toEntity(request)).thenReturn(postDiscover);
//        when(postDiscoverRepository.save(any(PostDiscover.class))).thenReturn(savedPostDiscover);
//        when(postDiscoverMapper.toResponse(savedPostDiscover)).thenReturn(postDiscoverResponse);
//
//        PostDiscoverResponse result = postDiscoverService.create(request);
//
//        verify(placeSupportService).findByID(eq(placeRepository),eq(placeId),eq("Place"));
//
//      verify(userSupportService).findByUUID(eq(userRepository), eq(userId), eq("User"));
//        verify(postDiscoverMapper).toEntity(request);
//        verify(postDiscoverRepository).save(postDiscover);
//        verify(postDiscoverMapper).toResponse(savedPostDiscover);
//
//        assertThat(result).isEqualTo(postDiscoverResponse);
//        assertThat(result.getId()).isEqualTo(postDiscoverId);
//    }

    @Test
    public void testGetAll() {
        PostDiscover postDiscover1 = new PostDiscover();
        postDiscover1.setId(UUID.randomUUID().toString());

        PostDiscover postDiscover2 = new PostDiscover();
        postDiscover2.setId(UUID.randomUUID().toString());

        List<PostDiscover> postDiscovers = Arrays.asList(postDiscover1, postDiscover2);

        PostDiscoverResponse response1 = new PostDiscoverResponse();
        response1.setId(postDiscover1.getId());

        PostDiscoverResponse response2 = new PostDiscoverResponse();
        response2.setId(postDiscover2.getId());

        List<PostDiscoverResponse> expectedResponses = Arrays.asList(response1, response2);

        when(postDiscoverRepository.findAll()).thenReturn(postDiscovers);
        when(postDiscoverMapper.toListResponse(postDiscovers)).thenReturn(expectedResponses);

        List<PostDiscoverResponse> result = postDiscoverService.getAll();

        verify(postDiscoverRepository).findAll();
        verify(postDiscoverMapper).toListResponse(postDiscovers);

        assertThat(result).isEqualTo(expectedResponses);
    }

    @Test
    public void testGetTags() {
        List<String> tagsFromRepo = Arrays.asList("tag1,tag2", "tag2,tag3", "tag4,tag1");

        List<String> expectedTags = Arrays.asList("tag1", "tag2", "tag3", "tag4");

        when(postDiscoverRepository.findAllTags()).thenReturn(tagsFromRepo);

        List<String> result = postDiscoverService.getTags();

        verify(postDiscoverRepository).findAllTags();

        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedTags);
    }

    @Test
    public void testGetUrlImgByTitle() {
        String title = "Some Title";
        List<String> expectedUrls = Arrays.asList("url1.jpg", "url2.jpg", "url3.jpg");
        String userId = "0d215f49-a3f4-4165-bf52-b42649bc85c3";

        when(postDiscoverRepository.findAllUrlImgOrderByUserId(title, userId)).thenReturn(expectedUrls);

        List<String> result = postDiscoverService.getUrlImgByTitle(title);

        verify(postDiscoverRepository).findAllUrlImgOrderByUserId(title, userId);

        assertThat(result).isEqualTo(expectedUrls);
    }

//    @Test
//    public void testGetCombinations() {
//        int n = 4;
//        int k = 2;
//
//        List<List<Integer>> expectedCombinations = new ArrayList<>();
//        expectedCombinations.add(Arrays.asList(0, 1));
//        expectedCombinations.add(Arrays.asList(0, 2));
//        expectedCombinations.add(Arrays.asList(0, 3));
//        expectedCombinations.add(Arrays.asList(1, 2));
//        expectedCombinations.add(Arrays.asList(1, 3));
//        expectedCombinations.add(Arrays.asList(2, 3));
//
//
//        List<List<Integer>> result = postDiscoverService.getCombinations(n, k);
//
//        assertThat(result).isEqualTo(expectedCombinations);
//    }


//    @Test
//    public void testDelete() {
//        String postId = UUID.randomUUID().toString();
//
//        PostDiscover postDiscover = new PostDiscover();
//        postDiscover.setId(postId);
//
//        when(postDiscoverSupport.findByUUID(postDiscoverRepository, postId, "PostDiscover")).thenReturn(postDiscover);
//
//        postDiscoverService.delete(postId);
//
//        verify(postDiscoverSupport).findByUUID(postDiscoverRepository, postId, "PostDiscover");
//        verify(postDiscoverRepository).delete(postDiscover);
//    }

//    @Test
//    public void testFind() {
//        String postId = UUID.randomUUID().toString();
//
//        PostDiscover postDiscover = new PostDiscover();
//        postDiscover.setId(postId);
//
//        PostDiscoverResponse postDiscoverResponse = new PostDiscoverResponse();
//        postDiscoverResponse.setId(postId);
//
//        when(postDiscoverSupport.findByUUID(postDiscoverRepository, postId, "PostDiscover")).thenReturn(postDiscover);
//        when(postDiscoverMapper.toResponse(postDiscover)).thenReturn(postDiscoverResponse);
//
//        PostDiscoverResponse result = postDiscoverService.find(postId);
//
//        verify(postDiscoverSupport).findByUUID(postDiscoverRepository, postId, "PostDiscover");
//        verify(postDiscoverMapper).toResponse(postDiscover);
//
//        assertThat(result).isEqualTo(postDiscoverResponse);
//        assertThat(result.getId()).isEqualTo(postId);
//    }

//    @Test
//    public void testUpdate() {
//        String postId = UUID.randomUUID().toString();
//        String userId = UUID.randomUUID().toString();
//        Long placeId = 1L;
//
//        PostDiscoverRequest request = new PostDiscoverRequest();
//        request.setUser_id(userId);
//        request.setPlace_id(placeId);
//
//        PostDiscover postDiscover = new PostDiscover();
//        postDiscover.setId(postId);
//
//        User oldUser = new User();
//        oldUser.setId(UUID.randomUUID().toString());
//        postDiscover.setUser(oldUser);
//
//        Place oldPlace = new Place();
//        oldPlace.setId(2L);
//        postDiscover.setPlace(oldPlace);
//
//        User newUser = new User();
//        newUser.setId(userId);
//
//        Place newPlace = new Place();
//        newPlace.setId(placeId);
//
//        PostDiscover updatedPostDiscover = new PostDiscover();
//        updatedPostDiscover.setId(postId);
//        BeanUtils.copyProperties(request, updatedPostDiscover);
//        updatedPostDiscover.setUser(newUser);
//        updatedPostDiscover.setPlace(newPlace);
//
//        PostDiscoverResponse postDiscoverResponse = new PostDiscoverResponse();
//        postDiscoverResponse.setId(postId);
//
//        when(postDiscoverSupport.findByUUID(postDiscoverRepository, postId, "PostDiscover")).thenReturn(postDiscover);
//        when(userSupportService.findByUUID(userRepository, userId, "User")).thenReturn(newUser);
//        when(placeSupportService.findByID(placeRepository, placeId, "Place")).thenReturn(newPlace);
//        when(postDiscoverRepository.save(any(PostDiscover.class))).thenReturn(updatedPostDiscover);
//        when(postDiscoverMapper.toResponse(updatedPostDiscover)).thenReturn(postDiscoverResponse);
//
//        PostDiscoverResponse result = postDiscoverService.update(postId, request);
//
//        verify(postDiscoverSupport).findByUUID(postDiscoverRepository, postId, "PostDiscover");
//        verify(userSupportService).findByUUID(userRepository, userId, "User");
//        verify(placeSupportService).findByID(placeRepository, placeId, "Place");
//        verify(postDiscoverRepository).save(postDiscover);
//        verify(postDiscoverMapper).toResponse(updatedPostDiscover);
//
//        assertThat(result).isEqualTo(postDiscoverResponse);
//        assertThat(result.getId()).isEqualTo(postId);
//    }

}
