public class NegativeCapacityException extends StackException {

    /**
     * an exception that extends a StackException and message is thrown
     * when a given stack is given a negative capacity value or something negative in relation
     * to the capacity of the ArrayStack and therefore this error is thrown.
     */
    public NegativeCapacityException() {
        super("error: negative capacity");
    }

    /**
     * an exception that extends a StackException and message is thrown
     * when a given stack is given a negative capacity value or something negative in relation
     * to the capacity of the ArrayStack and therefore this error is thrown.
     * @param s is a string message to be given
     */
    public NegativeCapacityException(String s) {
        super(s);
    }

    /**
     * an exception that extends a StackException and message is thrown
     * when a given stack is given a negative capacity value or something negative in relation
     * to the capacity of the ArrayStack and therefore this error is thrown.
     * @param s is a string message to be given
     * @param cause is an throwable object to what is thrown
     */
    public NegativeCapacityException(String s, Throwable cause) {
        super(s, cause);
    }

}
