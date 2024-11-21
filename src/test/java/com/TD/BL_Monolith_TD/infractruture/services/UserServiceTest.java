package com.TD.BL_Monolith_TD.infractruture.services;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.TD.BL_Monolith_TD.api.dto.requests.UserRequest;
import com.TD.BL_Monolith_TD.api.dto.response.UserResponse;
import com.TD.BL_Monolith_TD.domain.entities.User;
import com.TD.BL_Monolith_TD.domain.repositories.UserRepository;
import com.TD.BL_Monolith_TD.infrastructure.helpers.SupportService;
import com.TD.BL_Monolith_TD.infrastructure.helpers.mappers.UserMapper;
import com.TD.BL_Monolith_TD.infrastructure.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private SupportService<User> userSupportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        UserRequest userRequest = new UserRequest();

        User userEntity = new User();

        UserResponse userResponse = new UserResponse();

        when(userMapper.toEntity(userRequest)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toResponse(userEntity)).thenReturn(userResponse);

        UserResponse result = userService.create(userRequest);

        verify(userMapper).toEntity(userRequest);
        verify(userRepository).save(userEntity);
        verify(userMapper).toResponse(userEntity);

        assertThat(result).isEqualTo(userResponse);
    }

    @Test
    public void testUpdate() {
        String userId = "some-unique-uuid";
        UserRequest userRequest = new UserRequest();
        User userEntity = new User();
        userEntity.setId(userId);
        UserResponse userResponse = new UserResponse();

        when(userSupportService.findByUUID(eq(userRepository), eq(userId), eq("User"))).thenReturn(userEntity);
        when(userRepository.save(any(User.class))).thenReturn(userEntity);
        when(userMapper.toResponse(userEntity)).thenReturn(userResponse);

        UserResponse result = userService.update(userId, userRequest);

        verify(userSupportService).findByUUID(eq(userRepository), eq(userId), eq("User")); verify(userRepository).save(userEntity);
        verify(userMapper).toResponse(userEntity);

        assertThat(result).isEqualTo(userResponse); }

    @Test
    public void testGetAll() {
        User user1 = new User();
        user1.setId("1");
        User user2 = new User();
        user2.setId("2");

        List<User> users = Arrays.asList(user1, user2);
        UserResponse response1 = new UserResponse();
        response1.setId("1");
        UserResponse response2 = new UserResponse();
        response2.setId("2");

        List<UserResponse> expectedResponses = Arrays.asList(response1, response2);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toListResponse(users)).thenReturn(expectedResponses);

        List<UserResponse> result = userService.getAll();

        verify(userRepository).findAll();
        verify(userMapper).toListResponse(users);

        assertThat(result).isEqualTo(expectedResponses); }

    @Test
    public void testFind() {
        String userId = "some-unique-uuid";
        User userEntity = new User();
        userEntity.setId(userId);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);

        when(userSupportService.findByUUID(eq(userRepository), eq(userId), eq("User"))).thenReturn(userEntity);
        when(userMapper.toResponse(userEntity)).thenReturn(userResponse);

        UserResponse result = userService.find(userId);

        verify(userSupportService).findByUUID(eq(userRepository), eq(userId), eq("User"));
        verify(userMapper).toResponse(userEntity);

        assertThat(result).isEqualTo(userResponse); }

    @Test
    public void testDelete() {
        String userId = "some-unique-uuid";
        User userEntity = new User();
        userEntity.setId(userId);

        when(userSupportService.findByUUID(eq(userRepository), eq(userId), eq("User"))).thenReturn(userEntity);

        userService.delete(userId);

        verify(userSupportService).findByUUID(eq(userRepository), eq(userId), eq("User"));
        verify(userRepository).delete(userEntity);
    }
}
