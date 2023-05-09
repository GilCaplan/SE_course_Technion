public class Action {
    private Tile tile;
    private Direction direction;

    public Action(Tile tile, Direction dir){
        this.tile = tile;
        this.direction = dir;
    }

    public Tile getTile() {
        return this.tile;
    }
    public Direction getDirection() {
        return this.direction;
    }
    @Override
    public String toString() {
        return "Move " + this.tile.getValue() + " " + this.direction;
    }

}
