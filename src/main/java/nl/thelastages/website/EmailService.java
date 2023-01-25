package nl.thelastages.website;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {
    EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public Boolean addEmail(CreateEmailDto dto) {
        Optional<Email> optionalEmail = emailRepository.findEmailByEmailAddress(dto.getEmailAddress());
        if (!optionalEmail.isPresent()) {
            Email email = new Email();
            email.setEmailAddress(dto.getEmailAddress());
            emailRepository.save(email);
            return true;
        }else {
            return false;
        }
    }
    public EmailDto toDto (Email email){
        EmailDto dto = new EmailDto();
        dto.setEmailAddress(email.getEmailAddress());
        dto.setId(email.getId());
        return dto;
    }

   public List<Email> getAllEmails(){
        return emailRepository.findAll();
   }

}
