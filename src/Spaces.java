public class Spaces {
    private boolean partOfBoat;
    private boolean hit;
    private String symbol;

    public Spaces(){
        hit = false;
        partOfBoat = false;
        symbol = "-";
    }

    public Spaces(String symbol, boolean partOfBoat){
        this.partOfBoat = partOfBoat;
        this.symbol = symbol;
        hit = false;
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
}
