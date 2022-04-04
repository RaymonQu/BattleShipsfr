import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/** The main logic of the game within this class
 * */
public class BattleShipGame {
    /** represents the Player
     * */
    private Player player;
    /** scanner necessary for input
     * */
    private Scanner scan;

    /**initializes player and scanner*/
    public BattleShipGame(){
        player = new Player();
        scan = new Scanner(System.in);
    }

    /**presents main menu screen, where all other methods in class is used to make game.
     * Where all the logic is implemented, and the game itself.
     * */
    public void start(){
        System.out.println("             BATTLE SHIP\n\n\n\n        Press Enter to Start");
        scan.nextLine();
        System.out.println("The rules are simple, each tile is represented by a letter and number seen on the left and top. " +
                            "\nYou choose which coordinates to shoot on the hidden board and if you beat shoot all the boat tiles, you win. " +
                            "\nHowever you must not lose your own ships on your board" + "\n        Press Enter to Continue");
        scan.nextLine();
        player.getBoard().createBoard();
        player.getBoard().randomlyPutShipsOnHiddenBoard(true);
        updateBoard();
        System.out.println("Do you wish for (R)andom board generation or (M)anual setup?");
        String choice = scan.nextLine();
        int counter = 0;
        while(counter != 1) {
            if (choice.toUpperCase().equals("R")) {
                player.getBoard().randomlyPutShipsOnHiddenBoard(false);
                counter++;
            }
            else if (choice.toUpperCase().equals("M")) {
                setUp();
                counter++;
            }
        }
        updateBoard();
        System.out.println("LET THE BATTLE COMMENCE!!!!");
        while(!Victory() || !GameOver()){
            shoot();
            getShot();
            updateBoard();
        }
        if(Victory()) {
            System.out.println("Congratulations, You have eliminated the enemy and saved your fleet!");
        }
        else{
            System.out.println("Your fleet has been destroyed... Game Over");
        }
    }

    /** represents player shooting at enemy board.
     * Takes input on where to shoot and uses arraylist to properly deal with letters on row the row.
     * then uses method from board.
     * Preconditon: Has to be in bounds to change board
     * */
    public void shoot(){
        String[] arr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        System.out.println("What row will you shoot?");
        String letterRow = scan.nextLine();
        int row = 0;
        for(int i = 0; i < arr.length; i++){
            if(letterRow.toUpperCase().equals(arr[i])){
                row = i;
            }
        }
        System.out.println("What column will you shoot?");
        int col = scan.nextInt();
        scan.nextLine();
        player.getBoard().missOrHit(row, col - 1);
    }

    /** represents player board getting shot. This is random. Would've not been but that AI would be hard.
     * checks to see if area has already been shot before
     *
     * */
    public void getShot(){
        int row = (int) (Math.random() * 10);
        int col = (int) (Math.random() * 10);
        if(player.getBoard().getBoard()[row][col].ishit()) {
            getShot();
        }
        player.getBoard().becomeMissOrHit(row, col);
    }

    /** prints out player and computer's board*/
    public void updateBoard(){
        System.out.println("        Your Board");
        player.getBoard().printBoard();
        System.out.println("        Computer Board");
        player.getBoard().printHiddenBoard();
    }

    /** The option to place player ships manually, continously asks until valid answers are given using while loop.*/
    public void setUp(){
        ArrayList<Ships> shippies = player.getBoard().getBoats();
        System.out.println(shippies.size());
        int row;
        int col;
        String dir;
        while(shippies.size() != 5){
            if(shippies.size() == 0){
                row = askForRow(shippies.size());
                System.out.println("Select a column");
                col = scan.nextInt() - 1;
                scan.nextLine();
                dir = askForDir();
                Ships newboat = new Ships(2, "D");
                if(player.getBoard().putShipOnBoard(newboat, row, col, dir.toUpperCase(), false)){
                    shippies.add(newboat);
                }
                player.getBoard().printBoard();
            }
            if(shippies.size() == 1){
                row = askForRow(shippies.size());
                System.out.println("Select a column");
                col = scan.nextInt() - 1;
                scan.nextLine();
                dir = askForDir();
                Ships newboat = new Ships(3, "S");
                if(player.getBoard().putShipOnBoard(newboat, row, col, dir.toUpperCase(), false)){
                    shippies.add(newboat);
                }
                player.getBoard().printBoard();
            }
            if(shippies.size() == 2){
                row = askForRow(shippies.size());
                System.out.println("Select a column");
                col = scan.nextInt() - 1;
                scan.nextLine();
                dir = askForDir();
                Ships newboat = new Ships(3, "C");
                if(player.getBoard().putShipOnBoard(newboat, row, col, dir.toUpperCase(), false)){
                    shippies.add(newboat);
                }
                player.getBoard().printBoard();
            }
            if(shippies.size() == 3){
                row = askForRow(shippies.size());
                System.out.println("Select a column");
                col = scan.nextInt() - 1;
                scan.nextLine();
                dir = askForDir();
                Ships newboat = new Ships(4, "B");
                if(player.getBoard().putShipOnBoard(newboat, row, col, dir.toUpperCase(), false)){
                    shippies.add(newboat);
                }
                player.getBoard().printBoard();
            }
            if(shippies.size() == 4){
                row = askForRow(shippies.size());
                System.out.println("Select a column");
                col = scan.nextInt() - 1;
                scan.nextLine();
                dir = askForDir();
                Ships newboat = new Ships(5, "A");
                if(player.getBoard().putShipOnBoard(newboat, row, col, dir.toUpperCase(), false)){
                    shippies.add(newboat);
                }
                player.getBoard().printBoard();
            }
        }
    }
    /** helper method for manual placement, asks for row, and uses arraylist of letters to change letters to numbers.
     *
     * Precondition: input must be above 0, below 11
     * @return row number after conversion.*/
    private int askForRow(int num){
        System.out.println("Where would you like to place your " + player.getBoard().getHiddenBoats().get(num).getSize() + " tile long ship?\nSelect a row. Ships cannot overlap or go out of bounds");
        String[] arr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String letterRow = scan.nextLine();
        int row = 0;
        for(int i = 0; i < arr.length; i++){
            if(letterRow.toUpperCase().equals(arr[i])){
                row = i;
            }
        }
        return row;
    }

    /** helper method used for manual placement, asks for number that represents a direction. Method converts into string.
     *
     * Precondition: input must be above 0, below 5
     * @return a string representing a direction
     * */
    public String askForDir(){
        System.out.println("What direction? North (1), South (2), East (3), West (4)");
        int dir = scan.nextInt();
        scan.nextLine();
        String realDir = "";
        if (dir == 1){
            realDir = "N";
        }
        if (dir == 2){
            realDir = "S";
        }
        if (dir == 3){
            realDir = "E";
        }
        if (dir == 4){
            realDir = "W";
        }
        return realDir;
    }

    /** represents the win condition. Iterates through computer loop to count Xs each time, until total gets to 17.
     * 17 Xs means all ships have been hit therefore player wins and game is won.
     *
     * @return boolean representing if victory has been won or not. True wins and false for not. */
    public boolean Victory(){
        Spaces[][] hidBoard = player.getBoard().getHiddenBoard();
        int xCounter = 0;
        for(int i = 0; i < hidBoard.length; i++){
            for(int k = 0; k < hidBoard[0].length; k++){
                if(hidBoard[i][k].getSymbol().toUpperCase().equals("X")){
                    xCounter++;
                }
            }
        }
        if(xCounter == 17){
            return true;
        }
        return false;
    }

    /** represents the lose condition. If all your ships are Xs you will lose similar to victory method but reversed.
     *
     * @return boolean representing if game is over or not.
     * */

    public boolean GameOver(){
        Spaces[][] Board = player.getBoard().getBoard();
        int xCounter = 0;
        for(int i = 0; i < Board.length; i++){
            for(int k = 0; k < Board[0].length; k++){
                if(Board[i][k].getSymbol().toUpperCase().equals("X")){
                    xCounter++;
                }
            }
        }
        if(xCounter == 17){
            return true;
        }
        return false;
    }





}
