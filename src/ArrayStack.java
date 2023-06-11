import java.util.ArrayList;
import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E>, Cloneable {
    private final int maxElems;
    private ArrayList<E> array;
    private int stackPointer;

    public ArrayStack(int maxElems) throws StackException {
        if (maxElems < 0) {
            throw new NegativeCapacityException();
        }
        this.maxElems = maxElems;
        this.array = new ArrayList<>();
        this.stackPointer = -1;
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
        this.array.add(element);
        this.stackPointer++;
    }

    /**
     * remove the element at the top of the stack
     * @return top element in the stack which was removed
     * @throws EmptyStackException if stack is empty when trying to pop
     */
    @Override
    public E pop() throws EmptyStackException {
        E obj = this.peek();
        this.array.remove(obj);
        this.stackPointer--;
        return obj;
    }


    /**
     * look at the item at the top of the Stack
     * @return top item in the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() throws EmptyStackException {
        if (stackPointer <= 0) {
            throw new EmptyStackException();
        }
        E obj = this.array.get(stackPointer);
        return obj;
    }


    /**
     * @return size of the stack
     */
    @Override
    public int size() {
        return Math.max(stackPointer, 0);
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

    public void setArray(ArrayList<E> array) {
        this.array = array;
    }

    @Override
    public ArrayStack clone() {
        ArrayStack clone = new ArrayStack(this.maxElems);
        clone.setStackPointer(this.stackPointer);
        clone.setArray((ArrayList<E>) this.array.clone());//maybe need to change
        //will leave for now
        return clone;
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
            index = array.size() - 1;
        }

        @Override
        public boolean hasNext(){
            return index >= 0;
        }

        @Override
        public E next(){
            if(hasNext())
                return array.get(index--);
            return null;//return null if we reached the end
        }
    }
}
