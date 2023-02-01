package nl.thelastages.website.service;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import nl.thelastages.website.model.dto.CreateUserDto;
import nl.thelastages.website.model.dto.UserDto;
import nl.thelastages.website.model.entity.User;
import nl.thelastages.website.respository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserService implements IUserService{
    UserRepository userRepository;

    private String userName;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean addEmail(CreateUserDto dto) {
        Optional<User> optionalEmail = userRepository.findEmailByEmail(dto.getEmailAddress());
        if (!optionalEmail.isPresent()) {
            User user = new User();
            user.setEmail(dto.getEmailAddress());
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.username", "camielvilla@gmail.com");
            props.put("mail.password", "");
            sender.setJavaMailProperties(props);
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("camielvilla@gmail.com", "brvloeuswhwfwqkl");
                }
            });
            try{
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom();
                msg.setRecipients(Message.RecipientType.TO,
                        "camielvilla@gmail.com");
                msg.setSubject("JavaMail hello world example3");
                msg.setSentDate(new Date());
                msg.setText("Hello, world!\n");
                Transport.send(msg);
            }catch (MessagingException mex){
                System.out.println("send failed, exception: " + mex);
            }
            userRepository.save(user);
            return true;
        }else {
            return false;
        }
    }



    public UserDto toDto (User user){
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        return dto;
    }

   public List<User> getAllEmails(){
        return userRepository.findAll();
   }

}
