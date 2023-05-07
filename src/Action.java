public class Action {
    private String board;
    private int tile;
    private direction direction;
    @Override
    public String toString() {
        return "Move " + this.tile + " " + this.direction;
    }
}
