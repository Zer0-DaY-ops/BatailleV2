package BatailleV2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grille {

    public static final int TAILLE = 10;

    private final Navire[][] cases = new Navire[TAILLE][TAILLE];
    private final List<Navire> navires = new ArrayList<>();
    private final Set<Position> tirsSubis = new HashSet<>();

    public boolean dansLaGrille(Position p){
        return p.getLigne() >= 0 && p.getLigne() < TAILLE && p.getColonne() >= 0 && p.getColonne() < TAILLE;
    }
    public boolean peutPlacer(Navire navire,Position depart,Orientation orientation){
        for(Position p : casesOccupees(navire,depart,orientation)){
            if(!dansLaGrille(p)){
                return false;
            }
            if(cases[p.getLigne()][p.getColonne()] != null){
                return false;
            }
        }
        return true;
    }
    public boolean placer(Navire navire,Position depart,Orientation orientation){
        if(!peutPlacer(navire,depart,orientation)){
            return false;
        }
        for(Position p : casesOccupees(navire,depart,orientation)){
            cases[p.getLigne()][p.getColonne()] = navire; // met ladresse mémoire (la référence) du bateau//
        }
        navires.add(navire);
        return true;
    }
    public ResultatTir recevoirTir(Position p){
        if(tirsSubis.contains(p)){
            return ResultatTir.DEJA_TOUCHE;
        }
        tirsSubis.add(p);
        Navire navire = cases[p.getLigne()][p.getColonne()];
        if(navire == null){
            return ResultatTir.MANQUE;
        }
        navire.toucher();
        if(navire.estCoulee()){
            return ResultatTir.COULE;
        }
        return ResultatTir.TOUCHE;
    }
    public boolean tousCoules(){
        for(Navire navire : navires){
            if(!navire.estCoulee()){
                return false;
            }
        }
        return true;
    }
    public boolean aEteTiree(Position p){
        return tirsSubis.contains(p);
    }
    public Navire getNavire(Position p){
        return cases[p.getLigne()][p.getColonne()];
    }
    private List<Position> casesOccupees(Navire navire,Position depart,Orientation orientation){
        List<Position> positions = new ArrayList<>();
        int ligne = depart.getLigne();
        int colonne = depart.getColonne();
        for(int i = 0; i < navire.getTaille(); i++){
            positions.add(new Position(ligne,colonne));
            ligne += orientation.getDeltaLigne();
            colonne += orientation.getDeltaColonne();
        }
        return positions;
    }
}
