public class StackException extends Exception{
    //negative then throw NegativeCapacityException
    public void NegativeCapacityException(){
        System.err.println("error: negative capacity");
    }
    //more than current size throw StackOverflowException
    public void StackOverflowException(){
        System.err.println("error: stack overflow in capacity");
    }
    //if empty and tries to peek then throw EmptyStackException
    public void EmptyStackException(){
        System.err.println("error: stack is empty");
    }
}
