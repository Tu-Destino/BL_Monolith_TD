package com.TD.BL_Monolith_TD.infrastructure.helpers.mappers;

import com.TD.BL_Monolith_TD.api.dto.requests.CommentRequest;
import com.TD.BL_Monolith_TD.api.dto.response.CommentResponse;
import com.TD.BL_Monolith_TD.domain.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper extends GenericMapper<CommentRequest, CommentResponse, Comment>{
}
