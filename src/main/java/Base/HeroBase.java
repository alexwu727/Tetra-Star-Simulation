package main.java.Base;

import main.java.Inhabitant.*;
import main.java.TFace;

import main.java.Map.Map;

import java.util.ArrayList;

public class HeroBase extends Base {

    private ArrayList<Map> cloneMaps = null;
    public HeroBase(int row, int col, String bID){
        super(row, col, bID);
        cloneMaps = new ArrayList<Map>();
    }

//    public boolean checkID (TetHero hero) {
//        if (hero.getTID() == bID) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void cloneMap (Map clone) {
        if (clone != null) {
            cloneMaps.add(clone);
        }
    }
}