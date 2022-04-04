import java.util.ArrayList;
/** represents a single boat
 * */
public class Ships {
    /** represents the amount of tiles taken */
    private int size;
    /** the letter used on the board*/
    private String signifier;
    /** every ship has spaces which it represents*/
    private ArrayList<Spaces> spaces;

    /** initializes all the variables*/
    public Ships(int size, String signifier){
        this.size = size;
        this.signifier = signifier;
        spaces = new ArrayList<Spaces>();
    }

    /** gets size
     *
     * @return ship size
     * */
    public int getSize(){
        return size;
    }

    /** gets signifier
     *
     * @return space signifier.
     * */
    public String getSignifier(){
        return signifier;
    }

    /** adds spaces to its arraylist of spaces, called spaces*/
    public void addSpaces(Spaces space){
        spaces.add(space);
    }

    /** gets the arraylist of spaces, spaces
     *
     * @return spaces*/
    public ArrayList<Spaces> getSpaces(){
        return spaces;
    }

}
