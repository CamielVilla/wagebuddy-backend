package nl.thelastages.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    public void send(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo("camielvilla@gmail.com");
        message.setSubject("subject");
        emailSender.send(message);
    }
}
