package com.TD.BL_Monolith_TD.infrastructure.abstract_services;

import com.TD.BL_Monolith_TD.api.dto.requests.PlaceRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.api.dto.response.SearchListPlaceResponse;

import java.util.List;

public interface IPlaceService extends CrudService<PlaceRequest, PlaceResponse, Long> {
    List<String> getListNamePlace();
    PlaceResponse findByPlaceName(String placeName);
    List<PlaceResponse> CreateList(List<PlaceRequest> requests);
    Long getIdByTitle(String title );
}
