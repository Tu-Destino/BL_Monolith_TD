package com.TD.BL_Monolith_TD.infrastructure.helpers.mappers;

import com.TD.BL_Monolith_TD.api.dto.requests.PublicationRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PublicationResponse;
import com.TD.BL_Monolith_TD.domain.entities.Publication;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PublicationMapper extends GenericMapper<PublicationRequest, PublicationResponse, Publication>{
}
