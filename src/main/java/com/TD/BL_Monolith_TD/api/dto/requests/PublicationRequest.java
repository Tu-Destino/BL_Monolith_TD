package com.TD.BL_Monolith_TD.api.dto.requests;

import com.TD.BL_Monolith_TD.util.enums.StatusPublication;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicationRequest {
    @Size(max = 250, message = "You exceeded the number of characters allowed (250)")
    @NotBlank(message = "Required title!")
    private String title;
    @Size(max = 1000, message = "You exceeded the number of characters allowed (1000)")
    private String description;
    @Size(max = 300, message = "You exceeded the number of characters allowed (300)")
    @NotBlank(message = "Required tags!")
    private String tags;
    @Size(max = 500, message = "You exceeded the number of characters allowed (500)")
    @NotBlank(message = "Required URL image!")
    private String url_img;
    @NotBlank(message = "Required User id!")
    private String user_id;
    @NotNull(message = "Required status!")
    private StatusPublication enum_status;
}
