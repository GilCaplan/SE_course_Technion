public class NegativeCapacityException extends StackException {

    /**
     * an exception that extends a StackException and message is thrown
     * when a given stack is given a negative capacity value or something negative in relation
     * to the capacity of the ArrayStack and therefore this error is thrown.
     */
    public NegativeCapacityException() {
        super("error: negative capacity");
    }
}
