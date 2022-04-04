import java.util.ArrayList;

/** represents the single tile on the board*/
public class Spaces {
    /** represents if the tile is boat or not*/
    private boolean partOfBoat;
    /** represents if the tile's been shot at*/
    private boolean hit;
    /** represents the letter the space is*/
    private String symbol;

    /** initializes variables if its part of boat*/
    public Spaces(int row, int column, boolean partOfBoat){
        hit = false;
        this.partOfBoat = partOfBoat;
        symbol = "-";
    }

    /** overridden constructor for spaces without a boat*/
    public Spaces(int row, int column){
        hit = false;
        partOfBoat = false;
        symbol = "-";
    }

    /** gets the signifier
     *
     * @return the symbol variable
     * */
    public String getSymbol(){
        return symbol;
    }

    /** used to set to X or O or a ships signifier*/
    public void setSymbol(String newSym){
        symbol = newSym;
    }

    /**checks if space is part of boat
     *
     * @return boolean part of baot
     * */
    public boolean partOfBoat(){
        return partOfBoat;
    }

    /** checks if space has been hit
     *
     * @return boolean if it has or not
     * */
    public boolean ishit(){
        return hit;
    }

    /** changes hit boolean to true or false*/
    public void setHit(boolean hit){
        this.hit = hit;
    }


}
