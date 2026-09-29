package BatailleV2;

public class BatailleNavale {

    public static void main(String[] args){
        VueJeu vue = new VueConsole();
        Joueur humain = new Jhumain("Joueur",vue);
        Joueur ia = new Jia();
        Partie partie = new Partie(humain,ia,vue);
        partie.lancer();
    }
}
