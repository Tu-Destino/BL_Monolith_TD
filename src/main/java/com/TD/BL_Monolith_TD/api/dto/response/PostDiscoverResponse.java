package com.TD.BL_Monolith_TD.api.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDiscoverResponse {
    private String id;
    private String title;
    private String description;
    private String tags;
    private String urlImg;
}
