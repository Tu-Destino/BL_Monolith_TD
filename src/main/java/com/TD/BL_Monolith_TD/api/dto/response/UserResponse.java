package com.TD.BL_Monolith_TD.api.dto.response;

import com.TD.BL_Monolith_TD.util.enums.RoleUser;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private RoleUser enum_rol;

}
