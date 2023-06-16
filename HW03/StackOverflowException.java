public class StackOverflowException extends StackException {

    /**
     * an error that is thrown if we try to add more objects to our stack when it's full
     */
    public StackOverflowException() {
        super("error: stack overflow in capacity");
    }
}
