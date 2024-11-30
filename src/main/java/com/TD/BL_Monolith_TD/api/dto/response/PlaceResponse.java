package com.TD.BL_Monolith_TD.api.dto.response;

import com.TD.BL_Monolith_TD.util.enums.Enum_Type;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponse implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private Long id;
    private Enum_Type enum_type;
    private String title;
    private String details;
    private String price;
    private String schedule;
    private String address;
    private String link_address;
    private String vr;
    private String web;
    private String phone;
    private double rate;
    private String information;
    private String btn_url;


}
