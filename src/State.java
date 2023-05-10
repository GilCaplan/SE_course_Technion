public class State {
    private Board board;
    public State(Board board){
        this.board = board;
    }
    public Board getBoard() {
        return board;
    }
    public boolean isGoal(){
        //checks current state to see if finished
        Tile[][] currBoard = this.board.getTiles();
        int cnt = 1;
        int len = this.board.getTiles()[0].length * this.board.getTiles().length;
        for (int r=0; r<currBoard.length; r++) {
            for (int c = 0; c < currBoard[0].length; c++) {
                if (currBoard[r][c].getValue() != cnt && cnt < len)
                    return false;//not the solution
                cnt++;
            }
        }
        return true;//win
    }

    public direction[] actions(){
        direction[] dirs;
        int row = this.board.getEmpty_loc()[0];
        int col = this.board.getEmpty_loc()[1];
        int cnt = 0;
        if(row > 0)//can move down
            cnt++;
        if(col > 0)//can move left
            cnt++;
        if(row < board.getTiles().length-1)//move down
            cnt++;
        if(col < board.getTiles()[0].length-1)//move right
            cnt++;
        dirs =  new direction[cnt];
        int add=0;
        if(row > 0)
            dirs[add++] = direction.DOWN;//can move tile down
        if(row < board.getTiles().length-1)
            dirs[add++] = direction.UP;
        if(col > 0)
            dirs[add++] = direction.RIGHT;
        if(col < board.getTiles()[0].length-1)
            dirs[add] = direction.LEFT;
        return dirs;
    }
    public State result(Action action){
        Board new_board = new Board(this.board);
        if(action != null && this.getBoard().checkAction(action))
            new_board.moveTile(action);
        return new State(new_board);
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
