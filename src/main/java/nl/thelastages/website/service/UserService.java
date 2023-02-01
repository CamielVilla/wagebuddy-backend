package nl.thelastages.website.service;

import nl.thelastages.website.model.dto.CreateUserDto;
import nl.thelastages.website.model.dto.UserDto;
import nl.thelastages.website.model.entity.User;
import nl.thelastages.website.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;


    public Boolean addEmail(CreateUserDto dto) {
        Optional<User> optionalEmail = userRepository.findEmailByEmail(dto.getEmailAddress());
        if (!optionalEmail.isPresent()) {
            User user = new User();
            user.setEmail(dto.getEmailAddress());
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
