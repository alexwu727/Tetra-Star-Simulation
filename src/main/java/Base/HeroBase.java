package main.java.Base;

import main.java.Locatable;
import main.java.TFace;
import main.java.Map.Map;

public class HeroBase extends Base {

    private ArrayList<Map> cloneList = null;
    public HeroBase(int row, int col, int heroBID){
        super(row, col, heroBID);
        tFace.addObject(this);
        cloneList = new ArrayList<Map>();
    }

    public boolean checkID (TetHero hero) {
        if (hero.getTID() == bID) {
            return true;
        } else {
            return false;
        }
    }

    public void clone (Map cloneMap) {
        cloneList.add(cloneMap)

    }
}