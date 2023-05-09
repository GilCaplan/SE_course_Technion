public class Action {
    private Tile tile;
    private direction direction;
    public Action(Tile tile, direction dir){
        this.tile = tile;
        this.direction = dir;
    }

    public Tile getTile() {
        return this.tile;
    }
    public direction getDirection() {
        return this.direction;
    }
    @Override
    public String toString() {
        return "Move " + this.tile.getValue() + " " + this.direction;
    }
}