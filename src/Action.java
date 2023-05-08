public class Action {
    private Tile tile;
    private direction direction;
    public Action(Tile tile, direction dir){
        this.tile = tile;
        this.direction = dir;
    }

    public Tile getTile() {
        return tile;
    }
    public direction getDirection() {
        return direction;
    }
    @Override
    public String toString() {
        return "Move " + this.tile.getValue() + " " + this.direction;
    }
}