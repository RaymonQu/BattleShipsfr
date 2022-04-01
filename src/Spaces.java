import java.util.ArrayList;

public class Spaces {
    private boolean partOfBoat;
    private boolean hit;
    private String symbol;
    private int row;
    private int column;
    private String correspondingCoords;

    public Spaces(int row, int column, boolean partOfBoat){
        hit = false;
        this.partOfBoat = partOfBoat;
        symbol = "-";
        this.row = row;
        this.column = column;
        String[] arr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        int[] arrOfNums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        correspondingCoords = arr[row] + arrOfNums[column];
        //input subtract by one
    }

    public Spaces(int row, int column){
        hit = false;
        partOfBoat = false;
        symbol = "-";
        this.row = row;
        this.column = column;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol(String newSym){
        symbol = newSym;
    }

    public boolean partOfBoat(){
        return partOfBoat;
    }

    public boolean ishit(){
        return hit;
    }

    public void setHit(boolean hit){
        this.hit = hit;
    }

    public String getCorrespondingCoords() {
        return correspondingCoords;
    }

}
