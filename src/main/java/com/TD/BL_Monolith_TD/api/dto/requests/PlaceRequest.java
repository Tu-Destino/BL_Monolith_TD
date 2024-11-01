package com.TD.BL_Monolith_TD.api.dto.requests;

import com.TD.BL_Monolith_TD.util.enums.Enum_Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequest {
    @NotNull(message = "The place type can't be null")
    private Enum_Type enum_type;

    @Size(max = 100, message = "You exceeded the number of characters allowed (100)")
    @NotBlank(message = "This attribute is required")
    private String title;

    @Size(min = 20, message = "The details must be more than 20 characters")
    @Size(max = 2000, message = "You exceeded the number of characters allowed (2000)")
    @NotBlank(message = "This attribute is required")
    private String details;

    @Size(max = 100, message = "You exceeded the number of characters allowed (100)")
    @NotBlank(message = "This attribute is required")
    private String price;

    @Size(max = 100, message = "You exceeded the number of characters allowed (100)")
    private String schedule;

    @Size(max = 100, message = "You exceeded the number of characters allowed (100)")
    @NotBlank(message = "This attribute is required")
    private String address;

    @Size(max = 500, message = "You exceeded the number of characters allowed (100)")
    private String link_address;

    @Size(max = 1000, message = "You exceeded the number of characters allowed (1000)")
    private String vr;

    @Size(max = 120, message = "You exceeded the number of characters allowed (120)")
    private String web;

    @Size(max = 100, message = "You exceeded the number of characters allowed (100)")
    private String phone;

    @NotNull(message = "The place must have qualification")
    @PositiveOrZero(message = "The qualification can't be negative")
    private Double rate;

    @Size(min = 20, message = "The info must be more than 20 characters")
    @Size(max = 2000, message = "You exceeded the number of characters allowed (2000)")
    @NotBlank(message = "This attribute is required")
    private String information;

    @Size(max = 1000,message = "You exceeded the number of characters allowed (2000)")
    private String btn_url;
}
