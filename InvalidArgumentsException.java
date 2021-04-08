package Assignment2;

/**
 * Description
 * This class is a class of arguments exception,
 * which forbids wrong syntax of commands.
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public class InvalidArgumentsException extends Exception {
    public InvalidArgumentsException() {
        super();
    }

    public InvalidArgumentsException(String message) {
        super(message);
    }
}
