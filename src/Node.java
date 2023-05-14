public class Node {

    private final Node parent;
    private final Action prev_action;
    private final State currentState;
    public Node(Node prev, Action prev_action, State state){
        this.parent = prev;
        this.prev_action = prev_action;
        this.currentState = state;
    }

    /**
     * With given puzzle state, an array of all the possible actions that can be done on the board is received.
     * We make a node array which gets a node with the new state of the board after performing the action.
     * @return Node array with the updated states and previous nodes (which is saved in the node array)
     */
    public Node[] expand() {//use state object to do this
        Action[] actions = this.currentState.actions();//available directions that we can move in
        Node[] nodes = new Node[actions.length];
        for (int n = 0; n < actions.length; n++) {
            State newState = currentState.result(actions[n]);//what is our new possible state look like?
            nodes[n] = new Node(this, actions[n], newState);//add it to the node
        }
        return nodes;
    }

    /**
     * calculates the value of the current board in relation to the board solution using the manhattan (l1) calculation
     * @return Heuristic value of the board
     */
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