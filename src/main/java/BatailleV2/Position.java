package BatailleV2;

import java.util.Objects;

public class Position {

    private final int ligne;
    private final int colonne;

    public Position(int ligne,int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }
    public int getLigne(){
        return ligne;
    }
    public int getColonne(){
        return colonne;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getLigne() == position.getLigne() && getColonne() == position.getColonne();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLigne(), getColonne());
    }

    @Override
    public String toString() {
        return "" + (char) ('A' + colonne) + (ligne + 1);
    }
}
