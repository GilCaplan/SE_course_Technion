import java.util.ArrayList;
import java.util.Iterator;

public class ArrayStack<T> implements Stack,  Cloneable{
    private final int maxElems;
    private ArrayList<Cloneable> array;
    private int stackPointer;

    public ArrayStack(int maxElems) throws StackException{
        if(maxElems < 0){
            throw new NegativeCapacityException();
        }
        this.maxElems = maxElems;
        this.array = new ArrayList<>();
        this.stackPointer = -1;
    }

    /**
     * Pushes a new element to the top of the stack
     * @param element an element that should be of the stack type
     */
    @Override
    public void push (Cloneable element) throws StackOverflowException {
        if(this.stackPointer + 1 > this.maxElems) {
            throw new StackOverflowException();
        }
        this.array.add(element);
        this.stackPointer++;
    }

    @Override
    public Cloneable pop() throws EmptyStackException {
        Cloneable obj = this.peek();
        this.array.remove(obj);
        this.stackPointer--;
        return obj;
    }

    @Override
    public Cloneable peek() throws EmptyStackException {
        if(stackPointer <= 0){
            throw new EmptyStackException();
        }
        Cloneable obj = this.array.get(stackPointer);
        return obj;
    }

    @Override
    public int size() {
        return Math.max(stackPointer, 0);
    }

    @Override
    public boolean isEmpty() {
        return stackPointer <= 0;
    }

    @Override
    public Stack clone() {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return null;
    }
    //negative then throw NegativeCapacityException
    //more than current size throw StackOverflowException
    //if empty and tries to peek then throw EmptyStackException

//    public class StackIterator implements Iterator{
//
//    }
}
