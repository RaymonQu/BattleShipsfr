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
        if (direction.toUpperCase().equals("W")) {
            counter = column;
            if (column - ship.getSize() > 0 && doesNotOverlap(row, column, ship.getSize(), "W")) {
                for(int i = 0; i < ship.getSize(); i++){
                    if(!hidden){
                        Board[row][counter].setSymbol(ship.getSignifier());
                    }
                    ship.addSpaces(new Spaces(row, counter, true));
                    counter--;
                    System.out.println("HI");
                }
            }
            else{
                System.out.println("not work");
                return false;
            }
        }
        else if(direction.toUpperCase().equals("E")){
            counter = column;
            System.out.println(column + "," + ship.getSize() + ", " + row);
            if (column + ship.getSize() < 10  && doesNotOverlap(row, column, ship.getSize(), "E")) {
                for(int i = 0; i < ship.getSize(); i++){
                    if(!hidden){
                        Board[row][counter].setSymbol(ship.getSignifier());
                    }
                    ship.addSpaces(new Spaces(row, counter, true));
                    counter++;
                    System.out.println("HI?");

                }
            }
            else{
                System.out.println("not work");
                return false;
            }
        }
        else if(direction.toUpperCase().equals("S")){
            counter = row;
            if (row + ship.getSize() < 10  && doesNotOverlap(row, column, ship.getSize(), "S")) {
                for(int i = 0; i < ship.getSize(); i++){
                    if(!hidden){
                        Board[counter][column].setSymbol(ship.getSignifier());
                    }
                    ship.addSpaces(new Spaces(counter, column, true));
                    counter++;
                    System.out.println("HI??");

                }
            }
            else{
                System.out.println("not work");
                return false;
            }
        }
        else if(direction.toUpperCase().equals("N")){
            counter = row;
            if (row - ship.getSize() > 0 && doesNotOverlap(row, column, ship.getSize(), "N")) {
                for(int i = 0; i < ship.getSize(); i++){
                    if(!hidden){
                        Board[counter][column].setSymbol(ship.getSignifier());
                    }
                    ship.addSpaces(new Spaces(counter, column, true));
                    counter--;
                    System.out.println("HI???");
                }
            }
            else{
                System.out.println("not work");
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
                        System.out.println("YES");
                    }
                }
                if(counter == 1){
                    int row = (int) (Math.random() * 9);
                    int column = (int) (Math.random() * 9);
                    Ships newboat = new Ships(3, "S");
                    if(putShipOnBoard(newboat, row, column, randomDir(), true)){
                        counter++;
                        hiddenBoats.add(newboat);
                        System.out.println("YES!");
                    }
                }
                if(counter == 2){
                    int row = (int) (Math.random() * 9);
                    int column = (int) (Math.random() * 9);
                    Ships newboat = new Ships(3, "C");
                    if(putShipOnBoard(newboat, row, column, randomDir(), true)){
                        counter++;
                        hiddenBoats.add(newboat);
                        System.out.println("YES!!");
                    }
                }
                if(counter == 3){
                    int row = (int) (Math.random() * 9);
                    int column = (int) (Math.random() * 9);
                    Ships newboat = new Ships(4, "B");
                    if(putShipOnBoard(newboat, row, column, randomDir(), true)){
                        counter++;
                        hiddenBoats.add(newboat);
                        System.out.println("YES!!!");
                    }
                }
                if(counter == 4){
                    int row = (int) (Math.random() * 9);
                    int column = (int) (Math.random() * 9);
                    Ships newboat = new Ships(5, "A");
                    if(putShipOnBoard(newboat, row, column, randomDir(), true)){
                        counter++;
                        hiddenBoats.add(newboat);
                        System.out.println("YES!!!!");
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

    private boolean doesNotOverlap(int row, int column, int size, String direction){
        for(int i = 0; i < size; i++){
            if(direction.equals("W")){
                if(hiddenBoard[row][column].partOfBoat()){
                    return false;
                }
                column--;
            }
            if(direction.equals("E")){
                if(hiddenBoard[row][column].partOfBoat()){
                    return false;
                }
                column++;
            }
            if(direction.equals("S")){
                if(hiddenBoard[row][column].partOfBoat()){
                    return false;
                }
                row++;
            }
            if(direction.equals("N")){
                if(hiddenBoard[row][column].partOfBoat()){
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
