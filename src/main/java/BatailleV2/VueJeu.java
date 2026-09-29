package BatailleV2;

public interface VueJeu {

    void afficherMessage(String message);
    void afficherGrille(Grille grille,boolean montrerNavires);
    void afficherPlateau(Joueur moi,Joueur adversaire);
    void afficherResultatTir(Joueur tireur,Position tir,ResultatTir resultat);
    void afficherVainqueur(Joueur gagnant);
}
