import java.util.ArrayList;
/** Class represents the game boards and their contents.
 * @Author Raymon Qu
 * */

public class Board {
    /** Player board, 2D array of Spaces*/
    private Spaces[][] Board;

    /** Computer Board, 2D array of Spaces, hiddenBoard because player can't see its space signifiers*/
    private Spaces[][] hiddenBoard;

    /** Player boats, arraylist of Ships */
    private ArrayList<Ships> boats;

    /** Computer boats, arraylist of Ships */
    private ArrayList<Ships> hiddenBoats;

    /** Instantiates a Board object. */
    public Board(){
        Board = new Spaces[10][10];
        hiddenBoard = new Spaces[10][10];
        boats = new ArrayList<Ships>();
        hiddenBoats = new ArrayList<Ships>();

    }

    /** Fills both 2D array objects with Spaces objects */
    public void createBoard(){
        for(int i = 0; i < 10; i++){
            for (int k = 0; k < 10; k++){
                Board[i][k] = new Spaces(i, k);
                hiddenBoard[i][k] = new Spaces(i, k);
            }
        }
    }

    /** Prints out Player Board. Uses an array with letters for the rows. */
    public void printBoard(){
        String[] arr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        int counter = 0;
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for(Spaces[] listODashes: Board){
            System.out.print(arr[counter] + " ");
            counter++;
            for(Spaces realDashes: listODashes){
                System.out.print(realDashes.getSymbol() + " ");
            }
            System.out.println();
        }
    }

    /** Prints Computer Board, same as Player but with computer 2D array */
    public void printHiddenBoard(){
        String[] arr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        int counter = 0;
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for(Spaces[] listODashes: hiddenBoard){
            System.out.print(arr[counter] + " ");
            counter++;
            for(Spaces realDashes: listODashes){
                System.out.print(realDashes.getSymbol() + " ");
            }
            System.out.println();
        }
    }

    /** Changes space objects on board to represent ships.
     * Takes into account various parameters before it adds spaces in the corresponding direction.
     * Checks for overlap and returns false if so or if the ship goes out of the Boards bounds.
     *
     * @param ship the ship that is being represented.
     * @param row the row where the spaces will originate
     * @param column The column where the spaces will originate
     * @param direction the direction at which the spaces will head once origin is found
     * @param hidden used to determine which board to add the new space objects to
     *
     * @return whether the operation was successful
     * */
    public boolean putShipOnBoard(Ships ship, int row, int column, String direction, boolean hidden){
        int counter;
        Spaces[][] whichBoard;
        if(hidden){
            whichBoard = hiddenBoard;
        }
        else{
            whichBoard = Board;
        }
        if (direction.toUpperCase().equals("W")) {
            counter = column;
            if (column - ship.getSize() > 0 && doesNotOverlap(row, column, ship.getSize(), "W", whichBoard)) {
                for(int i = 0; i < ship.getSize(); i++){
                    Spaces newSpace = null;
                    if(!hidden){
                        newSpace = new Spaces(row, counter, true);
                        Board[row][counter] = newSpace;
                        Board[row][counter].setSymbol(ship.getSignifier());
                    }
                    else {
                        newSpace = new Spaces(counter, column, true);
                        hiddenBoard[row][counter] = newSpace;
                    }
                    ship.addSpaces(newSpace);
                    counter--;
                }
            }
            else{
                return false;
            }
        }
        else if(direction.toUpperCase().equals("E")){
            counter = column;
            if (column + ship.getSize() < 10  && doesNotOverlap(row, column, ship.getSize(), "E", whichBoard)) {
                for(int i = 0; i < ship.getSize(); i++){
                    Spaces newSpace = null;
                    if(!hidden){
                        newSpace = new Spaces(row, counter, true);
                        Board[row][counter] = newSpace;
                        Board[row][counter].setSymbol(ship.getSignifier());
                    }
                    else {
                        newSpace = new Spaces(counter, column, true);
                        hiddenBoard[row][counter] = newSpace;
                    }
                    ship.addSpaces(newSpace);
                    counter++;
                }
            }
            else{
                return false;
            }
        }
        else if(direction.toUpperCase().equals("S")){
            counter = row;
            if (row + ship.getSize() < 10  && doesNotOverlap(row, column, ship.getSize(), "S", whichBoard)) {
                for(int i = 0; i < ship.getSize(); i++){
                    Spaces newSpace = null;
                    if(!hidden){
                        newSpace = new Spaces(counter, column, true);
                        Board[counter][column] = newSpace;
                        Board[counter][column].setSymbol(ship.getSignifier());
                    }
                    else {
                        newSpace = new Spaces(counter, column, true);
                        hiddenBoard[counter][column] = newSpace;
                    }
                    ship.addSpaces(newSpace);
                    counter++;
                }
            }
            else{
                return false;
            }
        }
        else if(direction.toUpperCase().equals("N")){
            counter = row;
            if (row - ship.getSize() > 0 && doesNotOverlap(row, column, ship.getSize(), "N", whichBoard)) {
                for(int i = 0; i < ship.getSize(); i++){
                    Spaces newSpace = null;
                    if(!hidden){
                        newSpace = new Spaces(counter, column, true);
                        Board[counter][column] = newSpace;
                        Board[counter][column].setSymbol(ship.getSignifier());
                    }
                    else {
                        newSpace = new Spaces(counter, column, true);
                        hiddenBoard[counter][column] = newSpace;
                    }
                    ship.addSpaces(newSpace);
                    counter--;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }

    /** Randomly puts spaces on board representing the ships.
     * Uses a while loop to make sure no ships are skipped in case of a false return
     * Uses random coordinates. Uses previous method to put the new spaces on Board.
     * Can randomize hidden or regular board.
     *
     * @param hidden used to determine whether to randomize hidden or regular board
     * */

    public void randomlyPutShipsOnHiddenBoard(boolean hidden){
        int counter = 0;
        while(counter < 5) {
                if(counter == 0){
                    int row = (int) (Math.random() * 10);
                    int column = (int) (Math.random() * 10);
                    Ships newboat = new Ships(2, "D");
                    if(hidden) {
                        if (putShipOnBoard(newboat, row, column, randomDir(), true)) {
                            counter++;
                            hiddenBoats.add(newboat);
                        }
                    }
                    else{
                        if (putShipOnBoard(newboat, row, column, randomDir(), false)) {
                            counter++;
                            boats.add(newboat);
                        }
                    }
                }
                if(counter == 1){
                    int row = (int) (Math.random() * 10);
                    int column = (int) (Math.random() * 10);
                    Ships newboat = new Ships(3, "S");
                    if(hidden) {
                        if (putShipOnBoard(newboat, row, column, randomDir(), true)) {
                            counter++;
                            hiddenBoats.add(newboat);
                        }
                    }
                    else{
                        if (putShipOnBoard(newboat, row, column, randomDir(), false)) {
                            counter++;
                            boats.add(newboat);
                        }
                    }
                }
                if(counter == 2){
                    int row = (int) (Math.random() * 10);
                    int column = (int) (Math.random() * 10);
                    Ships newboat = new Ships(3, "C");
                    if(hidden) {
                        if (putShipOnBoard(newboat, row, column, randomDir(), true)) {
                            counter++;
                            hiddenBoats.add(newboat);
                        }
                    }
                    else{
                        if (putShipOnBoard(newboat, row, column, randomDir(), false)) {
                            counter++;
                            boats.add(newboat);
                        }
                    }
                }
                if(counter == 3){
                    int row = (int) (Math.random() * 10);
                    int column = (int) (Math.random() * 10);
                    Ships newboat = new Ships(4, "B");
                    if(hidden) {
                        if (putShipOnBoard(newboat, row, column, randomDir(), true)) {
                            counter++;
                            hiddenBoats.add(newboat);
                        }
                    }
                    else{
                        if (putShipOnBoard(newboat, row, column, randomDir(), false)) {
                            counter++;
                            boats.add(newboat);
                        }
                    }
                }
                if(counter == 4){
                    int row = (int) (Math.random() * 10);
                    int column = (int) (Math.random() * 10);
                    Ships newboat = new Ships(5, "A");
                    if(hidden) {
                        if (putShipOnBoard(newboat, row, column, randomDir(), true)) {
                            counter++;
                            hiddenBoats.add(newboat);
                        }
                    }
                    else{
                        if (putShipOnBoard(newboat, row, column, randomDir(), false)) {
                            counter++;
                            boats.add(newboat);
                        }
                    }
                }
        }
    }

    /** helper method for randomships method. Used to find a random direction to use.
     *
     * @return String representing a direction
     * */
    private String randomDir(){
        int direction = (int) (Math.random() * 4) + 1;
        String realDir = "";
        if(direction == 1){
            realDir = "E";
        }
        if(direction == 2){
            realDir = "W";
        }
        if(direction == 3){
            realDir = "N";
        }
        if(direction == 4){
            realDir = "S";
        }
        return realDir;
    }

    /** Helper method for put ships on board method. Determines if newly added ship will overlap or not.
     * It checks the spaces ahead of the origin to see if it's part of another boat or not.
     * Then returns a value approving its addition.
     *
     * @param row origin row to check if there is an overlap
     * @param column origin column
     * @param size how much the loop will have to check for
     * @param direction the cardinal direction for the method to go to check if the spaces are taken
     * @param notRelatedToOtherBoard used in putshipsonboard method to find if its checking hidden board or player board.
     *
     * @return a boolean value representing if it does overlap or not, false if it does, true if not.
     * */
    private static boolean doesNotOverlap(int row, int column, int size, String direction, Spaces[][] notRelatedToOtherBoard){
        for(int i = 0; i < size; i++){
            if(direction.equals("W")){
                if(notRelatedToOtherBoard[row][column].partOfBoat()){
                    return false;
                }
                column--;
            }
            if(direction.equals("E")){
                if(notRelatedToOtherBoard[row][column].partOfBoat()){
                    return false;
                }
                column++;
            }
            if(direction.equals("S")){
                if(notRelatedToOtherBoard[row][column].partOfBoat()){
                    return false;
                }
                row++;
            }
            if(direction.equals("N")){
                if(notRelatedToOtherBoard[row][column].partOfBoat()){
                    return false;
                }
                row--;
            }
        }
        return true;
    }

    /** method used to change symbol of space on player board to either an X, representing a hit, or O, representing a miss
     * if the space's partOfBoat variable is true, its a hit. Represents getting shot at by computer.
     * Checks if tiles been hit previously
     *
     * @param row the row for which to check in board
     * @param column the column for which to check in board.
     * */
    public void becomeMissOrHit(int row, int column){
        Spaces space = Board[row][column];
        if(!space.ishit()) {
            if (space.partOfBoat()) {
                space.setSymbol("X");
            }
            else {
                space.setSymbol("O");
            }
        }
        space.setHit(true);
    }

    /** Same method becomeMissOrHit except its for the hidden board, represents shooting at computer board
     *
     * @param row the row for which to check in computer's board
     * @param column the column for which to check in computer's board.
     * */
    public void missOrHit(int row, int column) {
        Spaces space = hiddenBoard[row][column];
        if (!space.ishit()) {
            if (space.partOfBoat()) {
                space.setSymbol("X");
            }
            else {
                space.setSymbol("O");
            }
        }
    }

    /** returns boats, arraylist of Ships for player
     *
     * @return ArrayList of ships, boats variable representing player's boats.
     * */
    public ArrayList<Ships> getBoats(){
        return boats;
    }

    /** returns boats, arraylist of Ships for computer
     *
     * @return ArrayList of ships, boats variable representing computer's boats.
     * */
    public ArrayList<Ships> getHiddenBoats(){
        return hiddenBoats;
    }

    /** returns player board, a 2D array
     *
     * @return the player board.
     * */
    public Spaces[][] getBoard(){
        return Board;
    }

    /** returns computer board, a 2D array
     *
     * @return the computer board.
     * */
    public Spaces[][] getHiddenBoard(){
        return hiddenBoard;
    }

}
