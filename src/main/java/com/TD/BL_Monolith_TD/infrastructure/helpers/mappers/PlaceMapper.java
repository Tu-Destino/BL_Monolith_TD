package com.TD.BL_Monolith_TD.infrastructure.helpers.mappers;

import com.TD.BL_Monolith_TD.api.dto.requests.PlaceRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PlaceResponse;
import com.TD.BL_Monolith_TD.domain.entities.Place;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlaceMapper extends  GenericMapper<PlaceRequest, PlaceResponse, Place>{
}
