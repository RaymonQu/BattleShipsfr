import java.util.ArrayList;

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
                Board[i][k] = new Spaces(i, k);
                hiddenBoard[i][k] = new Spaces(i, k);
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
                        hiddenBoard[row][counter].setSymbol(ship.getSignifier());
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
            System.out.println(column + "," + ship.getSize() + ", " + row);
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
                        hiddenBoard[row][counter].setSymbol(ship.getSignifier());
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
                        hiddenBoard[counter][column].setSymbol(ship.getSignifier());
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
                        hiddenBoard[counter][column].setSymbol(ship.getSignifier());
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

    public void randomlyPutShipsOnHiddenBoard(){
        int counter = 0;
        while(counter < 5) {
                if(counter == 0){
                    int row = (int) (Math.random() * 9);
                    int column = (int) (Math.random() * 9);
                    Ships newboat = new Ships(2, "D");
                    if(putShipOnBoard(newboat, row, column, randomDir(), true)){
                        counter++;
                        hiddenBoats.add(newboat);
                    }
                }
                if(counter == 1){
                    int row = (int) (Math.random() * 9);
                    int column = (int) (Math.random() * 9);
                    Ships newboat = new Ships(3, "S");
                    if(putShipOnBoard(newboat, row, column, randomDir(), true)){
                        counter++;
                        hiddenBoats.add(newboat);
                    }
                }
                if(counter == 2){
                    int row = (int) (Math.random() * 9);
                    int column = (int) (Math.random() * 9);
                    Ships newboat = new Ships(3, "C");
                    if(putShipOnBoard(newboat, row, column, randomDir(), true)){
                        counter++;
                        hiddenBoats.add(newboat);
                    }
                }
                if(counter == 3){
                    int row = (int) (Math.random() * 9);
                    int column = (int) (Math.random() * 9);
                    Ships newboat = new Ships(4, "B");
                    if(putShipOnBoard(newboat, row, column, randomDir(), true)){
                        counter++;
                        hiddenBoats.add(newboat);
                    }
                }
                if(counter == 4){
                    int row = (int) (Math.random() * 9);
                    int column = (int) (Math.random() * 9);
                    Ships newboat = new Ships(5, "A");
                    if(putShipOnBoard(newboat, row, column, randomDir(), true)){
                        counter++;
                        hiddenBoats.add(newboat);
                    }
                }
        }
        for(Ships ship: hiddenBoats){
            System.out.println(ship.getSignifier());
            for(int i = 0; i < ship.getSpaces().size(); i++){
                System.out.println(ship.getSpaces().get(i).getCorrespondingCoords());
            }
        }
    }

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

    public static boolean doesNotOverlap(int row, int column, int size, String direction, Spaces[][] notRelatedToOtherBoard){
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

    public boolean becomeMissOrHit(int row, int column){
        Spaces space = hiddenBoard[row][column];
        if(!space.ishit()) {
            if (space.partOfBoat()) {
                Board[row][column].setSymbol("X");
            }
            else {
                Board[row][column].setSymbol("O");
            }
            return true;
        }
        return false;
    }

    public boolean missOrHit(int row, int column) {
        Spaces space = hiddenBoard[row][column];
        if (!space.ishit()) {
            if (space.partOfBoat()) {
                space.setSymbol("X");
            }
            else {
                space.setSymbol("O");
            }
            return true;
        }
        return false;
    }

}
