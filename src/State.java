public class State {
    private String board;
    public State(String board){
        this.board = board;
    }
    public boolean isGoal(){
        //checks current state to see if finished
        return false;
    }

    public direction[] actions(){
        //checks which movements are valid and returns an array with the
        //directions that can be used
        return null;
    }
    public State result(Action action){
        //returns new state
        return this;
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
