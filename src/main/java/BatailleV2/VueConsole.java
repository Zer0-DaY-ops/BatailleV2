package BatailleV2;

public class VueConsole implements VueJeu{

    private char symbole(Grille grille,Position p,boolean montrerNavires){
        boolean tiree = grille.aEteTiree(p);
        boolean navire = grille.getNavire(p) != null;
        if(tiree && navire){
            return 'X';
        }
        if(tiree){
            return 'o';
        }
        if(navire && montrerNavires){
            return 'N';
        }
        return '~';
    }

    @Override
    public void afficherMessage(String message){
        System.out.println(message);
    }

    @Override
    public void afficherGrille(Grille grille,boolean montrerNavires){
        System.out.print("    ");
        for(int c = 0; c < Grille.TAILLE; c++){
            System.out.print((char) ('A' + c) + " ");
        }
        System.out.println();
        for(int l = 0; l < Grille.TAILLE; l++){
            System.out.printf("%2d  ",l + 1);
            for(int c = 0; c < Grille.TAILLE; c++){
                System.out.print(symbole(grille,new Position(l,c),montrerNavires) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void afficherPlateau(Joueur moi,Joueur adversaire){
        System.out.println();
        System.out.println("=== Grille de " + adversaire.getNom() + " (vos tirs) ===");
        afficherGrille(adversaire.getGrille(),false);
        System.out.println();
        System.out.println("=== Votre grille (" + moi.getNom() + ") ===");
        afficherGrille(moi.getGrille(),true);
        System.out.println();
    }

    @Override
    public void afficherResultatTir(Joueur tireur,Position tir,ResultatTir resultat){
        String texte = "déjà tiré ici.";
        if(resultat == ResultatTir.MANQUE){
            texte = "dans l'eau.";
        }else if(resultat == ResultatTir.TOUCHE){
            texte = "touché !";
        }else if(resultat == ResultatTir.COULE){
            texte = "touché, coulé !";
        }
        System.out.println(tireur.getNom() + " tire en " + tir + " : " + texte);
    }

    @Override
    public void afficherVainqueur(Joueur gagnant){
        System.out.println();
        System.out.println("***** " + gagnant.getNom() + " a gagné ! *****");
    }
}
