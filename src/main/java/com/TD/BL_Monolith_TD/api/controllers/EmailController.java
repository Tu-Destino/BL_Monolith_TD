package com.TD.BL_Monolith_TD.api.controllers;


import com.TD.BL_Monolith_TD.api.dto.requests.emailSenderRequest;
import com.TD.BL_Monolith_TD.infrastructure.services.EmailService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;


    /*@PostMapping("/send")
    public String sendEmail(@RequestBody emailSenderRequest emailSenderRequest){
        try {

            if (emailSenderRequest.getSender() == null || emailSenderRequest.getSubject() == null || emailSenderRequest.getBody() == null) {
                return "Error: Alguno de los campos está vacío";
            }
            //Call the service to send the mail
            emailService.sendEmail(emailSenderRequest.getSender(),emailSenderRequest.getSubject(),emailSenderRequest.getBody());
            return "Mail sent correctly";
        }catch (Exception e){
            return "Error sending mail "+ e.getMessage();
        }
    }*/

    @PostMapping("/send")
    public String sendEmail(@RequestBody emailSenderRequest emailRequest){
        //Register of important info
        logger.info("Received emailRequest {}", emailRequest);
        try {
            //call the service with the data
            emailService.sendEmailWithAttachment(
                    emailRequest.getSender(),
                    emailRequest.getSubject(),
                    emailRequest.getBody(),
                    emailRequest.getAttachmentPath()
            );
            //Register of result of sent
            logger.info("Mail sent correctly to: {}", emailRequest.getSender());
            return "Mail sent successfully";
        }catch (MessagingException e){
            //Register of fail of sent
            logger.error("Error to send the mail: {}", emailRequest.getSubject());
            return "Error sending email: "+ e.getMessage();
        }
    }
}
