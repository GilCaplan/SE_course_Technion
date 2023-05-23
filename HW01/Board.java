import java.util.Arrays;

public class Board {
    private final Tile[][] tiles;
    private int[] emptyLoc;

    /**
     * Board constructor, given input it makes a 2d Tile array according to input string and places
     * the coordinates of the empty tile in emptyLoc
     * @param input string input representation of the board
     */
    public Board(String input){// ["6 7 5 1","2 10 4 11","9 3 8 0"]
        String[] divide_board = input.split( "\\|"), colInput;
        int colLen = divide_board[0].split("\\s+").length;
        this.tiles = new Tile[divide_board.length][colLen];
        for(int row = 0; row < divide_board.length; row++){
            colInput = divide_board[row].split("\\s+");
            for(int col = 0; col < colInput.length; col++) {
                if (!colInput[col].equals("_"))
                    this.tiles[row][col] = new Tile(Integer.parseInt(colInput[col]));//initialize board
                else {
                    this.tiles[row][col] = new Tile(0);
                    this.emptyLoc = new int[]{row, col};
                }
            }
        }
    }

    /**
     * another board Constructor to deep copy the board to a new board if needed
     */
    public Board(Board board){
        int rows = board.getTiles().length, cols = board.getTiles()[0].length;
        this.tiles = new Tile[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c< cols; c++){
                this.tiles[r][c] = board.getTiles()[r][c];
            }
        }
        this.emptyLoc = board.emptyLoc;
    }

    /**
     * moves Tile on board object according to given action object (direction and #tile)
     * @param action object that has a direction (enum) & #tile (which tile to move)
     */
    public void moveTile(Action action){
        int row, col;
        boolean flag = false;
        for(int r=0; r<this.tiles.length; r++){
            for(int c=0; c<this.tiles[0].length; c++){
                if(this.tiles[r][c].equals(action.getTile())){
                    col = action.getDirection().equals(direction.RIGHT)?c+1:c;
                    col = action.getDirection().equals(direction.LEFT)?c-1:col;
                    row = action.getDirection().equals(direction.UP)?r-1:r;
                    row = action.getDirection().equals(direction.DOWN)?r+1:row;
                    this.tiles[row][col] = this.tiles[r][c];
                    this.tiles[r][c] = new Tile(0);
                    this.emptyLoc = new int[]{r, c};
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }
    }

    /**
     * Finds tile on board by given direction (assuming it's next to the empty tile)
     * @param dir is a direction enum (right, left, up, down)
     * @return the Tile object that can move accordingly with given direction
     */
    public Tile findTile(direction dir){
        if(this.emptyLoc[0] > 0 && dir.equals(direction.DOWN)) {
            return this.getTiles()[this.emptyLoc[0]-1][this.emptyLoc[1]];
        }
        if(this.emptyLoc[0] < this.getTiles().length-1 && dir.equals(direction.UP)){
            return this.getTiles()[this.emptyLoc[0]+1][this.emptyLoc[1]];
        }
        if(this.emptyLoc[1] < this.getTiles()[0].length-1 && dir.equals(direction.LEFT)){
            return this.getTiles()[this.emptyLoc[0]][this.emptyLoc[1]+1];
        }
        if(this.emptyLoc[1] > 0 &&dir.equals(direction.RIGHT)){
            return this.getTiles()[this.emptyLoc[0]][this.emptyLoc[1]-1];
        }
        return null;
    }

    @Override
    /*
    *equals between 2 boards
    */
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
        return this.tiles;
    }

    public int[] getEmptyLoc() {
        return this.emptyLoc;
    }
}
