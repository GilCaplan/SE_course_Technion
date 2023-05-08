public class Node {

    private Node parent;
    private Action prev_action;
    private State current_state;
    public Node(Action prev_action, State state){
        this.parent = this;
        this.prev_action = prev_action;
        this.current_state = state;
    }
    public Tile[] expand(){//use state object to do this
        direction[] dirs = this.current_state.actions();//available directions that we can move in
        Board board = this.current_state.getBoard();//our current board
        int[] empty = board.getEmpty_loc();//row, col coords of empty tile
        Node[] nodes = new Node[dirs.length];
        int i = 0;
        Tile tile;
        for(Node node : nodes){
            tile = board.findTile(dirs[i]);
            Action action = new Action(tile, dirs[i++]);
            State new_state = current_state.result(action);
            node = new Node(action, new_state);
        }
        return null;
    }
    public int heuristicValue(){
        return 0;
    }
    public Node getParent() {
        return this.parent;
    }
    public Action getAction(){
        return this.getAction();
    }
    public State getState(){
        return this.getState();
    }
}
