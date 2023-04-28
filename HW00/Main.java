import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.sort;

public class Main{
    public static Scanner scanner;
    public static Random rnd;

    public static void battleshipGame() {
        //get board length, amount of ships and their length's creat board and ship objects and sort them.
        System.out.println("Enter the board size");//"nXm", n lines, m columns
        String[] board_len = scanner.nextLine().split("X");
        int n_len = Integer.parseInt(board_len[0]);
        int m_len = Integer.parseInt(board_len[1]);
        int[][] p_board = new int[n_len][m_len];//player board, nxm
        int[][] b_board = new int[n_len][m_len];//bot board, nxm

        int[][] guess_p_board = new int[n_len][m_len];//player board
        int[][] guess_b_board = new int[n_len][m_len];//bot/computer board

        //boards are instantiated to 0 when created.

        int[] ships = instantiate_board();
        int user_life = ships.length, bot_life = ships.length;//lives left
        int[][] b_ships_locX = new int[2][ships.length];
        int[][] b_ships_locY = new int[2][ships.length];
        int[][] p_ships_locX = new int[2][ships.length];//0,1 is y ship loc
        int[][] p_ships_locY = new int[2][ships.length];//4 (0,1 is ship x loc), ship.length
        //is the number ship we're checking, 2 is x/y coords

        //now have user place ships on the board, then bot sets board up randomly
        System.out.println("Your current game board:");
        print_board(p_board, false);
        place_user_ships(p_board, ships, p_ships_locX, p_ships_locY);
        place_bot_ships(b_board, ships, b_ships_locX, b_ships_locY);

        //time to play game
        while(true){//run until game is over
            System.out.println("Your current guessing board:");
            print_board(guess_p_board, true);
            System.out.println("Enter a tile to attack");
            bot_life += User_Turn(guess_p_board, b_board, b_ships_locX, b_ships_locY, bot_life);
            if(bot_life == 0){//user wins
                System.out.println("You won the game!");
                return;
            }

            user_life += Bot_Turn(guess_b_board, p_board, p_ships_locX, p_ships_locY, user_life);
            System.out.println("Your current game board:");
            print_board(p_board, false);

            if(user_life == 0){//bot wins
                System.out.println("You lost ):");
                return;
            }
        }
    }

    /***
     * place ships for user, runs until user inputs fills the board with all the given ships
     * @param board - player board, battlefield
     * @param ships - array (int) of ship lengths (from smallest to largest)
     * @param ships_locX - int array, will set x index's locations for the ship's placed
     * @param ships_locY - int array, will set y index's locations for the ship's placed
     */
    public static void place_user_ships(int[][] board, int @NotNull [] ships, int[][] ships_locX, int[][] ships_locY){
        String loc;
        int row, col;
        for(int i=0; i < ships.length; i++) {
            loc = "error";
            System.out.println("Enter location and orientation for battleship of size "+ ships[i]);
            while(loc.equals("error"))
                loc = check_ship_placement(board, ships, i);
            row = Integer.parseInt(String.valueOf(loc.split(",")[0]));
            col= Integer.parseInt(String.valueOf(loc.split(",")[1]));
            place_ship(row, col, ships[i], i, board, loc.split(",")[2].equals("1"), ships_locX, ships_locY);

            //we got a valid ship placement
            System.out.println("Your current game board:");
            print_board(board, false);
        }
    }

    /***
     * places ships for the bot randomly on the battlefield board
     * @param board - battlefield (2d int array)
     * @param ships - integer array containing ships lengths
     * @param ships_locX - int array, will set x index's locations for the ship's placed
     * @param ships_locY - int array, will set y index's locations for the ship's placed
     */
    public static void place_bot_ships(int[] @NotNull [] board, int @NotNull [] ships, int[][] ships_locX, int[][] ships_locY){
        int row=0, col=0, orientation = 0;
        boolean check;
        for(int i=0; i < ships.length; i++) {
            check = false;
            while(!check) {
                row = rnd.nextInt(board.length);
                col = rnd.nextInt(board[0].length);
                orientation = rnd.nextInt(2);
                check = check_bot_ship_placement(board, ships, row, col, orientation, i);
            }
            //we have a valid ship placement therefore we place it on the board accordingly
            place_ship(row, col, ships[i], i, board, orientation == 1, ships_locX, ships_locY);
        }
    }

    /***
     * Run's the user/player's turn to attack the computer/bot, given the player's input coords (x,y)
     * player attacks that position, the variables are updated accordingly and appropriate message printed.
     * if one of the computer's ships got sunk than return -1 to take off a life (battleships remaining). Otherwise 0
     * @param guess_board an int array that contains the player's guesses on tiles throughout the game
     * @param enemy_board the bot/computer's board setup (int[][]) for the game
     * @param enemy_loc_shipsX x coordinates for all the enemy ships, where ships X coords can be accessed at index i
     * @param enemy_loc_shipsY y coordinates for all the enemy ships, where ship Y coords can be accessed at index i
     * @param lives how many lives (battleships remaining) the user/player has left
     * @return -1 if ship sunk, otherwise 0
     */
    public static int User_Turn(int[][] guess_board, int[][] enemy_board, int[][] enemy_loc_shipsX, int[][] enemy_loc_shipsY, int lives){
        int row=-1,col=-1;//just to initialize to run the loop
        String tile;
        while(exceeds_bounds(row, col, enemy_board) || guess_board[row][col] != 0){
            tile = scanner.nextLine().replaceAll("\\s+","");//"x,y"
            row = Integer.parseInt(tile.split(",")[0]);
            col = Integer.parseInt(tile.split(",")[1]);
            if(exceeds_bounds(row, col, enemy_board))
                System.out.println("Illegal tile, try again!");
            else if(guess_board[row][col] != 0)//1=miss,2=hit,3=sunk
                System.out.println("Tile already attacked, try again!");
        }
        //we now have a valid hit which is a hit or miss
        if(enemy_board[row][col] == 0) {
            System.out.println("That is a miss!");
            guess_board[row][col] = 1;//1 is miss, 2 is hit. update guess board.
        }
        if(enemy_board[row][col] == 1){
            System.out.println("That is a hit!");
            guess_board[row][col] = 2;//update guess board.
            enemy_board[row][col] = 2;//update enemy board.

            System.out.println("For making sure, computer game board");
            print_board(enemy_board, false);

            //check if sunk
            if(ship_sunk(enemy_board, enemy_loc_shipsX, enemy_loc_shipsY, row, col)){
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
     * @param guess_board an int array that contains the computer's guesses on tiles throughout the game
     * @param user_board the user's board setup (int[][]) for the game
     * @param user_loc_shipsX x coordinates for all the ships, where ships X coords can be accessed at index i
     * @param user_ships_locY y coordinates for all the ships, where ship Y coords can be accessed at index i
     * @param lives how many lives (battleships remaining) the bot has left
     * @return -1 if ship sunk, otherwise 0
     */
    public static int Bot_Turn(int[][] guess_board, int[][] user_board, int[][] user_loc_shipsX, int[][] user_ships_locY, int lives){
        int row = -1, col = -1;
        while(row < 0 || guess_board[row][col] != 0){//col, row
            //if value is 0 => tile hasn't been hit. else the tile has been hit whether hit, sink or miss
            row = rnd.nextInt(user_board.length);
            col = rnd.nextInt(user_board[0].length);
        }
        String tile_attack = "("+row+", "+col+")";
        System.out.println("The computer attacked " + tile_attack);
        //regular board: miss =  1, hit = 2, ship sunk = 3, else -, (regular board)
        //guess_board: not hit = 0, miss = 1, hit = 2, ship sunk = 3
        if(user_board[row][col] == 0){
            System.out.println("That is a miss!");
            guess_board[row][col] = 1;
        }
        if(user_board[row][col] == 1){
            System.out.println("That is a hit!");
            guess_board[row][col] = 2;
            user_board[row][col] = 2;
            if(ship_sunk(user_board, user_loc_shipsX, user_ships_locY, row, col)){
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
    public static @NotNull String check_ship_placement(int[][] board, int @NotNull [] ships, int i){
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
        if(exceeds_bounds(row, col, board)){
            System.out.println("Illegal tile, try again!");
            return "error";
        }
        if(ship_in_bounds(row, col, ships[i], orientation.equals("1"), board)){
            System.out.println("Battleship exceeds the boundaries of the board, try again!");
            return "error";
        }
        if(check_overlap(row, col, ships[i], orientation.equals("1"), board)){
            System.out.println("Battleship overlaps another battleship, try again!");
            return "error";
        }
        if(check_adjacent_ships(row, col, ships[i], orientation.equals("1"), board)) {
            System.out.println("Adjacent battleship detected, try again!");
            return "error";
        }
        return loc;//no errors so loc is valid
    }

    /*given board, checks ship placement given indexes is valid or not for bot/computer*/
    public static boolean check_bot_ship_placement(int[][] board, int[] ships, int row, int col, int orientation, int i){
        if(orientation != 0 && orientation != 1)//error, invalid value
            return false;
        if(exceeds_bounds(row, col, board))
            return false;
        if(ship_in_bounds(row, col, ships[i], orientation == 1, board))
            return false;
        if(check_overlap(row, col, ships[i], orientation == 1, board))
            return false;
        return !check_adjacent_ships(row, col, ships[i], orientation == 1, board);
    }

    /***
     * given coordinates that represent a valid tile to place the ship, the function places the ship on the board at x,y
     * @param col coordinate
     * @param row coordinate
     * @param ship_len length of a given ship
     * @param ship ship index
     * @param board player/computer board (battlefield)
     * @param ver Vertical
     * @param ships_locX  int array, will set x index's locations for the ship's placed
     * @param ships_locY  int array, will set y index's locations for the ship's placed
     */
    public static void place_ship(int row, int col, int ship_len, int ship, int[][] board, boolean ver, int[][] ships_locX, int[][] ships_locY){
        if(ver) {
            for (int i = row; i < row + ship_len; i++)
                board[i][col] = 1;
            ships_locX[0][ship] = col;//keep track of the battleships x,y locations according to #ship
            ships_locX[1][ship] = col;
            ships_locY[0][ship] = row;
            ships_locY[1][ship] = row + ship_len;
        }
        else {//is horizontal
            for (int i = col; i < col + ship_len; i++)
                board[row][i] = 1;
            ships_locX[0][ship] = col;//keep track of the battleships x,y locations according to #ship
            ships_locX[1][ship] = col + ship_len;
            ships_locY[0][ship] = row;
            ships_locY[1][ship] = row;
        }
    }
    /***
     * finds ship on enemy_board (battlefield) given coordinates x,y and check's if that ship is sunken or not.
     * @param enemy_board the bot/computer's board setup (int[][]) for the game
     * @param enemy_loc_shipsX x coordinates for all the enemy ships, where ship X coords can be accessed at index i
     * @param enemy_loc_shipsY y coordinates for all the enemy ships, where ship Y coords can be accessed at index i
     * @param row row coordinates
     * @param col col coordinates
     * @return - whether ship at coordinates x,y has sunken or not.
     */
    public static boolean ship_sunk(int[][] enemy_board, int[][] enemy_loc_shipsX, int[][] enemy_loc_shipsY, int row, int col){
        int ship = 0;boolean check;
        for(int i = 0; i < enemy_loc_shipsX[0].length; i++){//find ship
            check = enemy_loc_shipsX[0][i] == enemy_loc_shipsX[1][i] && enemy_loc_shipsX[0][i] == col;//check hor
            if(check || col >= enemy_loc_shipsX[0][i] && col < enemy_loc_shipsX[1][i]) {//check if x in range
                check = enemy_loc_shipsY[0][i] == enemy_loc_shipsY[1][i] && enemy_loc_shipsY[0][i] == row;//check ver
                if(check || row >= enemy_loc_shipsY[0][i] && row < enemy_loc_shipsY[1][i]){//check that y is in range
                    ship = i;//we found our ship index
                    break;
                }
            }
        }
        boolean flag_sunk = true, hor = enemy_loc_shipsY[0][ship] == enemy_loc_shipsY[1][ship];
        if(hor) //ship is horizontal
            for (int j = enemy_loc_shipsX[0][ship]; j < enemy_loc_shipsX[1][ship]; j++) {
                flag_sunk = enemy_board[enemy_loc_shipsY[0][ship]][j] == 2;//row is same, traverse col
                if(!flag_sunk)
                    break;
            }
        else//ship is vertical
            for(int j = enemy_loc_shipsY[0][ship]; j < enemy_loc_shipsY[1][ship]; j++) {
                flag_sunk = enemy_board[j][enemy_loc_shipsX[0][ship]] == 2;//col is same, traverse rows
                if(!flag_sunk)
                    break;
            }

        if(flag_sunk){//change values on board so that we know that the ship is sunk
            if(hor) //ship is horizontal
                for (int j = enemy_loc_shipsX[0][ship]; j < enemy_loc_shipsX[1][ship]; j++)
                    enemy_board[enemy_loc_shipsY[0][ship]][j] = 3;
            //change values of tiles of the sunken ship so that we know that the ship has been sunk
            else//ship is vertical
                for(int j = enemy_loc_shipsY[0][ship]; j < enemy_loc_shipsY[1][ship]; j++)
                    enemy_board[j][enemy_loc_shipsX[0][ship]] = 3;//3 means sunk
        }
        //let's change values to 3 since we know ship is sunk so that we don't count it again
        return flag_sunk;
    }

    public static int @NotNull [] instantiate_board(){
        System.out.println("Enter the battleships sizes");
        String ships_input = scanner.nextLine();//:n1Xs1 n2Xs2 ... nkXsk", n is amount, s is length of ship
        String[] battle_ships = ships_input.split("\\s+");
        int cnt_ship = 0;
        for (String battleShip : battle_ships)
            cnt_ship += Integer.parseInt(String.valueOf(battleShip.charAt(0)));//count total amount of ships

        int[] ships = new int[cnt_ship];//list of our ship lengths
        int j=0;
        for (String battleShip : battle_ships) {//setting values to list according to input
            for (int run = 0; run < Integer.parseInt(battleShip.split("X")[0]); run++)
                ships[j++] = Integer.parseInt(battleShip.split("X")[1]);
        }
        sort(ships);//sort encase, although given that it's sorted
        return ships;
    }
    /*
    prints given board
     */
    public static void print_board(int[] @NotNull [] board, boolean guess_tile){
        String tile, check_guess_tile, space;
        System.out.print("   ");
        for (int r = 0; r < board[0].length; r++) {
            space = r < board[0].length-1 ? " " : "";
            System.out.print(r + space);
        }
        System.out.println();
        for (int c = 0; c < board.length; c++) {//cols
            System.out.print((c < 10 ? " " : "") + c + " ");
            for (int r = 0; r < board[0].length; r++) {//rows
                //regular board: miss =  1, hit = 2, ship sunk = 3, else -, (regular board)
                //guess_board: not hit = 0, miss = 1, hit = 2, ship sunk = 3
                tile = board[c][r] == 1 ? "#" : (board[c][r] == 2 || board[c][r] == 3) ? "X" : "–";
                //V if hit (2,3), X (0) if miss, otherwise -
                check_guess_tile = board[c][r] == 1 ? "X" : ((board[c][r] == 2 || board[c][r] == 3) ? "V" : "–");
                System.out.print((guess_tile ? check_guess_tile : tile) + " ");//print guess tile board or normal board
            }
            System.out.println();
        }
    }

    //checks that there isn't another ship in the place that we are trying to place our ship
    public static boolean check_overlap(int row, int col, int len_ship, boolean ver, int[][] board){
        if(ver){
            for(int i=row; i< row + len_ship;i++)
                if(i < board.length && board[i][col] != 0)
                    return true;
            return false;
        }
        for(int i=col; i< col+len_ship;i++)//hor, right-left
            if(i < board[0].length && board[row][i] != 0)
                return true;
        return false;
    }
    //check's for adjacent ship's relative to location x,y and along the ship length and direction
    public static boolean check_adjacent_ships(int row, int col, int len_ship, boolean ver, int[][] board){
        if(ver) {
            for (int i = row; i < row + len_ship; i++)//ship is vertical
                if (check_adjacent(i, col, board))
                    return true;
            return false;
        }
        for(int i=col; i < col+len_ship;i++)//ship is horizontal
            if(check_adjacent(row, i, board))
                return true;
        return false;
    }

    /*
     check's adjacent spots to coordinates x,y respectively on the board.
     Returns whether there are any ships surrounding it
     */
    public static boolean check_adjacent(int r, int c, int[][] board){
        boolean flag;
        for(int i = -1; i <= 1; i++){
            for(int j=-1; j <=1; j++){//[row][col]
                flag = (r+i >= 0 && r+i <board.length && c+j >= 0 && c+j < board[0].length);//check in bounds
                if (flag && board[r+i][c+j]!=0)//if in bounds and tile is taken then return false
                    return true;
            }
        }
        return false;
    }

    //check if the starting coords of the ship are in the board (2d array), r=row, c=col
    public static boolean exceeds_bounds(int r, int c, int[][] board){
        return (c < 0 || c >= board[0].length) || (r < 0 || r >= board.length);
    }

    //check's that the entire length of the ship is in the board range (2d array), r=row, c=col
    public static boolean ship_in_bounds(int r, int c, int len_ship, boolean ver, int[][] board){
        return ver ? r+len_ship-1 < 0 || r+len_ship-1 >= board.length : c+len_ship-1 < 0 || c + len_ship-1 >= board[0].length;
    }

    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Total of " + numberOfGames + " games.");

        for (int i = 1; i <= numberOfGames; i++) {
            scanner.nextLine();
            String s = scanner.next();
            int seed = Integer.parseInt(s);
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
