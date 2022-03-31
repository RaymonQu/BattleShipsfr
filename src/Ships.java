import java.util.ArrayList;

public class Ships {
    private int size;
    private boolean isDead;
    private boolean isHit;
    private String signifier;
    private ArrayList<String> coords;
    // A = aircraft car B = battle C= cruiser d = destroyer s= submarine

    public Ships(int size, String signifier){
        this.size = size;
        isDead = false;
        isHit = false;
        this.signifier = signifier;
        coords = new ArrayList<String>();
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

    public void setCoords(ArrayList<String> locs){
        coords.addAll(locs);
    }

    public ArrayList<String> getCoords(){
        return coords;
    }

}
