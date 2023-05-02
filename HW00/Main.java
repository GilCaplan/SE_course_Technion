import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main{
    public static Scanner scanner;
    public static Random rnd;
    //regular board: miss =  1, hit = 2, ship sunk = 3, else -, (regular board)
    //guessBoard: not hit = 0, miss = 1, hit = 2, ship sunk = 3
    public final static int MISS = 1, HIT = 2, SUNK = 3, NOT_HIT = 0, SHIP = 1;

    public static void battleshipGame() {
        //get board length, amount of ships and their length's creat board and ship objects and sort them.
        System.out.println("Enter the board size");//"nXm", n lines, m columns
        String[] boardLen = scanner.nextLine().split("X");
        int nLen = Integer.parseInt(boardLen[0]);
        int mLen = Integer.parseInt(boardLen[1]);
        int[][] playerBoard = new int[nLen][mLen];//player board, nxm
        int[][] botBoard = new int[nLen][mLen];//bot board, nxm

        int[][] guessPlayerBoard = new int[nLen][mLen];//player board
        int[][] guessBotBoard = new int[nLen][mLen];//bot/computer board

        //boards are instantiated to 0 when created.

        int[] ships = instantiateBoard();
        int userLife = ships.length, botLife = ships.length;//lives left
        int[][] botShipsLocX = new int[2][ships.length];
        int[][] botShipsLocY = new int[2][ships.length];
        int[][] playerShipsLocX = new int[2][ships.length];//0,1 is y ship loc
        int[][] playerShipsLocY = new int[2][ships.length];//4 (0,1 is ship x loc), ship.length
        //is the number ship we're checking, 2 is x/y coords

        //now have user place ships on the board, then bot sets board up randomly
        System.out.println("Your current game board:");
        printBoard(playerBoard, false);
        placeUserShips(playerBoard, ships, playerShipsLocX, playerShipsLocY);
        placeBotShips(botBoard, ships, botShipsLocX, botShipsLocY);

        while(true){//time to play game, run until game is over
            System.out.println("Your current guessing board:");
            printBoard(guessPlayerBoard, true);
            System.out.println("Enter a tile to attack");
            botLife += userTurn(guessPlayerBoard, botBoard, botShipsLocX, botShipsLocY, botLife);//-1 if ship sunk
            if(botLife == 0){//user wins
                System.out.println("You won the game!");
                return;
            }

            userLife += botTurn(guessBotBoard, playerBoard, playerShipsLocX, playerShipsLocY, userLife);//-1 if ship sunk
            System.out.println("Your current game board:");
            printBoard(playerBoard, false);

            if(userLife == 0){//bot wins
                System.out.println("You lost ):");
                return;
            }
        }
    }

    /***
     * place ships for user, runs until user inputs fills the board with all the given ships
     * @param board - player board, battlefield
     * @param ships - array (int) of ship lengths (from smallest to largest)
     * @param shipsLocX - int array, will set x index's locations for the ship's placed
     * @param shipsLocY - int array, will set y index's locations for the ship's placed
     */
    public static void placeUserShips(int[][] board, int[] ships, int[][] shipsLocX, int[][] shipsLocY){
        String loc;
        int row, col;
        for(int i=0; i < ships.length; i++) {
            loc = "error";
            System.out.println("Enter location and orientation for battleship of size "+ ships[i]);
            while(loc.equals("error"))
                loc = checkShipPlacement(board, ships, i);
            row = Integer.parseInt(String.valueOf(loc.split(",")[0]));
            col= Integer.parseInt(String.valueOf(loc.split(",")[1]));
            placeShip(row, col, ships[i], i, board, loc.split(",")[2].equals("1"), shipsLocX, shipsLocY);

            //we got a valid ship placement
            System.out.println("Your current game board:");
            printBoard(board, false);
        }
    }

    /***
     * places ships for the bot randomly on the battlefield board
     * @param board - battlefield (2d int array)
     * @param ships - integer array containing ships lengths
     * @param shipsLocX - int array, will set x index's locations for the ship's placed
     * @param shipsLocY - int array, will set y index's locations for the ship's placed
     */
    public static void placeBotShips(int[][] board, int[] ships, int[][] shipsLocX, int[][] shipsLocY){
        int row = 0, col = 0, orientation = 0;
        boolean check;
        for(int i=0; i < ships.length; i++) {
            check = false;
            while(!check) {
                row = rnd.nextInt(board.length);
                col = rnd.nextInt(board[0].length);
                orientation = rnd.nextInt(2);
                check = checkBotShipPlacement(board, ships, row, col, orientation, i);
            }
            //we have a valid ship placement therefore we place it on the board accordingly
            placeShip(row, col, ships[i], i, board, orientation == 1, shipsLocX, shipsLocY);
        }
    }

    /***
     * Run's the user/player's turn to attack the computer/bot, given the player's input coords (x,y)
     * player attacks that position, the variables are updated accordingly and appropriate message printed.
     * if one of the computer's ships got sunk than return -1 to take off a life (battleships remaining). Otherwise 0
     * @param guessBoard an int array that contains the player's guesses on tiles throughout the game
     * @param enemyBoard the bot/computer's board setup (int[][]) for the game
     * @param enemyLocShipsX x coordinates for all the enemy ships, where ships X coords can be accessed at index i
     * @param enemyLocShipsY y coordinates for all the enemy ships, where ship Y coords can be accessed at index i
     * @param lives how many lives (battleships remaining) the user/player has left
     * @return -1 if ship sunk, otherwise 0
     */
    public static int userTurn(int[][] guessBoard, int[][] enemyBoard, int[][] enemyLocShipsX, int[][] enemyLocShipsY, int lives){
        int row=-1, col=-1;//just to initialize to run the loop
        String tile;
        while(exceedsBounds(row, col, enemyBoard) || guessBoard[row][col] != 0){
            tile = scanner.nextLine().replaceAll("\\s+","");//"x,y"
            row = Integer.parseInt(tile.split(",")[0]);
            col = Integer.parseInt(tile.split(",")[1]);
            if(exceedsBounds(row, col, enemyBoard))
                System.out.println("Illegal tile, try again!");
            else if(guessBoard[row][col] != 0)//1=miss,2=hit,3=sunk
                System.out.println("Tile already attacked, try again!");
        }
        //we now have a valid hit which is a hit or miss
        if(enemyBoard[row][col] == NOT_HIT) {
            System.out.println("That is a miss!");
            guessBoard[row][col] = MISS;//1 is miss, 2 is hit. update guess board.
        }
        if(enemyBoard[row][col] == SHIP){
            System.out.println("That is a hit!");
            guessBoard[row][col] = enemyBoard[row][col] = HIT;//update enemy board & guess board.

            //check if sunk
            if(shipSunk(enemyBoard, enemyLocShipsX, enemyLocShipsY, row, col)){
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
     * @param guessBoard an int array that contains the computer's guesses on tiles throughout the game
     * @param userBoard the user's board setup (int[][]) for the game
     * @param userLocShipsX x coordinates for all the ships, where ships X coords can be accessed at index i
     * @param userShipsLocYY y coordinates for all the ships, where ship Y coords can be accessed at index i
     * @param lives how many lives (battleships remaining) the bot has left
     * @return -1 if ship sunk, otherwise 0
     */
    public static int botTurn(int[][] guessBoard, int[][] userBoard, int[][] userLocShipsX, int[][] userShipsLocYY, int lives){
        int row = -1, col = -1;
        while(row < 0 || guessBoard[row][col] != 0){//col, row
            //if value is 0 => tile hasn't been hit. else the tile has been hit whether hit, sink or miss
            row = rnd.nextInt(userBoard.length);
            col = rnd.nextInt(userBoard[0].length);
        }
        String tileAttack = "("+row+", "+col+")";
        System.out.println("The computer attacked " + tileAttack);
        if(userBoard[row][col] == NOT_HIT){
            System.out.println("That is a miss!");
            guessBoard[row][col] = MISS;
        }
        if(userBoard[row][col] == SHIP){
            System.out.println("That is a hit!");
            guessBoard[row][col] = userBoard[row][col] = HIT;
            if(shipSunk(userBoard, userLocShipsX, userShipsLocYY, row, col)){
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
    public static String checkShipPlacement(int[][] board, int[] ships, int i){
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
        if(exceedsBounds(row, col, board)){
            System.out.println("Illegal tile, try again!");
            return "error";
        }
        if(shipInBounds(row, col, ships[i], orientation.equals("1"), board)){
            System.out.println("Battleship exceeds the boundaries of the board, try again!");
            return "error";
        }
        if(checkOverlap(row, col, ships[i], orientation.equals("1"), board)){
            System.out.println("Battleship overlaps another battleship, try again!");
            return "error";
        }
        if(checkAdjacentShips(row, col, ships[i], orientation.equals("1"), board)) {
            System.out.println("Adjacent battleship detected, try again!");
            return "error";
        }
        return loc;//no errors so loc is valid
    }

    /** given board, checks ship placement given indexes is valid or not for bot/computer */
    public static boolean checkBotShipPlacement(int[][] board, int[] ships, int row, int col, int orientation, int i){
        if(orientation != 0 && orientation != 1)//error, invalid orientation
            return false;
        if(exceedsBounds(row, col, board))
            return false;
        if(shipInBounds(row, col, ships[i], orientation == 1, board))
            return false;
        if(checkOverlap(row, col, ships[i], orientation == 1, board))
            return false;
        return !checkAdjacentShips(row, col, ships[i], orientation == 1, board);
    }

    /***
     * given coordinates that represent a valid tile to place the ship, the function places the ship on the board at x,y
     * @param col coordinate
     * @param row coordinate
     * @param shipLen length of a given ship
     * @param ship ship index
     * @param board player/computer board (battlefield)
     * @param ver Vertical
     * @param shipsLocX  int array, will set x index's locations for the ship's placed
     * @param shipsLocY  int array, will set y index's locations for the ship's placed
     */
    public static void placeShip(int row, int col, int shipLen, int ship, int[][] board, boolean ver, int[][] shipsLocX, int[][] shipsLocY){
        if(ver) {//ship is vertical
            for (int i = 0; i < shipLen; i++)
                board[row + i][col] = SHIP;
            shipsLocX[0][ship] = shipsLocX[1][ship] = col;//keep track of the battleships x,y locations according to #ship
            shipsLocY[0][ship] = row;
            shipsLocY[1][ship] = row + shipLen;// <=> [row, row+shipLen)
        }
        else {//ship is horizontal
            for (int i = 0; i < shipLen; i++)
                board[row][col + i] = SHIP;
            shipsLocX[0][ship] = col;//keep track of the battleships x,y locations according to #ship
            shipsLocX[1][ship] = col + shipLen;// <=> [col, col+shipLen)
            shipsLocY[0][ship] = shipsLocY[1][ship] = row;
        }
    }
    /***
     * finds ship on enemyBoard (battlefield) given coordinates x,y and check's if that ship is sunken or not.
     * @param enemyBoard the bot/computer's board setup (int[][]) for the game
     * @param enemyLocShipsX x coordinates for all the enemy ships, where ship X coords can be accessed at index i
     * @param enemyLocShipsY y coordinates for all the enemy ships, where ship Y coords can be accessed at index i
     * @param row row coordinates
     * @param col col coordinates
     * @return Boolean value of whether ship at coordinates x,y has sunken or not.
     */
    public static boolean shipSunk(int[][] enemyBoard, int[][] enemyLocShipsX, int[][] enemyLocShipsY, int row, int col){
        int ship = 0;
        boolean check;
        for(int i = 0; i < enemyLocShipsX[0].length; i++){//find ship
            check = enemyLocShipsX[0][i] == enemyLocShipsX[1][i] && enemyLocShipsX[0][i] == col;//check hor
            if(check || col >= enemyLocShipsX[0][i] && col < enemyLocShipsX[1][i]) {//check if x in range
                check = enemyLocShipsY[0][i] == enemyLocShipsY[1][i] && enemyLocShipsY[0][i] == row;//check ver
                if(check || row >= enemyLocShipsY[0][i] && row < enemyLocShipsY[1][i]){//check that y is in range
                    ship = i;//we found our ship index
                    break;
                }
            }
        }
        boolean flagSunk = true, hor = enemyLocShipsY[0][ship] == enemyLocShipsY[1][ship];
        if(hor) //ship is horizontal, (can optimize and shorten code, I feel too lazy to do so lol)
            for (int j = enemyLocShipsX[0][ship]; j < enemyLocShipsX[1][ship]; j++) {
                flagSunk = enemyBoard[enemyLocShipsY[0][ship]][j] == HIT;//row is same, traverse col
                if(!flagSunk)
                    break;
            }
        else//ship is vertical
            for(int j = enemyLocShipsY[0][ship]; j < enemyLocShipsY[1][ship]; j++) {
                flagSunk = enemyBoard[j][enemyLocShipsX[0][ship]] == HIT;//col is same, traverse rows
                if(!flagSunk)
                    break;
            }

        if(flagSunk){//change values on board so that we know that the ship is sunk
            if(hor) //ship is horizontal
                for (int j = enemyLocShipsX[0][ship]; j < enemyLocShipsX[1][ship]; j++)
                    enemyBoard[enemyLocShipsY[0][ship]][j] = SUNK;
                //change values of tiles of the sunken ship so that we know that the ship has been sunk
            else//ship is vertical
                for(int j = enemyLocShipsY[0][ship]; j < enemyLocShipsY[1][ship]; j++)
                    enemyBoard[j][enemyLocShipsX[0][ship]] = SUNK;//3 means sunk
        }//let's change values to 3 since we know ship is sunk so that we don't count it again
        return flagSunk;
    }

    /**
     * instantiates the amount and length of battleShips to an array according to input N1XM1 ... NkXMk
     * @return ship array (where each item in the array represents the length of the ship)
     */
    public static int[] instantiateBoard(){
        System.out.println("Enter the battleships sizes");
        String[] battleShips = scanner.nextLine().split("\\s+");
        int cntShip = 0, j = 0;

        for (String battleShip : battleShips)//split when given "n1Xs1 n2Xs2 ... nkXsk"
            cntShip += Integer.parseInt(String.valueOf(battleShip.charAt(0)));//count total amount of ships

        int[] ships = new int[cntShip];//list of our ship lengths
        for (String battleShip : battleShips) //setting values to list according to input
            for (int run = 0; run < Integer.parseInt(battleShip.split("X")[0]); run++)
                ships[j++] = Integer.parseInt(battleShip.split("X")[1]);
        return ships;
    }
    /** prints given board whether it's the guessing board or battle game board */
    public static void printBoard(int[][] board, boolean guessTile){
        String tile, checkGuessTile, extraSpaces = "";
        for(int i = board.length; i > 0; i /= 10)
            extraSpaces += " ";
        System.out.print(extraSpaces);
        for (int r = 0; r < board[0].length; r++) {
            extraSpaces = (r==0? " ":"") +( (board[0].length>10&&r<10)?" ":"") + ((board[0].length>100&&r<100)?" ":"");
            System.out.print(extraSpaces + r + (r < board[0].length - 1 ? " " : ""));//r + whitespace if not end of the line
        }
        System.out.println();
        for (int c = 0; c < board.length; c++) {//cols-is index...
            extraSpaces = (board.length>10&&c<10?" ":"") + (board.length>100&&c<100?" ":"");
            System.out.print(extraSpaces  + c);
            for (int r = 0; r < board[0].length; r++) {//rows
                tile = board[c][r] == SHIP ? "#" : (board[c][r] == HIT || board[c][r] == SUNK) ? "X" : "–";
                //V if hit (2,3), X (0) if miss, otherwise -
                checkGuessTile = board[c][r] == MISS ? "X" : ((board[c][r] == HIT || board[c][r] == SUNK) ? "V" : "–");
                extraSpaces = (r==0? " ":"") +(board[0].length>10?" ":"") + (board[0].length>100?" ":"");
                System.out.print(extraSpaces + (guessTile ? checkGuessTile : tile) + (r<board[0].length-1?" ":""));
                //print guess tile board or normal board tile
            }
            System.out.println();
        }
        System.out.println();
    }

    /** checks that there isn't another ship in the place that we are trying to place our ship */
    public static boolean checkOverlap(int row, int col, int lenShip, boolean ver, int[][] board){
        for(int i = 0; i < lenShip; i++)//loop through the length of the ship
            if(board[row+ i * (ver? 1 : 0)][col + i * (ver ? 0 : 1)] != 0 )//checking vertical or horizontal axis
                return true;
        return false;
    }
    /** check's for adjacent ship's relative to location x,y and along the ship length and direction */
    public static boolean checkAdjacentShips(int row, int col, int lenShip, boolean ver, int[][] board){
        for(int i = 0; i < lenShip;i++)//loop through the length of the ship
            if(checkAdjacent(row+ i * (ver? 1 : 0), col + i * (ver ? 0 : 1), board))//current coordinate, (x,y)
                return true;//check if there is a ship around that coordinate (row,col)
        return false;
    }

    /**
     check's adjacent spots to coordinates x,y respectively on the board.
     Returns whether there are any ships surrounding it
     */
    public static boolean checkAdjacent(int r, int c, int[][] board){
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
    public static boolean exceedsBounds(int r, int c, int[][] board){
        return (c < 0 || c >= board[0].length) || (r < 0 || r >= board.length);
    }

    /** check's that the entire length of the ship is in the board range (2d array), r=row, c=col */
    public static boolean shipInBounds(int r, int c, int lenShip, boolean ver, int[][] board){
        return ver ? r+lenShip-1 < 0 || r+lenShip > board.length : c+lenShip-1 < 0 || c + lenShip > board[0].length;
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
