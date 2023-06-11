import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E>, Cloneable {
    private final int maxElems;
    private Cloneable[] arr;
    private int stackPointer;

    public ArrayStack(int maxElems) throws StackException {
        if (maxElems < 0) {
            throw new NegativeCapacityException();
        }
        this.maxElems = maxElems;
        this.arr = new Cloneable[maxElems];
        this.stackPointer = 0;
    }

    public ArrayStack(int maxElems, Cloneable[] arr, int stackPointer) throws StackException {
        if (maxElems < 0) {
            throw new NegativeCapacityException();
        }
        this.maxElems = maxElems;
        this.arr = arr;
        this.stackPointer = stackPointer;
    }


    /**
     * Pushes a new element to the top of the stack
     *
     * @param element an element that should be of the stack type
     */
    @Override
    public void push(E element) throws StackOverflowException {
        if (this.stackPointer + 1 > this.maxElems) {
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
        this.arr[this.stackPointer--] = null;
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
        return (E)this.arr[stackPointer-1];
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

    public void setStackPointer(int stackPointer) {
        this.stackPointer = stackPointer;
    }

    @Override
    public ArrayStack<E> clone() {
        Cloneable[] clonedList = new Cloneable[this.maxElems];
        for(int i=0; i<this.maxElems; i++){
            if(arr[i] != null)
                clonedList[i] = this.arr[i];//need to clone this, not sure how
            //atm i'm getting a shallow copy which is problematic
        }

        return new ArrayStack<>(maxElems, clonedList, this.stackPointer);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
    //negative then throw NegativeCapacityException
    //more than current size throw StackOverflowException
    //if empty and tries to peek then throw EmptyStackException

    private class StackIterator implements Iterator<E>{
        private int index;
        public StackIterator(){
            index = stackPointer - 1;
        }

        @Override
        public boolean hasNext(){
            return index >= 0 && arr[index] != null;
        }

        @Override
        public E next(){
            if(hasNext())
                return (E)arr[index--];
            return null;//return null if we reached the end
        }
    }
}
