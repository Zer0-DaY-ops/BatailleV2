package BatailleV2;

public class TestGrille {

    public static void main(String[] args){
        Grille grille = new Grille();
        VueConsole vue = new VueConsole();
        Navire titan = new Titan();
        Navire dart = new Dart();
        System.out.println("titan en A1 horizontal : " + grille.placer(titan,new Position(0,0),Orientation.HORIZONTALE));
        System.out.println("dart en C1 (déjà pris) : " + grille.placer(dart,new Position(0,2),Orientation.HORIZONTALE));
        System.out.println("titan qui dépasse : " + grille.peutPlacer(new Titan(),new Position(0,8),Orientation.HORIZONTALE));
        System.out.println("dart en J10 : " + grille.placer(dart,new Position(9,9),Orientation.VERTICALE));
        vue.afficherGrille(grille,true);
        System.out.println("tir en E5 : " + grille.recevoirTir(new Position(4,4)));
        System.out.println("tir en A1 : " + grille.recevoirTir(new Position(0,0)));
        System.out.println("tir en A1 : " + grille.recevoirTir(new Position(0,0)));
        System.out.println("tir en J10 : " + grille.recevoirTir(new Position(9,9)));
        System.out.println("tout coule ? " + grille.tousCoules());
        vue.afficherGrille(grille,true);
    }
}
