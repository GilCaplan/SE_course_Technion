public class EmptyStackException extends StackException {
    /**
     * Exception error that extends StackException which is called when a stack is empty
     * and an action or method is called on the ArraySTack
     */
    public EmptyStackException() {
        super("error: stack is empty");
    }
}
