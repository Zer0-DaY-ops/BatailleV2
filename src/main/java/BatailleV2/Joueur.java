package BatailleV2;

import java.util.Random;

public abstract class Joueur {

    private final String nom;
    private final Grille grille;

    protected Joueur(String nom){
        this.nom = nom;
        this.grille = new Grille();
    }
    public String getNom() { return nom; }
    public Grille getGrille() { return grille; }

    public boolean aPerdu(){
        return grille.tousCoules();
    }
    public abstract Position choisirTir();
    public abstract void placerNavires();
    protected void placerAleatoirement(){
        Random random = new Random();
        for(Navire navire : Flotte.creer()){
            boolean place = false;
            while(!place){
                Position depart = new Position(random.nextInt(Grille.TAILLE),random.nextInt(Grille.TAILLE));
                Orientation orientation = Orientation.VERTICALE;
                if(random.nextBoolean()){
                    orientation = Orientation.HORIZONTALE;
                }
                place = grille.placer(navire,depart,orientation);
            }
        }
    }
}
