package BatailleV2;

import java.util.Scanner;

public class Jhumain extends Joueur{

    private final Scanner scanner = new Scanner(System.in);
    private final VueJeu vue;

    public Jhumain(String nom,VueJeu vue){
        super(nom);
        this.vue = vue;
    }
    private Position lireCase(String question){
        Position position = null;
        while(position == null){
            vue.afficherMessage(question);
            String saisie = scanner.nextLine().trim().toUpperCase();
            if(saisie.length() >= 2 && Character.isLetter(saisie.charAt(0)) && saisie.substring(1).matches("[0-9]+")){
                int colonne = saisie.charAt(0) - 'A';
                int ligne = Integer.parseInt(saisie.substring(1)) - 1;
                position = new Position(ligne,colonne);
            }else{
                vue.afficherMessage("zaisie invalide, tapez une lettre puis un nombre (ex : C7).");
            }
        }
        return position;
    }
    private Orientation lireOrientation(){
        Orientation orientation = null;
        while(orientation == null){
            vue.afficherMessage("orientation (H = horizontale, V = verticale) : ");
            String saisie = scanner.nextLine().trim().toUpperCase();
            if(saisie.equals("H")){
                orientation = Orientation.HORIZONTALE;
            }else if(saisie.equals("V")){
                orientation = Orientation.VERTICALE;
            }else{
                vue.afficherMessage("Répondez H ou V.");
            }
        }
        return orientation;
    }

    @Override
    public Position choisirTir(){
        return lireCase("Où voulez-vous tirer ? (ex : B5) : ");
    }

    @Override
    public void placerNavires(){
        vue.afficherMessage("Placement automatique de vos navires ? (o/n) : ");
        String reponse = scanner.nextLine().trim().toLowerCase();
        if(reponse.equals("o")){
            placerAleatoirement();
        }else{
            for(Navire navire : Flotte.creer()){
                boolean place = false;
                while(!place){
                    vue.afficherGrille(getGrille(),true);
                    vue.afficherMessage("Placement du " + navire.getNom() + " (" + navire.getTaille() + " cases)");
                    Position depart = lireCase("Case de départ (ex : A1) : ");
                    Orientation orientation = lireOrientation();
                    place = getGrille().placer(navire,depart,orientation);
                    if(!place){
                        vue.afficherMessage("impossible : le navire sort de la grille ou en touche un autre recommencez.");
                    }
                }
            }
            vue.afficherGrille(getGrille(),true);
        }
    }
}
