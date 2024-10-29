package com.TD.BL_Monolith_TD.infrastructure.helpers.mappers;

import java.util.List;

public interface GenericMapper <Request, Response, Entity>{
    Entity toEntity(Request request);
    Response toResponse(Entity entity);
    List<Response> toListResponse(List<Entity> entityList);
}
