public class State {
    public boolean IsGoal(){
        int rows=this.board.GetRows();
        int cols=this.board.Getcols();
        int count=0;
        for (int i =0;i<rows;i++){
            for (int j=0; j<cols;j++){
                if(this.board.getBoard()[i][j].getValue()!=count){
                    return  false;
                }
                count++;
            }
        }
        return true;
    }
    public  Action[] action(){
        Action [] actions= new Action[4];
        int [] space= new int[2];
        int rows=this.board.GetRows();
        int cols=this.board.Getcols();
        for (int i =0;i<rows;i++){
            for (int j=0; j<cols;j++){
                if(this.board.getBoard()[i][j].getValue()==0){
                    space[0]=i;
                    space[1]=j;
                }
            }
        }
        //up
        if(space[0]!=0){
            actions[0].SetDirection(Direction.UP);
            actions[0].SetTile(this.board.getBoard()[space[0]+1][space[1]]);
        }
        if(space[0]!=this.board.getBoard().length){
            actions[1].SetDirection(Direction.DOWN);
            actions[1].SetTile(this.board.getBoard()[space[0]-1][space[1]]);
        }
        if(space[1]!=0){
            actions[0].SetDirection(Direction.LEFT);
            actions[0].SetTile(this.board.getBoard()[space[0]][space[1]+1]);
        }
        if(space[1]!=this.board.getBoard()[0].length){
            actions[0].SetDirection(Direction.UP);
            actions[0].SetTile(this.board.getBoard()[space[0]][space[1]-1]);
        }
        return actions;
    }
    public void swap(int i, int j, Direction direction){
        Tile temptile=this.board.getBoard()[i][j];
        if(direction==Direction.UP){
            this.board.getBoard()[i][j]=this.board.getBoard()[i-1][j];
            this.board.getBoard()[i-1][j]=temptile;
        }
        if(direction==Direction.DOWN){
            this.board.getBoard()[i][j]=this.board.getBoard()[i+1][j];
            this.board.getBoard()[i+1][j]=temptile;
        }
        if(direction==Direction.RIGHT){
            this.board.getBoard()[i][j]=this.board.getBoard()[i][j-1];
            this.board.getBoard()[i][j-1]=temptile;
        }
        if(direction==Direction.LEFT){
            this.board.getBoard()[i][j]=this.board.getBoard()[i][j+1];
            this.board.getBoard()[i][j+1]=temptile;
        }
    }
    public  Board result(Action action){
        Action[] actions=this.action();
        int [] space= new int[2];
        int rows=this.board.GetRows();
        int cols=this.board.Getcols();
        for (int i =0;i<rows;i++){
            for (int j=0; j<cols;j++){
                if(this.board.getBoard()[i][j].getValue()==0){
                    space[0]=i;
                    space[1]=j;
                }
            }
        }
        for(Direction direction: Direction.values()){
            if(action.getDirection()==direction){
                swap(space[0],space[1],direction);
            }
        }
        return  board;
    }

    Board board;

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
