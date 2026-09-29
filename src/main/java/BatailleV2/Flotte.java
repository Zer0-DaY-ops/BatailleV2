package BatailleV2;

import java.util.ArrayList;
import java.util.List;

public class Flotte {
    public static List<Navire> creer(){
        List<Navire> flotte = new ArrayList<>();
        flotte.add(new Titan());
        flotte.add(new Warden());
        flotte.add(new Interceptor());
        flotte.add(new Phantom());
        flotte.add(new Dart());
        return flotte;

    }
}
