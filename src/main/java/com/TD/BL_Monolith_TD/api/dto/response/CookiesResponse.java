package com.TD.BL_Monolith_TD.api.dto.response;

import com.TD.BL_Monolith_TD.util.enums.Iso_639_1;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CookiesResponse {

    private String id;
    private String label_counter;
    private Iso_639_1 language;
    private String favorites;

    private UserResponse user;
}
