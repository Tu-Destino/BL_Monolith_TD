package com.TD.BL_Monolith_TD.infrastructure.abstract_services;

import com.TD.BL_Monolith_TD.api.dto.requests.CommentRequest;
import com.TD.BL_Monolith_TD.api.dto.response.CommentResponse;

import java.util.List;

public interface ICommentService extends CrudService<CommentRequest, CommentResponse,Long>{
 public List<CommentResponse> getAllByIdPlace (Long idPLace);
}
