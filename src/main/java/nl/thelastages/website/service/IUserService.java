package nl.thelastages.website.service;

import nl.thelastages.website.model.dto.CreateUserDto;
import nl.thelastages.website.model.dto.UserDto;
import nl.thelastages.website.model.entity.User;
import nl.thelastages.website.model.entity.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    VerificationToken getVerificationToken(String VerificationToken);


    void createVerificationToken(User user, String token);


//    public Boolean addEmail(CreateUserDto dto);
//


}
