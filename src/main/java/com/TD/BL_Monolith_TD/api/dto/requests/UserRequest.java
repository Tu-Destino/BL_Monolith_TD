package com.TD.BL_Monolith_TD.api.dto.requests;

import com.TD.BL_Monolith_TD.util.enums.RoleUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "The name can't be null")
    @Size(max = 50, message = "You exceeded the number of characters allowed (50)")
    private String name;
    @NotBlank(message = "The email can't be null")
    @Size(max = 40, message = "You exceeded the number of characters allowed (40)")
    private String email;
    @NotBlank(message = "The password can't be null")
    @Size(max = 40, message = "You exceeded the number of characters allowed (40)")
    private String password;
    @NotNull(message = "The User_Role can't be null")
    private RoleUser enum_rol;
  //  private String cookies_id;
}
