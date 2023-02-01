package nl.thelastages.website.configuration;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfiguration {


    @Bean
    public JavaMailSender getJavaMailSender() {
        return new JavaMailSenderImpl();
    }
}

//    @Value("${spring.mail.host}")
//    private String mailServerHost;
//
//    @Value("${spring.mail.port}")
//    private Integer mailServerPort;
//
//    @Value("${spring.mail.username}")
//    private String mailServerUsername;
//
//    @Value("${spring.mail.password}")
//    private String mailServerPassword;
//
//    @Value("${spring.mail.properties.mail.smtp.auth}")
//    private String mailServerAuth;
//
//    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
//    private String mailServerStartTls;
//
//    @Value("${spring.mail.templates.path}")
//    private String mailTemplatesPath;


//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//

//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//}
//
//    @Bean
//    public SimpleMailMessage templateSimpleMessage() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText("This is the test email template for your email:\n%s\n");
//        return message;
//    }

