public class EmptyStackException extends StackException {
    /**
     * Exception error that extends StackException which is called when a stack is empty
     * and an action or method is called on the ArraySTack
     */
    public EmptyStackException() {
        super("error: stack is empty");
    }

    /**
     * Exception error that extends StackException which is called when a stack is empty
     * and an action or method is called on the ArraySTack
     * @param message is a string that is given with the exception
     */
    public EmptyStackException(String message) {
        super(message);
    }

    /**
     * Exception error that extends StackException which is called when a stack is empty
     * and an action or method is called on the ArraySTack
     * @param message is a string that is given with the exception
     * @param cause which is a throwable object as to why the exception is being thrown
     */
    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }
}
