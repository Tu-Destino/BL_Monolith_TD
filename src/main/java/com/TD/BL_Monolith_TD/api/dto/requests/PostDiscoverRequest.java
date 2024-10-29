package com.TD.BL_Monolith_TD.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDiscoverRequest {
    @Size(max = 250,message = "You exceeded the number of characters allowed (250)")
    @NotBlank(message = "The title of the post is required")
    private String title;
    @Size(max = 1000,message = "You exceeded the number of characters allowed (1000)")
    private String description;
    @Size(max = 300,message = "SuperaYou exceeded the number of characters allowed (300)")
    @NotBlank(message = "The labels in the post are required")
    private String tags;
    @Size(max = 500,message = "You exceeded the number of characters allowed (500)")
    @NotBlank(message = "The url image is required")
    private String urlImg;
    @NotNull(message = "The post location is required")
    private Long place_id;
    @NotNull(message = "The user who made the post is required")
    private String user_id;
}
