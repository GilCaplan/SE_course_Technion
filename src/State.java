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
        int cnt = 0;
        for (Tile[] tiles : this.board.getTiles()) {
            for (int j = 0; j < this.board.getTiles()[0].length; j++) {
                if (tiles[j] != null && tiles[j].getValue() != cnt)
                    return false;//not the solution
                cnt++;
            }
        }
        int len = this.board.getTiles()[0].length * this.board.getTiles().length;
        return cnt == len - 1;//win
    }

    public direction[] actions(){
        direction[] dirs;
        int row = this.board.getEmpty_loc()[0];
        int col = this.board.getEmpty_loc()[0];
        int cnt = 0;
        if(row > 0)
            cnt++;
        if(col > 0)
            cnt++;
        if(row < board.getTiles().length-1)
            cnt++;
        if(col < board.getTiles()[0].length-1)
            cnt++;
        dirs =  new direction[cnt];
        int add=0;
        if(row > 0) {
            dirs[add++] = direction.UP;
        }
        if(row < board.getTiles().length-1)
            dirs[add++] = direction.DOWN;
        if(col > 0)
            dirs[add++] = direction.LEFT;
        if(col < board.getTiles()[0].length-1)
            dirs[add] = direction.RIGHT;
        return dirs;
    }
    public State result(Action action){
        Board new_board = this.board;
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
