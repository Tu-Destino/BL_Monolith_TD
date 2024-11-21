package com.TD.BL_Monolith_TD.infractruture.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.TD.BL_Monolith_TD.api.dto.requests.PublicationRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PublicationResponse;
import com.TD.BL_Monolith_TD.domain.entities.Publication;
import com.TD.BL_Monolith_TD.domain.entities.User;
import com.TD.BL_Monolith_TD.domain.repositories.PublicationRepository;
import com.TD.BL_Monolith_TD.domain.repositories.UserRepository;
import com.TD.BL_Monolith_TD.infrastructure.helpers.SupportService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.mappers.PublicationMapper;
import com.TD.BL_Monolith_TD.infrastructure.services.PublicationService;
import com.TD.BL_Monolith_TD.util.enums.RoleUser;
import com.TD.BL_Monolith_TD.util.enums.StatusPublication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PublicationServiceTest {

    @InjectMocks
    private PublicationService publicationService;

    @Mock
    private PublicationRepository publicationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PublicationMapper publicationMapper;

    @Mock(name = "SP")
    private SupportService<Publication> publicationSupport;

    @Mock(name = "SU")
    private SupportService<User> UserSupport;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
//
//    @Test
//    public void testCreate() {
//        PublicationRequest publicationRequest = new PublicationRequest();
//        publicationRequest.setUser_id("some-unique-uuid");
//
//        User user = new User();
//        user.setId("some-unique-uuid");
//
//        Publication publication = new Publication();
//        publication.setUser(user);
//        publication.setEnum_status(StatusPublication.PENDIENTE);
//        publication.setPublication_date(LocalDateTime.now());
//
//        PublicationResponse publicationResponse = new PublicationResponse();
//
//        when(UserSupport.findByUUID(eq(userRepository), eq("some-unique-uuid"), eq("User"))).thenReturn(user);
//        when(publicationMapper.toEntity(publicationRequest)).thenReturn(publication);
//        when(publicationRepository.save(any(Publication.class))).thenReturn(publication);
//        when(publicationMapper.toResponse(any(Publication.class))).thenReturn(publicationResponse);
//
//        PublicationResponse result = publicationService.create(publicationRequest);
//
//        verify(UserSupport).findByUUID(eq(userRepository), eq("some-unique-uuid"), eq("User"));
//        verify(publicationMapper).toEntity(publicationRequest);
//        verify(publicationRepository).save(publication);
//        verify(publicationMapper).toResponse(publication);
//
//        assertThat(result).isEqualTo(publicationResponse);
//    }

//    @Test
//    public void testUpdate() {
//        Long publicationId = 1L;
//        PublicationRequest publicationRequest = new PublicationRequest();
//        publicationRequest.setUser_id("new-user-id");
//
//        User newUser = new User();
//        newUser.setId("new-user-id");
//
//        User currentUser = new User();
//        currentUser.setId("current-user-id");
//
//        Publication publication = new Publication();
//        publication.setUser(currentUser);
//        publication.setUser(new User( "sss" ,"User1","test@example.com" ,"test", RoleUser.USER, new ArrayList<>(),new ArrayList<>()));
//
//        PublicationResponse publicationResponse = new PublicationResponse();
//
//        when(publicationSupport.findByID(eq(publicationRepository), eq(publicationId), eq("Publication"))).thenReturn(publication);
//        when(UserSupport.findByUUID(eq(userRepository), eq("new-user-id"), eq("User"))).thenReturn(newUser);
//        when(publicationRepository.save(any(Publication.class))).thenReturn(publication);
//        when(publicationMapper.toResponse(any(Publication.class))).thenReturn(publicationResponse);
//        PublicationResponse result = publicationService.update(publicationId, publicationRequest);
//        verify(publicationSupport).findByID(eq(publicationRepository), eq(publicationId), eq("Publication"));
//        verify(UserSupport).findByUUID(eq(userRepository), eq("new-user-id"), eq("User"));
//        verify(publicationRepository).save(publication);
//        verify(publicationMapper).toResponse(publication);
//
//        assertThat(result).isEqualTo(publicationResponse); }
}
