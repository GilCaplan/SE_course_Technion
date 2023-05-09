import java.util.Arrays;

public class Board {
    private Tile[][] tiles;
    private int[] empty_loc;
    public Board(String input){
        String[] divide_board = input.split( "\\|"), colInput;
        this.tiles = new Tile[divide_board.length][divide_board[0].split("\\s+").length];
        for(int row = 0; row < divide_board.length; row++){
            colInput = divide_board[row].split("\\s+");
            for(int col = 0; col < colInput.length; col++)
                if(!colInput[col].equals("_"))
                    this.tiles[row][col] = new Tile(Integer.parseInt(colInput[col]));//initialize board
                else {
                    this.tiles[row][col] = new Tile(0);
                    this.empty_loc = new int[]{row, col};
                }
        }
    }
    public Board(Board board){
        int rows = board.getTiles().length, cols = board.getTiles()[0].length;
        this.tiles = new Tile[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c< cols; c++){
                this.tiles[r][c] = board.getTiles()[r][c];
            }
        }

    }
    public void moveTile(Action action){
        int row, col;
        boolean flag = false;
        for(int r=0; r<this.tiles.length; r++){
            for(int c=0; c<this.tiles[0].length; c++){
                if(this.tiles[r][c] != null && this.tiles[r][c].equals(action.getTile())){
                    boolean hello = action.getDirection().equals(direction.RIGHT);
                    row = action.getDirection().equals(direction.RIGHT)?r+1:r;
                    row = action.getDirection().equals(direction.LEFT)?r-1:r;
                    col = action.getDirection().equals(direction.UP)?c-1:c;
                    col = action.getDirection().equals(direction.DOWN)?c+1:c;
                    this.tiles[row][col] = this.tiles[r][c];
                    this.tiles[r][c] = new Tile(0);
                    flag = true;
                    break;
                }
                if(flag)
                    break;
            }
        }
    }
    public int[] getEmpty_loc() {
        return this.empty_loc;
    }
    public Tile findTile(direction dir){
        if(this.empty_loc[0] > 0 && dir.equals(direction.DOWN)) {
            return this.getTiles()[this.empty_loc[0]-1][this.empty_loc[1]];
        }
        if(this.empty_loc[0] < this.getTiles().length-1 && dir.equals(direction.UP)){
            return this.getTiles()[this.empty_loc[0]+1][this.empty_loc[1]];
        }
        if(this.empty_loc[1] < this.getTiles()[0].length-1 && dir.equals(direction.LEFT)){
            return this.getTiles()[this.empty_loc[0]][this.empty_loc[1]+1];
        }
        if(this.empty_loc[1] > 0 &&dir.equals(direction.RIGHT)){
            return this.getTiles()[this.empty_loc[0]][this.empty_loc[1]-1];
        }
        return null;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(this.tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}