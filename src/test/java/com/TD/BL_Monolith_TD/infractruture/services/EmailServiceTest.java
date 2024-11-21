package com.TD.BL_Monolith_TD.infractruture.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.TD.BL_Monolith_TD.infrastructure.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MimeMessage mimeMessage;

    @Mock
    private MimeMessageHelper mimeMessageHelper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mimeMessage = new MimeMessage((jakarta.mail.Session) null);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    public void testSendEmailWithAttachment() throws MessagingException {
        String toEmail = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";
        String attachmentPath = "path/to/attachment.txt";

        emailService.sendEmailWithAttachment(toEmail, subject, body, attachmentPath);

        verify(mailSender).send(mimeMessage);
    }

    @Test
    public void testSendEmailWithoutAttachment() throws MessagingException {
        String toEmail = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";
        String attachmentPath = "";

        emailService.sendEmailWithAttachment(toEmail, subject, body, attachmentPath);

        verify(mailSender).send(mimeMessage);
    }
}
