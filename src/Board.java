import java.util.Arrays;

public class Board {

    public Tile[][] getTiles() {
        return tiles;
    }

    private Tile[][] tiles;
    private int[] empty_loc;
    public Board(String input){
        String[] divide_board = input.split( "|"), row_input;
        this.tiles = new Tile[divide_board.length][divide_board[0].split("\\s+").length];
        for(int row = 0; row < this.tiles[0].length; row++){
            row_input = divide_board[row].split("\\s+");
            for(int col = 0; col < row_input.length; col++)
                if(!row_input[col].equals("_"))
                    this.tiles[row][col] = new Tile(Integer.parseInt(row_input[col]));//initialize board
                else {
                    this.tiles[row][col] = new Tile(0);
                    this.empty_loc = new int[]{row, col};
                }
        }
    }
    public void moveTile(Action action){
        int row, col;
        boolean flag = false;
        for(int r=0; r<this.tiles.length; r++){
            for(int c=0; c<this.tiles[0].length; c++){
                if(this.tiles[r][c].equals(action.getTile())){
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
        if(dir.equals(direction.DOWN)) {
            return this.getTiles()[this.empty_loc[0]-1][this.empty_loc[1]];
        }
        if(dir.equals(direction.UP)){
            return this.getTiles()[this.empty_loc[0]+1][this.empty_loc[1]];
        }
        if(dir.equals(direction.LEFT)){
            return this.getTiles()[this.empty_loc[0]][this.empty_loc[1]+1];
        }
        if(dir.equals(direction.RIGHT)){
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
}
