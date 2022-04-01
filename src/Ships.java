import java.util.ArrayList;

public class Ships {
    private int size;
    private boolean isDead;
    private boolean isHit;
    private String signifier;
    private ArrayList<Spaces> spaces;
    // A = aircraft car B = battle C= cruiser D = destroyer s= submarine

    public Ships(int size, String signifier){
        this.size = size;
        isDead = false;
        isHit = false;
        this.signifier = signifier;
        spaces = new ArrayList<Spaces>();
    }

    public int getSize(){
        return size;
    }

    public void makeHit(){
        isHit = true;
    }

    public boolean isHit(){
        return isHit;
    }

    public void setDead(){
        isDead = true;
    }

    public boolean getStatus(){
        return isDead;
    }

    public void setSignifier(String letter){
        signifier = letter;
    }

    public String getSignifier(){
        return signifier;
    }

    public void addSpaces(Spaces space){
        spaces.add(space);
    }

    public ArrayList<Spaces> getSpaces(){
        return spaces;
    }

}
