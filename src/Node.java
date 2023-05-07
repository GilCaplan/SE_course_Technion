public class Node {
    public Node getParent() {
        return parent;
    }
    public Action getAction(){
        return this.getAction();
    }
    public State getState(){
        return this.getState();
    }

    private Node parent;
    public Node[] expand(){//use state object to do this
        return null;
    }
    public int heuristicValue(){
        return 0;
    }
}
