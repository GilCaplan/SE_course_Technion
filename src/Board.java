import java.util.Arrays;

public class Board {
    private Tile[][] board;
    private int[] empty_location;

    public Board(String board){
        String[] NewBoard=board.split("\\|"),rowBoard;
        int rows=NewBoard.length;
        int cols=NewBoard[0].length();
        for(int i=0;i<rows;i++){
            rowBoard = NewBoard[i].split("\\s");
            for (int j=0;j<cols;j++){
                if(rowBoard[j].equals("_")){
                    this.board[i][j]=new Tile(0);
                    this.empty_location=new int[]{i,j};
                }
                else {
                    this.board[i][j]=new Tile(Integer.parseInt(rowBoard[j]));
                }
            }
        }
    }
    public Tile[][] getBoard(){
        return this.board;
    }

    public int Getcols(){
        return this.board[0].length;
    }
    public int GetRows(){
        return this.board.length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override


    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
