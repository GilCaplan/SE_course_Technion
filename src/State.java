public class State {
    public boolean IsGoal(){
        int rows=this.board.GetRows();
        int cols=this.board.Getcols();
        int count=0;
        for (int i =0;i<rows;i++){
            for (int j=0;j<0;j++){
                if(this.board.getBoard()[i][j].getValue()!=count){
                    return  false;
                }
                count++;
            }
        }
        return true;
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
