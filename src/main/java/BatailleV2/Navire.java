package BatailleV2;

public abstract class Navire {

    private final String nom;
    private final int taille;
    private int pointsDeVie;

    protected  Navire (String nom,int taille){
        this.nom = nom;
        this.taille = taille;
        this.pointsDeVie = taille;
    }
    public String getNom() { return nom; }
    public int getTaille() { return taille; }

    public boolean estCoulee(){
        return pointsDeVie == 0;
    }
    public void toucher(){
        if(!estCoulee()){
            pointsDeVie -= 1;
        }
    }

    @Override
    public String toString() {
    return nom + " (" + pointsDeVie + "/" + taille + ")";
    }
}
