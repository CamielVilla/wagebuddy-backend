package nl.thelastages.website.service;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import nl.thelastages.website.configuration.EmailConfiguration;
//import nl.thelastages.website.configuration.SmtpAuthenticator;
import nl.thelastages.website.model.dto.CreateUserDto;
import nl.thelastages.website.model.dto.UserDto;
import nl.thelastages.website.model.entity.User;
import nl.thelastages.website.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserService implements IUserService{
    UserRepository userRepository;
    EmailConfiguration emailConfiguration;

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${spring.mail.password}")
    private String password;

    @Autowired
    JavaMailSender sender;



    public UserService(UserRepository userRepository, EmailConfiguration emailConfiguration) {
        this.userRepository = userRepository;
        this.emailConfiguration = emailConfiguration;
    }

    private static final String MESSAGE = "<html><head></head><body><p>Youwieeee, <br><br>\n" +
            "    hier is weer een lekker email adresje.\n" +
            "</p></body></html>";

    private static final String ENCODE = "text/html; charset=UTF-8";

    public Boolean addEmail(CreateUserDto dto) {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", "smtp.hostnet.nl");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enabled", true);

            Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(userName, password);
                }
            });
            try{
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom("info@thelastages.com");
                msg.setRecipients(Message.RecipientType.TO,
                        dto.getEmailAddress());
                msg.addRecipients(Message.RecipientType.TO, "yurivillarikkers@gmail.com");
                msg.setSubject("nieuw email adres voor wagebuddy!");
                msg.setSentDate(new Date());
                msg.setText(dto.getName() + dto.getEmailAddress() + dto.getPhone());
                msg.setHeader("Content-Type", ENCODE);
                Transport.send(msg);
                return true;
            }catch (MessagingException mex){
                System.out.println("send failed, exception: " + mex);
                return false;
            }
    }



    public UserDto toDto (User user){
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        return dto;
    }

}
