public class StackOverflowException extends StackException {

    /**
     * an error that is thrown if we try to add more objects to our stack when it's full
     */
    public StackOverflowException() {
        super("error: stack overflow in capacity");
    }

    /**
     * an error that is thrown if we try to add more objects to our stack when it's full
     * @param message is a string message to posted with the exception
     */
    public StackOverflowException(String message) {
        super(message);
    }

    /**
     * an error that is thrown if we try to add more objects to our stack when it's full
     * @param message is a string message to posted with the exception
     * @param cause is a throwable object that tells us what is the cause of the exception
     */
    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
