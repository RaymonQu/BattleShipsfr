import java.util.ArrayList;
import java.util.Locale;

public class Board {
    private Spaces[][] Board;
    private Spaces[][] hiddenBoard;
    private ArrayList<Ships> boats;
    private ArrayList<Ships> hiddenBoats;

    public Board(){
        Board = new Spaces[10][10];
        hiddenBoard = new Spaces[10][10];
        boats = new ArrayList<Ships>();
        hiddenBoats = new ArrayList<Ships>();

    }

    public void createBoard(){
        for(int i = 0; i < 10; i++){
            for (int k = 0; k < 10; k++){
                Board[i][k] = new Spaces();
                hiddenBoard[i][k] = new Spaces();
            }
        }
    }

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

    public void putShipsOnBoard(Ships ship, int row, int column, String direction){
        ArrayList<String> locations = new ArrayList<String>();
        int counter;
        if (direction.toUpperCase().equals("E")) {
            counter = column;
            while (column - ship.getSize() > 0) {
                for(int i = 0; i < ship.getSize(); i++){
                    Board[row][counter].setSymbol(ship.getSignifier());
                    locations.add(row + ", " + counter);
                    counter--;
                }
            }
        }
        else if(direction.toUpperCase().equals("W")){
            counter = column;
            while (column + ship.getSize() < 10) {
                for(int i = 0; i < ship.getSize(); i++){
                    Board[row][counter].setSymbol(ship.getSignifier());
                    locations.add(row + ", " + counter);
                    counter++;
                }
            }
        }
        else if(direction.toUpperCase().equals("N")){
            counter = row;
            while (row + ship.getSize() < 10) {
                for(int i = 0; i < ship.getSize(); i++){
                    Board[counter][column].setSymbol(ship.getSignifier());
                    locations.add(counter + ", " + column);
                    counter++;
                }
            }
        }
        else if(direction.toUpperCase().equals("S")){
            counter = row;
            while (row - ship.getSize() < 10) {
                for(int i = 0; i < ship.getSize(); i++){
                    Board[counter][column].setSymbol(ship.getSignifier());
                    locations.add(counter + ", " + column);
                    counter--;
                }
            }
        }
        ship.setCoords(locations);
    }
//NEED TO IMPLEMENT RANDOM PLACEMENTS, ANOTHER CONDITIONAL IN EACH WHILE LOOP, the new METHOD I JSUT MADE THEN
// USING RANDOM NUMS TO FIND STUFF. THEN SHOULD BE SMOOTH SAILING FROM THERE
    public void randomlyPutShipsOnHiddenBoard(Ships ship){
        ArrayList<String> locations = new ArrayList<String>();
        int counter = 0;
        while(hiddenBoats.size() <= 5) {
            int direction = (int) (Math.random() * 4);
            int row = Math.random()
            if (direction == 1) {
                counter = column;
                while (column - ship.getSize() > 0) { /* East*/
                    for (int i = 0; i < ship.getSize(); i++) {
                        locations.add(row + ", " + counter);
                        counter--;
                    }
                }
            } else if (direction == 2) {
                counter = column;
                while (column + ship.getSize() < 10) { /* west */
                    for (int i = 0; i < ship.getSize(); i++) {
                        locations.add(row + ", " + counter);
                        counter++;
                    }
                }
            } else if (direction == 3) {
                counter = row;
                while (row + ship.getSize() < 10) { /* North*/
                    for (int i = 0; i < ship.getSize(); i++) {
                        locations.add(counter + ", " + column);
                        counter++;
                    }
                }
            } else if (direction == 4) {
                counter = row;
                while (row - ship.getSize() < 10) { /* South*/
                    for (int i = 0; i < ship.getSize(); i++) {
                        locations.add(counter + ", " + column);
                        counter--;
                    }
                }
            }
            counter++;
            ship.setCoords(locations);
        }
    }

    public boolean willOverlap(int row, int column, int size, String direction){
        for(int i = 0; i < size; i++){
            if(direction.equals("W")){
                if(hiddenBoard[row][column].partOfBoat()){
                    return true;
                }
                column--;
            }
            if(direction.equals("E")){
                if(hiddenBoard[row][column].partOfBoat()){
                    return true;
                }
                column++;
            }
            if(direction.equals("S")){
                if(hiddenBoard[row][column].partOfBoat()){
                    return true;
                }
                row++;
            }
            if(direction.equals("N")){
                if(hiddenBoard[row][column].partOfBoat()){
                    return true;
                }
                row--;
            }
        }
        return false;
    }

    public void becomeMissOrHit(int row, int column){
        if(Board[row][column].getSymbol().equals("B")){
            Board[row][column].setSymbol("X");
        }
        else{
            Board[row][column].setSymbol("O");
        }
    }

    public void setMissOrHit(int row, int column){
        if(hiddenBoard[row][column].getSymbol().equals("B")){
            hiddenBoard[row][column].setSymbol("X");
        }
        else{
            hiddenBoard[row][column].setSymbol("O");
        }
    }

}
