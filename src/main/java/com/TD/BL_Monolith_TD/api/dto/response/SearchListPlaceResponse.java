package com.TD.BL_Monolith_TD.api.dto.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchListPlaceResponse {
    private List<String> listPlace;
}
