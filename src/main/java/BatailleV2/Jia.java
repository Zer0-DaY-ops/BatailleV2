package BatailleV2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Jia extends Joueur{

    private final Random random = new Random();
    private final Set<Position> dejaTires = new HashSet<>();

    public Jia(){
        super("IA");
    }

    @Override
    public Position choisirTir(){
        Position p = new Position(random.nextInt(Grille.TAILLE),random.nextInt(Grille.TAILLE));
        while(dejaTires.contains(p)){
            p = new Position(random.nextInt(Grille.TAILLE),random.nextInt(Grille.TAILLE));
        }
        dejaTires.add(p);
        return p;
    }

    @Override
    public void placerNavires(){
        placerAleatoirement();
    }
}
