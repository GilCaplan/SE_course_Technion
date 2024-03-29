import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E> {
    private Cloneable[] arr;
    private int stackPointer;


    /**
     * A constructor that makes a new ArrayStack object where the array holds
     * a maximum of maxElems of elements and will throw an exception if that
     * amount is negative.
     * @param maxElems that an array can hold in our stack
     */
    public ArrayStack(int maxElems){
        if (maxElems < 0) {
            throw new NegativeCapacityException();
        }
        this.arr = new Cloneable[maxElems];
        this.stackPointer = 0;
    }


    /**
     * Pushes a new element to the top of the stack
     *
     * @param element an element that should be of the stack type
     */
    @Override
    public void push(E element) throws StackOverflowException {
        if (this.stackPointer >= this.arr.length) {
            throw new StackOverflowException();
        }
        this.arr[this.stackPointer++] = element;
    }

    /**
     * remove the element at the top of the stack
     * @return top element in the stack which was removed
     * @throws EmptyStackException if stack is empty when trying to pop
     */
    @Override
    public E pop() throws EmptyStackException {
        E obj = this.peek();
        this.stackPointer--;//we ignore the items
        //above stackPointer, if we add more they get swapped out
        return obj;
    }


    /**
     * look at the item at the top of the Stack
     * @return top item in the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) arr[stackPointer - 1];
    }


    /**
     * @return size of the stack
     */
    @Override
    public int size() {
        return Math.max(this.stackPointer, 0);
    }

    /**
     * checks if the stack is empty or not
     * @return 0 if empty otherwise the size
     */
    @Override
    public boolean isEmpty() {
        return stackPointer <= 0;
    }


    /**
     * clone the stack array using invoke method to use clone function on our Cloneable objects.
     * @return cloned stack of the orignial stack.
     */
    @Override
    public ArrayStack<E> clone() {
        ArrayStack<E> clonedStack = new ArrayStack<>(this.arr.length);
        clonedStack.stackPointer = stackPointer;
        Method cloneMeth;
        for (int i = 0; i < this.stackPointer; i++) {
            try{
                cloneMeth = this.arr[i].getClass().getMethod("clone");
                clonedStack.arr[i] = (Cloneable) cloneMeth.invoke(arr[i]);
            }
            catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return clonedStack;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<E>{
        private int check = stackPointer;
        /**
         * Returns true if the iteration has more elements.
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return check > 0;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public E next() {
            return (E)arr[--check];
        }
    }
}