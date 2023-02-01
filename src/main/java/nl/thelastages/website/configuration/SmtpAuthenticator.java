//package nl.thelastages.website.configuration;
//
//import jakarta.mail.Authenticator;
//import jakarta.mail.PasswordAuthentication;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//
//import java.sql.SQLOutput;
//
//@Configuration
//@PropertySource(value = "application.properties", ignoreResourceNotFound = true)
//public class SmtpAuthenticator extends Authenticator {
//
//    public SmtpAuthenticator() {
//        super();
//    }
//
//    @Value("${spring.mail.username}")
//    private String userName;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    public PasswordAuthentication getPasswordAuthentication() {
//        System.out.println(userName);
////
////        if ((username != null) && (username.length() > 0) && (password != null)
////                && (password.length   () > 0)) {
//
//            return new PasswordAuthentication(userName, password);
//    }
//}
