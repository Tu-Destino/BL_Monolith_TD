package com.TD.BL_Monolith_TD.api.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String comment;
    private LocalDate date;
    private UserResponse user;
    private PlaceResponse place;
}
