package nl.thelastages.website;

public class EmailAllreadyExistException extends RuntimeException{
    public EmailAllreadyExistException(String email) {
        super(email + " is allready in database");
    }
}
