package nl.thelastages.website.service;

import nl.thelastages.website.exception.UserAlreadyExistException;
import nl.thelastages.website.model.dto.UserDto;
import nl.thelastages.website.model.entity.User;
import nl.thelastages.website.model.entity.VerificationToken;
import nl.thelastages.website.respository.UserRepository;
import nl.thelastages.website.respository.VerificationTokenRespository;
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
    @Autowired
    VerificationTokenRespository tokenRepository;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
                Optional<User> optionalEmail = userRepository.findEmailByEmail(userDto.getEmail());
        if (!optionalEmail.isPresent()) {
            throw new UserAlreadyExistException(
                    "There is an account with that email adress: "
                            + userDto.getEmail());
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
       // user.setRole(new Role(Integer.valueOf(1), user));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }



//    public Boolean addEmail(CreateUserDto dto) {
//        Optional<User> optionalEmail = userRepository.findEmailByEmailAddress(dto.getEmailAddress());
//        if (!optionalEmail.isPresent()) {
//            User user = new User();
//            user.setEmail(dto.getEmailAddress());
//            userRepository.save(user);
//            return true;
//        }else {
//            return false;
//        }
//    }




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
