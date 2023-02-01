package nl.thelastages.website.configuration;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
public class EmailConfiguration {
//
//       @Value("${spring.mail.username}")
//        private String aap;
//
//    @Value("${spring.mail.password}")
//    private String password;

    @Bean
    public JavaMailSender getJavaMailSender() {
        return new JavaMailSenderImpl();

    }
}

