public class Node {

    private final Node parent;
    private final Action prev_action;
    private final State currentState;
    public Node(Node prev, Action prev_action, State state){
        this.parent = prev;
        this.prev_action = prev_action;
        this.currentState = state;
    }
    public Node[] expand() {//use state object to do this
        Action[] actions = this.currentState.actions();//available directions that we can move in
        Node[] nodes = new Node[actions.length];
        for (int n = 0; n < actions.length; n++) {
            State newState = currentState.result(actions[n]);//what is our new possible state look like?
            nodes[n] = new Node(this, actions[n], newState);//add it to the node
        }
        return nodes;
    }
    public int heuristicValue() {
        int rowLen = currentState.getBoard().getTiles().length;
        int colLen = currentState.getBoard().getTiles()[0].length;
        int manhattanDistance = 0, goalRow, goalCol, diffRow, diffCol;
        Tile piece;

        for(int r = 0; r < rowLen; r++){
            for(int c = 0; c < colLen; c++){
                piece = currentState.getBoard().getTiles()[r][c];

                if(piece.getValue() != 0){
                    goalRow = (piece.getValue() - 1) / colLen;
                    goalCol = (piece.getValue() -1) % colLen;
                    
                    diffRow = r>goalRow ? r - goalRow : goalRow - r;
                    diffCol = c>goalCol ? c - goalCol : goalCol - c;

                    manhattanDistance += diffRow + diffCol;
                }
            }
        }

        return manhattanDistance;

    }
    public Node getParent() {
        return this.parent;
    }
    public Action getAction(){
        return this.prev_action;
    }
    public State getState(){
        return this.currentState;
    }
}