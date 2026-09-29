package BatailleV2;

public enum Orientation {
    HORIZONTALE(0,1),
    VERTICALE(1,0);

    private final int deltaLigne;
    private final int deltaColonne;

    Orientation(int deltaLigne,int deltaColonne){
        this.deltaLigne = deltaLigne;
        this.deltaColonne = deltaColonne;
    }
    public int getDeltaLigne(){
        return deltaLigne;
    }
    public int getDeltaColonne(){
        return deltaColonne;
    }
}
