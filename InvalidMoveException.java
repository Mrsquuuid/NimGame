package Assignment2;

/**
 * Description
 * This class is a class of move exception,
 * which forbids input which is not in the specified range.
 * yuzyou@student.unimelb.edu.au
 * No.1159774
 *
 * @author Yuzhe You
 */

public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super();
    }

    public InvalidMoveException(String message) {
        super(message);
    }
}
