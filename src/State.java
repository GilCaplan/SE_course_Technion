public class State {
    private final Board board;
    public State(Board board){
        this.board = board;
    }
    public Board getBoard() {
        return board;
    }

    /**
     * Given the board object, check if the board matches the final solution board object
     * @return true if board matches solution board, otherwise false
     */
    public boolean isGoal(){
        //checks current state to see if finished
        Tile[][] currBoard = this.board.getTiles();
        int cnt = 1;
        int len = this.board.getTiles()[0].length * this.board.getTiles().length;
        for (Tile[] tiles : currBoard) {
            for (int c = 0; c < currBoard[0].length; c++) {
                if (tiles[c].getValue() != cnt && cnt < len)
                    return false;//not the solution
                cnt++;
            }
        }
        return true;//win
    }

    /**
     * Given a board we find what direction we can move the tile in (relative to the location of the empty tile)
     * @return an array with possible directions (enum) that the tile can be moved in
     */
    /**
     * Given a board we find what direction we can move the tile in (relative to the location of the empty tile)
     * @return an array with possible directions (enum) that the tile can be moved in
     */
    public Action[] actions(){
        direction[] dirs = this.getDirections();
        Tile tile;
        Action[] actions = new Action[dirs.length];
        int i=0;
        for(direction dir : dirs){
            tile = board.findTile(dir);//find tile with given direction
            actions[i++] = new Action(tile, dir);//get the action that we can do with given tile and direction
        }
        return actions;
    }
    public direction[] getDirections() {
        direction[] dirs;
        int row = this.board.getemptyLoc()[0];
        int col = this.board.getemptyLoc()[1];
        int cnt = 0;
        if (row > 0)//can move down
            cnt++;
        if (col > 0)//can move left
            cnt++;
        if (row < board.getTiles().length - 1)//move down
            cnt++;
        if (col < board.getTiles()[0].length - 1)//move right
            cnt++;
        dirs = new direction[cnt];
        int add = 0;
        if (row < board.getTiles().length - 1)
            dirs[add++] = direction.UP;
        if (row > 0)
            dirs[add++] = direction.DOWN;//can move tile down
        if (col < board.getTiles()[0].length - 1)
            dirs[add++] = direction.LEFT;
        if (col > 0)
            dirs[add] = direction.RIGHT;
        return dirs;
    }

    /**
     * Given an action (direction and tile) return a new state object of the board after doing that action
     * @param action object that has two values, direction (enum) & tile object number
     * @return state of board after doing the action
     */
    public State result(Action action){
        Board new_board = new Board(this.board);
        new_board.moveTile(action);
        return new State(new_board);
    }

    @Override
    public String toString() {
        Tile[][] board = this.getBoard().getTiles();
        StringBuilder sb = new StringBuilder();
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                sb.append(tile.getValue()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }

}
