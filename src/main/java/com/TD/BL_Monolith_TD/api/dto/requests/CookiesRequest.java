package com.TD.BL_Monolith_TD.api.dto.requests;

import com.TD.BL_Monolith_TD.util.enums.Iso_639_1;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CookiesRequest {
    @Size(max = 200, message = "You exceeded the number of characters allowed (200)")
    private String label_counter;
    @Size(max = 2, message = "You exceeded the number of characters allowed (2)")
    private Iso_639_1 language;
    @Size(max = 2000, message = "You exceeded the number of characters allowed (2000)")
    private String favorites;
    private String user_id;
}
