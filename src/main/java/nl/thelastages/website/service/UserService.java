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

    private static final String MESSAGE = "<html><head></head><body><p>Dear board game fanatic, <br><br>\n" +
            "    We want to thank you for your interest in The Last Ages. As for this moment we are working hard to finish our\n" +
            "    board game. We plan to launch later this year and would love to keep you updated on the process.<br><br>\n" +
            "    A pre-order opportunity is coming soon to make sure you are one of the first people on earth that can play a real-time strategy game\n" +
            "    within the comfort of your own home gathered by your friends and family.<br><br>\n" +
            "    Do you wish to receive more information about the game or have some cool ideas? Please don't hesitate to contact us.<br><br>\n" +
            "    Kind Regards,<br><br>\n" +
            "    Camiel, Jasper, Ruben & Yuri<br>\n" +
            "    info@thelastages.com\n" +
            "</p></body></html>";

    private static final String ENCODE = "text/html; charset=UTF-8";

    public Boolean addEmail(CreateUserDto dto) {
        Optional<User> optionalEmail = userRepository.findEmailByEmail(dto.getEmailAddress());
        if (!optionalEmail.isPresent()) {
            User user = new User();
            user.setEmail(dto.getEmailAddress());
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
                msg.setSubject("Thank you for your interest The Last Ages");
                msg.setSentDate(new Date());
                msg.setText(MESSAGE);
                msg.setHeader("Content-Type", ENCODE);
                Transport.send(msg);
                userRepository.save(user);
                return true;
            }catch (MessagingException mex){
                System.out.println("send failed, exception: " + mex);
            }
        }else {
            return false;
        }
       return false;
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
