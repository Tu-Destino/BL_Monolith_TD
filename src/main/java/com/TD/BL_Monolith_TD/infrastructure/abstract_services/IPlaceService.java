package com.TD.BL_Monolith_TD.infrastructure.abstract_services;

import com.TD.BL_Monolith_TD.api.dto.requests.PlaceRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.api.dto.response.SearchListPlaceResponse;

public interface IPlaceService extends CrudService<PlaceRequest, PlaceResponse, Long> {
    SearchListPlaceResponse getListNamePlace();
    PlaceResponse findByPlaceName(String placeName);
}
