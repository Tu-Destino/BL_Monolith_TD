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
public class CommentRequest {
    @NotBlank(message = "This attribute is required")
    @Size(min = 20, max =2000,message = "Comment must be more than 50 characters")
    private String comment;
    @NotNull(message = "This attribute is required")
    private String user_id;
    @NotNull(message = "This attribute is required")
    private Long place_id;
}
