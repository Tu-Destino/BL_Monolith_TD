package com.TD.BL_Monolith_TD.infrastructure.helpers.mappers;

import com.TD.BL_Monolith_TD.api.dto.requests.PostDiscoverRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PostDiscoverResponse;
import com.TD.BL_Monolith_TD.domain.entities.PostDiscover;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostDiscoverMapper extends GenericMapper<PostDiscoverRequest, PostDiscoverResponse, PostDiscover>{
}
