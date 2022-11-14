package main.java.Base;

import main.java.Locatable;
import main.java.TFace;
import main.java.Map.Map;

public class HeroBase extends Base {

    private ArrayList<Map> cloneMaps = null;
    public HeroBase(int row, int col, int heroBID){
        super(row, col, heroBID);
        cloneMaps = new ArrayList<Map>();
    }

    public boolean checkID (TetHero hero) {
        if (hero.getTID() == bID) {
            return true;
        } else {
            return false;
        }
    }

    public void cloneMap (Map clone) {
        if (clone != null) {
            cloneMaps.add(clone);
        }
    }
}