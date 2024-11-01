package com.TD.BL_Monolith_TD.infrastructure.abstract_services;

import com.TD.BL_Monolith_TD.api.dto.requests.LabelsRequest;
import com.TD.BL_Monolith_TD.api.dto.requests.PostDiscoverRequest;
import com.TD.BL_Monolith_TD.api.dto.response.PostDiscoverResponse;

import java.util.List;

public interface IPostDiscoverService extends CrudService<PostDiscoverRequest, PostDiscoverResponse,String> {
    List<PostDiscoverResponse> findByTags(LabelsRequest labelsRequest);
    List<String> getTags();
}
