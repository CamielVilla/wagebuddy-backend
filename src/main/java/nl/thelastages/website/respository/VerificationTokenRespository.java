package nl.thelastages.website.respository;

import nl.thelastages.website.model.entity.User;
import nl.thelastages.website.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRespository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
