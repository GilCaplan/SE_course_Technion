    public interface Stack<E extends Cloneable> extends Iterable<E>, Cloneable {
        void push(E element) throws StackOverflowException;
        E pop() throws EmptyStackException;
        E peek() throws EmptyStackException;
        int size();
        boolean isEmpty();
        Stack<E> clone();
    }


