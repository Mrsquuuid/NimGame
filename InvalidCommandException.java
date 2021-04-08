package Assignment2;

/**
 * Description
 * This class is a class of Commands exception,
 * which forbids input command which is not among the specified commands.
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super();
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
