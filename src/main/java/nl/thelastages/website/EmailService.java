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

    public EmailDto addEmail(CreateEmailDto createEmailDto) {
        Optional<Email> optionalEmail = emailRepository.findEmailByEmailAddress(createEmailDto.getEmailAddress());
        if(!optionalEmail.isPresent()){
            Email email = new Email();
            email.setEmailAddress(createEmailDto.getEmailAddress());
            return toDto(emailRepository.save(email));
        }
   else{
       throw new EmailAllreadyExistException(createEmailDto.getEmailAddress());
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
