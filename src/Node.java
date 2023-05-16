public class Node {
    private final Node dad;
    private final Action prevAction;
    private final State curState;

    public Node(Node prev, Action prevAction, State state){
        this.dad = prev;
        this.prevAction = prevAction;
        this.curState = state;
    }
    public Node[] expend(){

    }
    public Node getParent() {
        return this.dad;
    }
    public Action getAction(){
        return this.prevAction;
    }
    public State getState(){
        return this.curState;
    }
}
