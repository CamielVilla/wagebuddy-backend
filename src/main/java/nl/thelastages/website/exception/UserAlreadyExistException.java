package nl.thelastages.website.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String email) {
        super(email + " is allready in database");
    }
}
