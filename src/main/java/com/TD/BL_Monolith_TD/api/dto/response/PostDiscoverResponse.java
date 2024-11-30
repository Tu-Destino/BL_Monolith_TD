package com.TD.BL_Monolith_TD.api.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDiscoverResponse implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private String id;
    private String title;
    private String description;
    private String tags;
    private String urlImg;
}
