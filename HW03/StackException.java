public class StackException extends RuntimeException {

    /**
     * stackException which receives a message in a string s and prints it in the error message
     * @param s is a string message for the exception
     */
    public StackException(String s) {
        super(s);
    }

    /**
     * a stackException that doesn't print any message.
     */
    public StackException() {
        super();
    }
}

