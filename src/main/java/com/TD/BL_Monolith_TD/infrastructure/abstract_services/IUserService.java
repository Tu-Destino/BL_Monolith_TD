package com.TD.BL_Monolith_TD.infrastructure.abstract_services;

import com.TD.BL_Monolith_TD.api.dto.requests.UserRequest;
import com.TD.BL_Monolith_TD.api.dto.response.UserResponse;

public interface IUserService extends CrudService<UserRequest, UserResponse, String>{
}
