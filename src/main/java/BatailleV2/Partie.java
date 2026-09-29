package BatailleV2;

public class Partie {

    private final Joueur joueur1;
    private final Joueur joueur2;
    private final VueJeu vue;

    public Partie(Joueur joueur1,Joueur joueur2,VueJeu vue){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.vue = vue;
    }
    public void lancer(){
        vue.afficherMessage("===== BATAILLE NAVALE =====");
        joueur1.placerNavires();
        joueur2.placerNavires();
        Joueur tireur = joueur1;
        Joueur cible = joueur2;
        boolean fini = false;
        while(!fini){
            if(tireur == joueur1){
                vue.afficherPlateau(joueur1,joueur2);
            }
            jouerTour(tireur,cible);
            if(cible.aPerdu()){
                vue.afficherPlateau(joueur1,joueur2);
                vue.afficherVainqueur(tireur);
                fini = true;
            }else{
                Joueur temp = tireur;
                tireur = cible;
                cible = temp;
            }
        }
    }
    private void jouerTour(Joueur tireur,Joueur cible){
        boolean tirValide = false;
        while(!tirValide){
            Position tir = tireur.choisirTir();
            if(!cible.getGrille().dansLaGrille(tir)){
                vue.afficherMessage("Cette case est hors de la grille, rejouez.");
            }else{
                ResultatTir resultat = cible.getGrille().recevoirTir(tir);
                if(resultat == ResultatTir.DEJA_TOUCHE){
                    vue.afficherMessage("Vous avez déjà tiré en " + tir + ", rejouez.");
                }else{
                    vue.afficherResultatTir(tireur,tir,resultat);
                    tirValide = true;
                }
            }
        }
    }
}
