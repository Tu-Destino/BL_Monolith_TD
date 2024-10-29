package com.TD.BL_Monolith_TD.infrastructure.abstract_services;

import com.TD.BL_Monolith_TD.api.dto.requests.CookiesRequest;
import com.TD.BL_Monolith_TD.api.dto.response.CookiesResponse;

public interface ICookiesService extends CrudService<CookiesRequest, CookiesResponse,String>{
    public CookiesResponse getById(String id);
}
