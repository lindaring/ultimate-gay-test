package za.co.lindaring.gay.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Yoh, user not found...");
    }
}
