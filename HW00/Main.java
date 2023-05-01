import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main{
    public static Scanner scanner;
    public static Random rnd;

    public static void battleshipGame() {
        //get board length, amount of ships and their length's creat board and ship objects and sort them.
        System.out.println("Enter the board size");//"nXm", n lines, m columns
        String[] board_Len = scanner.nextLine().split("X");
        int n_Len = Integer.parseInt(board_Len[0]);
        int m_Len = Integer.parseInt(board_Len[1]);
        int[][] p_Board = new int[n_Len][m_Len];//player board, nxm
        int[][] b_Board = new int[n_Len][m_Len];//bot board, nxm

        int[][] guess_P_Board = new int[n_Len][m_Len];//player board
        int[][] guess_b_Board = new int[n_Len][m_Len];//bot/computer board

        //boards are instantiated to 0 when created.

        int[] ships = instantiate_Board();
        int user_Life = ships.length, bot_Life = ships.length;//lives left
        int[][] b_ships_LocX = new int[2][ships.length];
        int[][] b_ships_LocY = new int[2][ships.length];
        int[][] p_ships_LocX = new int[2][ships.length];//0,1 is y ship loc
        int[][] p_ships_LocY = new int[2][ships.length];//4 (0,1 is ship x loc), ship.length
        //is the number ship we're checking, 2 is x/y coords

        //now have user place ships on the board, then bot sets board up randomly
        System.out.println("Your current game board:");
        print_Board(p_Board, false);
        place_user_ships(p_Board, ships, p_ships_LocX, p_ships_LocY);
        place_Bot_Ships(b_Board, ships, b_ships_LocX, b_ships_LocY);

        while(true){//time to play game, run until game is over
            System.out.println("Your current guessing board:");
            print_Board(guess_P_Board, true);
            System.out.println("Enter a tile to attack");
            bot_Life += user_Turn(guess_P_Board, b_Board, b_ships_LocX, b_ships_LocY, bot_Life);//-1 if ship sunk
            if(bot_Life == 0){//user wins
                System.out.println("You won the game!");
                return;
            }

            user_Life += bot_Turn(guess_b_Board, p_Board, p_ships_LocX, p_ships_LocY, user_Life);//-1 if ship sunk
            System.out.println("Your current game board:");
            print_Board(p_Board, false);

            if(user_Life == 0){//bot wins
                System.out.println("You lost ):");
                return;
            }
        }
    }

    /***
     * place ships for user, runs until user inputs fills the board with all the given ships
     * @param board - player board, battlefield
     * @param ships - array (int) of ship lengths (from smallest to largest)
     * @param ships_LocX - int array, will set x index's locations for the ship's placed
     * @param ships_LocY - int array, will set y index's locations for the ship's placed
     */
    public static void place_user_ships(int[][] board, int[] ships, int[][] ships_LocX, int[][] ships_LocY){
        String loc;
        int row, col;
        for(int i=0; i < ships.length; i++) {
            loc = "error";
            System.out.println("Enter location and orientation for battleship of size "+ ships[i]);
            while(loc.equals("error"))
                loc = check_Ship_Placement(board, ships, i);
            row = Integer.parseInt(String.valueOf(loc.split(",")[0]));
            col= Integer.parseInt(String.valueOf(loc.split(",")[1]));
            place_ship(row, col, ships[i], i, board, loc.split(",")[2].equals("1"), ships_LocX, ships_LocY);

            //we got a valid ship placement
            System.out.println("Your current game board:");
            print_Board(board, false);
        }
    }

    /***
     * places ships for the bot randomly on the battlefield board
     * @param board - battlefield (2d int array)
     * @param ships - integer array containing ships lengths
     * @param ships_LocX - int array, will set x index's locations for the ship's placed
     * @param ships_LocY - int array, will set y index's locations for the ship's placed
     */
    public static void place_Bot_Ships(int[][] board, int[] ships, int[][] ships_LocX, int[][] ships_LocY){
        int row = 0, col = 0, orientation = 0;
        boolean check;
        for(int i=0; i < ships.length; i++) {
            check = false;
            while(!check) {
                row = rnd.nextInt(board.length);
                col = rnd.nextInt(board[0].length);
                orientation = rnd.nextInt(2);
                check = check_Bot_Ship_Placement(board, ships, row, col, orientation, i);
            }
            //we have a valid ship placement therefore we place it on the board accordingly
            place_ship(row, col, ships[i], i, board, orientation == 1, ships_LocX, ships_LocY);
        }
    }

    /***
     * Run's the user/player's turn to attack the computer/bot, given the player's input coords (x,y)
     * player attacks that position, the variables are updated accordingly and appropriate message printed.
     * if one of the computer's ships got sunk than return -1 to take off a life (battleships remaining). Otherwise 0
     * @param guess_Board an int array that contains the player's guesses on tiles throughout the game
     * @param enemy_Board the bot/computer's board setup (int[][]) for the game
     * @param enemy_Loc_ShipsX x coordinates for all the enemy ships, where ships X coords can be accessed at index i
     * @param enemy_Loc_ShipsY y coordinates for all the enemy ships, where ship Y coords can be accessed at index i
     * @param lives how many lives (battleships remaining) the user/player has left
     * @return -1 if ship sunk, otherwise 0
     */
    public static int user_Turn(int[][] guess_Board, int[][] enemy_Board, int[][] enemy_Loc_ShipsX, int[][] enemy_Loc_ShipsY, int lives){
        int row=-1, col=-1;//just to initialize to run the loop
        String tile;
        while(exceeds_Bounds(row, col, enemy_Board) || guess_Board[row][col] != 0){
            tile = scanner.nextLine().replaceAll("\\s+","");//"x,y"
            row = Integer.parseInt(tile.split(",")[0]);
            col = Integer.parseInt(tile.split(",")[1]);
            if(exceeds_Bounds(row, col, enemy_Board))
                System.out.println("Illegal tile, try again!");
            else if(guess_Board[row][col] != 0)//1=miss,2=hit,3=sunk
                System.out.println("Tile already attacked, try again!");
        }
        //we now have a valid hit which is a hit or miss
        if(enemy_Board[row][col] == 0) {
            System.out.println("That is a miss!");
            guess_Board[row][col] = 1;//1 is miss, 2 is hit. update guess board.
        }
        if(enemy_Board[row][col] == 1){
            System.out.println("That is a hit!");
            guess_Board[row][col] = enemy_Board[row][col] = 2;//update enemy board & guess board.

            //check if sunk
            if(ship_Sunk(enemy_Board, enemy_Loc_ShipsX, enemy_Loc_ShipsY, row, col)){
                System.out.println("The computer's battleship has been drowned, " + (lives-1) +" more battleships to go!");
                return -1;//lost a life
            }
        }
        return 0;//otherwise no ships are sunk, therefore no change in lives left
    }

    /***
     * Run's the bot/computer's turn to attack the user/player, it finds random coords x,y that it hasn't hit yet
     * and attacks that position, the variables are updated accordingly and appropriate message printed.
     * if one of the user's ships got sunk than return -1 to take off a life (battleships remaining). Otherwise 0
     * @param guess_Board an int array that contains the computer's guesses on tiles throughout the game
     * @param user_Board the user's board setup (int[][]) for the game
     * @param user_Loc_ShipsX x coordinates for all the ships, where ships X coords can be accessed at index i
     * @param user_Ships_LocYY y coordinates for all the ships, where ship Y coords can be accessed at index i
     * @param lives how many lives (battleships remaining) the bot has left
     * @return -1 if ship sunk, otherwise 0
     */
    public static int bot_Turn(int[][] guess_Board, int[][] user_Board, int[][] user_Loc_ShipsX, int[][] user_Ships_LocYY, int lives){
        int row = -1, col = -1;
        while(row < 0 || guess_Board[row][col] != 0){//col, row
            //if value is 0 => tile hasn't been hit. else the tile has been hit whether hit, sink or miss
            row = rnd.nextInt(user_Board.length);
            col = rnd.nextInt(user_Board[0].length);
        }
        String tile_Attack = "("+row+", "+col+")";
        System.out.println("The computer attacked " + tile_Attack);
        //regular board: miss =  1, hit = 2, ship sunk = 3, else -, (regular board)
        //guess_Board: not hit = 0, miss = 1, hit = 2, ship sunk = 3
        if(user_Board[row][col] == 0){
            System.out.println("That is a miss!");
            guess_Board[row][col] = 1;
        }
        if(user_Board[row][col] == 1){
            System.out.println("That is a hit!");
            guess_Board[row][col] = user_Board[row][col] = 2;
            if(ship_Sunk(user_Board, user_Loc_ShipsX, user_Ships_LocYY, row, col)){
                System.out.println("Your battleship has been drowned, you have left " + (lives-1) +" more battleships!");
                return -1;//lost a life
            }
        }
        return 0;

    }

    /***
     * receives input from user to place ship and check's if input is valid or not.
     * @param board player board (battlefield)
     * @param ships int array containing ship lengths
     * @param i ship index
     * @return "error" if invalid input, otherwise is valid and therefore returns valid input
     */
    public static String check_Ship_Placement(int[][] board, int[] ships, int i){
        String loc, orientation;//loc=location
        int row, col;
        loc = scanner.nextLine().replaceAll("\\s+", "");//"x,y,orientation", orientation= 0/1 otherwise false
        orientation = loc.split(",")[2];
        if(!orientation.equals("0") && !orientation.equals("1")){//error, invalid value
            System.out.println("Illegal orientation, try again!");
            return "error";
        }
        row = Integer.parseInt(loc.split(",")[0]);//board gets transposed for some reason
        col = Integer.parseInt(loc.split(",")[1]);
        if(exceeds_Bounds(row, col, board)){
            System.out.println("Illegal tile, try again!");
            return "error";
        }
        if(ship_In_Bounds(row, col, ships[i], orientation.equals("1"), board)){
            System.out.println("Battleship exceeds the boundaries of the board, try again!");
            return "error";
        }
        if(check_Overlap(row, col, ships[i], orientation.equals("1"), board)){
            System.out.println("Battleship overlaps another battleship, try again!");
            return "error";
        }
        if(check_Adjacent_Ships(row, col, ships[i], orientation.equals("1"), board)) {
            System.out.println("Adjacent battleship detected, try again!");
            return "error";
        }
        return loc;//no errors so loc is valid
    }

    /** given board, checks ship placement given indexes is valid or not for bot/computer */
    public static boolean check_Bot_Ship_Placement(int[][] board, int[] ships, int row, int col, int orientation, int i){
        if(orientation != 0 && orientation != 1)//error, invalid orientation
            return false;
        if(exceeds_Bounds(row, col, board))
            return false;
        if(ship_In_Bounds(row, col, ships[i], orientation == 1, board))
            return false;
        if(check_Overlap(row, col, ships[i], orientation == 1, board))
            return false;
        return !check_Adjacent_Ships(row, col, ships[i], orientation == 1, board);
    }

    /***
     * given coordinates that represent a valid tile to place the ship, the function places the ship on the board at x,y
     * @param col coordinate
     * @param row coordinate
     * @param ship_Len length of a given ship
     * @param ship ship index
     * @param board player/computer board (battlefield)
     * @param ver Vertical
     * @param ships_LocX  int array, will set x index's locations for the ship's placed
     * @param ships_LocY  int array, will set y index's locations for the ship's placed
     */
    public static void place_ship(int row, int col, int ship_Len, int ship, int[][] board, boolean ver, int[][] ships_LocX, int[][] ships_LocY){
        if(ver) {//ship is vertical
            for (int i = 0; i < ship_Len; i++)
                board[row + i][col] = 1;
            ships_LocX[0][ship] = ships_LocX[1][ship] = col;//keep track of the battleships x,y locations according to #ship
            ships_LocY[0][ship] = row;
            ships_LocY[1][ship] = row + ship_Len;// <=> [row, row+ship_Len)
        }
        else {//ship is horizontal
            for (int i = 0; i < ship_Len; i++)
                board[row][col + i] = 1;
            ships_LocX[0][ship] = col;//keep track of the battleships x,y locations according to #ship
            ships_LocX[1][ship] = col + ship_Len;// <=> [col, col+ship_Len)
            ships_LocY[0][ship] = ships_LocY[1][ship] = row;
        }
    }
    /***
     * finds ship on enemy_Board (battlefield) given coordinates x,y and check's if that ship is sunken or not.
     * @param enemy_Board the bot/computer's board setup (int[][]) for the game
     * @param enemy_Loc_ShipsX x coordinates for all the enemy ships, where ship X coords can be accessed at index i
     * @param enemy_Loc_ShipsY y coordinates for all the enemy ships, where ship Y coords can be accessed at index i
     * @param row row coordinates
     * @param col col coordinates
     * @return Boolean value of whether ship at coordinates x,y has sunken or not.
     */
    public static boolean ship_Sunk(int[][] enemy_Board, int[][] enemy_Loc_ShipsX, int[][] enemy_Loc_ShipsY, int row, int col){
        int ship = 0;
        boolean check;
        for(int i = 0; i < enemy_Loc_ShipsX[0].length; i++){//find ship
            check = enemy_Loc_ShipsX[0][i] == enemy_Loc_ShipsX[1][i] && enemy_Loc_ShipsX[0][i] == col;//check hor
            if(check || col >= enemy_Loc_ShipsX[0][i] && col < enemy_Loc_ShipsX[1][i]) {//check if x in range
                check = enemy_Loc_ShipsY[0][i] == enemy_Loc_ShipsY[1][i] && enemy_Loc_ShipsY[0][i] == row;//check ver
                if(check || row >= enemy_Loc_ShipsY[0][i] && row < enemy_Loc_ShipsY[1][i]){//check that y is in range
                    ship = i;//we found our ship index
                    break;
                }
            }
        }
        boolean flag_Sunk = true, hor = enemy_Loc_ShipsY[0][ship] == enemy_Loc_ShipsY[1][ship];
        if(hor) //ship is horizontal, (can optimize and shorten code, I feel too lazy to do so lol)
            for (int j = enemy_Loc_ShipsX[0][ship]; j < enemy_Loc_ShipsX[1][ship]; j++) {
                flag_Sunk = enemy_Board[enemy_Loc_ShipsY[0][ship]][j] == 2;//row is same, traverse col
                if(!flag_Sunk)
                    break;
            }
        else//ship is vertical
            for(int j = enemy_Loc_ShipsY[0][ship]; j < enemy_Loc_ShipsY[1][ship]; j++) {
                flag_Sunk = enemy_Board[j][enemy_Loc_ShipsX[0][ship]] == 2;//col is same, traverse rows
                if(!flag_Sunk)
                    break;
            }

        if(flag_Sunk){//change values on board so that we know that the ship is sunk
            if(hor) //ship is horizontal
                for (int j = enemy_Loc_ShipsX[0][ship]; j < enemy_Loc_ShipsX[1][ship]; j++)
                    enemy_Board[enemy_Loc_ShipsY[0][ship]][j] = 3;
                //change values of tiles of the sunken ship so that we know that the ship has been sunk
            else//ship is vertical
                for(int j = enemy_Loc_ShipsY[0][ship]; j < enemy_Loc_ShipsY[1][ship]; j++)
                    enemy_Board[j][enemy_Loc_ShipsX[0][ship]] = 3;//3 means sunk
        }//let's change values to 3 since we know ship is sunk so that we don't count it again
        return flag_Sunk;
    }

    /**
     * instantiates the amount and length of battleShips to an array according to input N1XM1 ... NkXMk
     * @return ship array (where each item in the array represents the length of the ship)
     */
    public static int[] instantiate_Board(){
        System.out.println("Enter the battleships sizes");
        String[] battle_Ships = scanner.nextLine().split("\\s+");
        int cnt_Ship = 0, j = 0;

        for (String battleShip : battle_Ships)//split when given "n1Xs1 n2Xs2 ... nkXsk"
            cnt_Ship += Integer.parseInt(String.valueOf(battleShip.charAt(0)));//count total amount of ships

        int[] ships = new int[cnt_Ship];//list of our ship lengths
        for (String battleShip : battle_Ships) //setting values to list according to input
            for (int run = 0; run < Integer.parseInt(battleShip.split("X")[0]); run++)
                ships[j++] = Integer.parseInt(battleShip.split("X")[1]);
        return ships;
    }
    /** prints given board whether it's the guessing board or battle game board */
    public static void print_Board(int[][] board, boolean guess_tile){
        String tile, check_Guess_Tile, extra_Spaces = "";
        for(int i = board.length; i > 0; i /= 10)
            extra_Spaces += " ";
        System.out.print(extra_Spaces);
        for (int r = 0; r < board[0].length; r++) {
            extra_Spaces = (r==0? " ":"") +( (board[0].length>10&&r<10)?" ":"") + ((board[0].length>100&&r<100)?" ":"");
            System.out.print(extra_Spaces + r + (r < board[0].length - 1 ? " " : ""));//r + whitespace if not end of the line
        }
        System.out.println();
        for (int c = 0; c < board.length; c++) {//cols
            extra_Spaces = (board.length>10&&c<10?" ":"") + (board.length>100&&c<100?" ":"");
            System.out.print(extra_Spaces  + c);
            for (int r = 0; r < board[0].length; r++) {//rows
                //regular board: miss =  1, hit = 2, ship sunk = 3, else -, (regular board)
                //guess_Board: not hit = 0, miss = 1, hit = 2, ship sunk = 3
                tile = board[c][r] == 1 ? "#" : (board[c][r] == 2 || board[c][r] == 3) ? "X" : "–";
                //V if hit (2,3), X (0) if miss, otherwise -
                check_Guess_Tile = board[c][r] == 1 ? "X" : ((board[c][r] == 2 || board[c][r] == 3) ? "V" : "–");
                extra_Spaces = (r==0? " ":"") +(board[0].length>10?" ":"") + (board[0].length>100?" ":"");
                System.out.print(extra_Spaces + (guess_tile ? check_Guess_Tile : tile) + (r<board[0].length-1?" ":""));
                //print guess tile board or normal board tile
            }
            System.out.println();
        }
        System.out.println();
    }

    /** checks that there isn't another ship in the place that we are trying to place our ship */
    public static boolean check_Overlap(int row, int col, int len_Ship, boolean ver, int[][] board){
        for(int i = 0; i < len_Ship;i++)//loop through the length of the ship
            if(board[row+ i * (ver? 1 : 0)][col + i * (ver ? 0 : 1)] != 0 )//checking vertical or horizontal axis
                return true;
        return false;
    }
    /** check's for adjacent ship's relative to location x,y and along the ship length and direction */
    public static boolean check_Adjacent_Ships(int row, int col, int len_Ship, boolean ver, int[][] board){
        for(int i = 0; i < len_Ship;i++)//loop through the length of the ship
            if(check_Adjacent(row+ i * (ver? 1 : 0), col + i * (ver ? 0 : 1), board))//current coordinate, (x,y)
                return true;//check if there is a ship around that coordinate (row,col)
        return false;
    }

    /**
     check's adjacent spots to coordinates x,y respectively on the board.
     Returns whether there are any ships surrounding it
     */
    public static boolean check_Adjacent(int r, int c, int[][] board){
        boolean flag;
        for(int i = -1; i <= 1; i++){//check all 9 adjacent spots to current coords r,c
            for(int j=-1; j <=1; j++){//[row][col]
                flag = (r+i >= 0 && r+i <board.length && c+j >= 0 && c+j < board[0].length);//check in bounds
                if (flag && board[r+i][c+j]!=0)//if in bounds and tile is taken then return false
                    return true;
            }
        }
        return false;
    }

    /** check if the starting coords of the ship are in the board (2d array), r=row, c=col */
    public static boolean exceeds_Bounds(int r, int c, int[][] board){
        return (c < 0 || c >= board[0].length) || (r < 0 || r >= board.length);
    }

    /** check's that the entire length of the ship is in the board range (2d array), r=row, c=col */
    public static boolean ship_In_Bounds(int r, int c, int len_Ship, boolean ver, int[][] board){
        return ver ? r+len_Ship-1 < 0 || r+len_Ship > board.length : c+len_Ship-1 < 0 || c + len_Ship > board[0].length;
    }

    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Total of " + numberOfGames + " games.");
        for (int i = 1; i <= numberOfGames; i++) {
            scanner.nextLine();
            int seed = scanner.nextInt();
            rnd = new Random(seed);
            scanner.nextLine();
            System.out.println("Game number " + i + " starts.");
            battleshipGame();
            System.out.println("Game number " + i + " is over.");
            System.out.println("------------------------------------------------------------");
        }
        System.out.println("All games are over.");
    }
}
