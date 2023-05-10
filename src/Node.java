public class Node {

    private final Node parent;
    private final Action prev_action;
    private final State current_state;
    public Node(Node prev, Action prev_action, State state){
        this.parent = prev;
        this.prev_action = prev_action;
        this.current_state = state;
    }
    public Node[] expand(){//use state object to do this
        direction[] dirs = this.current_state.actions();//available directions that we can move in
        Board board = this.current_state.getBoard();//our current board
        Node[] nodes = new Node[dirs.length];
        Tile tile;
        for(int n=0; n<dirs.length; n++){
            tile = board.findTile(dirs[n]);//find tile with given direction
            Action action = new Action(tile, dirs[n]);//get the action that we can do with given tile and direction
            State new_state = current_state.result(action);//what is our new possible state look like?
            nodes[n] = new Node(this, action, new_state);//add it to the node
        }
        return nodes;
    }

//    public int heuristicValue(){
//        int val = 1, cnt=0;
//        Tile[][] board = this.getState().getBoard().getTiles();
//        for(int i=0; i< board.length; i++){
//            for(int j=0; j< board[0].length; j++){
//                if(!board[i][j].equals(new Tile(val++)))
//                    cnt++;
//            }
//        }
//        return cnt;
//    }
    public int heuristicValue(){
        Tile[][] board = this.getState().getBoard().getTiles();
        int cnt=0, rowNum = board.length, colNum = board[0].length;
        int piece, goalCol, goalRow, diffRow, diffCol;
        int val = 1, cnt2=0;
        for(int i=0; i< rowNum; i++){
            for(int j=0; j< colNum; j++){
                piece = board[i][j].getValue();
                if(piece != 0) {
                    goalCol = (piece - 1) / colNum;//goal location
                    goalRow = (piece - 1) % colNum;
                    diffRow = i - goalRow > 0 ? i - goalRow : goalRow - i;
                    diffCol = j - goalCol > 0 ? j - goalCol : goalCol - j;
                    cnt += diffRow + diffCol;//sum Manhattan distance
                }
                if(board[i][j].getValue() != val++)
                    cnt2++;
            }
        }
        int maxDis = ((rowNum - 1) * colNum + (colNum- 1) * rowNum) * (rowNum*colNum-1);//not count 0
        int manhattanDis = (int)(100* ((double)cnt/(double) maxDis));
        int standardcalc = (100*cnt2)/(rowNum*colNum-1);
        return manhattanDis+standardcalc;
    }
    public Node getParent() {
        return this.parent;
    }
    public Action getAction(){
        return this.prev_action;
    }
    public State getState(){
        return this.current_state;
    }
}