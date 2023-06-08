public class EmptyStackException extends StackException {
    public EmptyStackException() {
        super("error: stack is empty");
    }
}
