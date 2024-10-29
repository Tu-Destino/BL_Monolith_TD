package com.TD.BL_Monolith_TD.api.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class emailSenderRequest{

    @NotNull(message = "The mail must be not null")
    @Email(message = "The input must be a email")
    private String sender;

    @NotNull(message = "The subject can't be null")
    private String subject;

    @NotNull(message = "The body can't be null")
    private String body;

    private String attachmentPath;

}
