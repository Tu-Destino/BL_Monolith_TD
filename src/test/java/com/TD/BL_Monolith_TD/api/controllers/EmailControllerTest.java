package com.TD.BL_Monolith_TD.api.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.TD.BL_Monolith_TD.config.TestSecurityConfig;
import com.TD.BL_Monolith_TD.config.WebConfig;
import com.TD.BL_Monolith_TD.infrastructure.services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.TD.BL_Monolith_TD.api.dto.requests.emailSenderRequest;

@WebMvcTest(EmailController.class)
@Import({TestSecurityConfig.class, WebConfig.class})
public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSendEmail() throws Exception {
        emailSenderRequest emailRequest = new emailSenderRequest();
        emailRequest.setSender("test@example.com");
        emailRequest.setSubject("Test Subject");
        emailRequest.setBody("This is a test email body.");
        emailRequest.setAttachmentPath("/path/to/attachment");

        doNothing().when(emailService).sendEmailWithAttachment(
                anyString(), anyString(), anyString(), anyString());

        mockMvc.perform(post("/email/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailRequest)))
                .andExpect(status().isOk());

        verify(emailService).sendEmailWithAttachment(
                anyString(), anyString(), anyString(), anyString());
    }
}
