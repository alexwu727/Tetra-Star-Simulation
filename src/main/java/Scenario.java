package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.Inhabitant.TetHero;
import main.java.Inhabitant.TetRover;
import main.java.Inhabitant.TetVader;
import main.java.Map.StarAltas;
import main.java.Map.StarMap;

public class Scenario {
    public int sufaceColSize;
    public int sufaceRowSize;
    public List<TetRover> inhibitantList = new ArrayList<>();
    private List<int[]> tetRoverArgsList = new ArrayList<int[]>();
    private List<int[]> tetHeroArgsList = new ArrayList<int[]>();
    private List<int[]> tetVaderArgsList = new ArrayList<int[]>();
    private List<Object[]> starMapArgsList = new ArrayList<Object[]>();
    private List<Object[]> starAltasArgsList = new ArrayList<Object[]>();

    public void addTetRoverArgs(int row, int col, int tID, int walkDirectionIndex) {
        tetRoverArgsList.add(new int[] { row, col, tID, walkDirectionIndex });
    }

    public void addTetHeroArgs(int row, int col, int tID, int walkDirectionIndex) {
        tetHeroArgsList.add(new int[] { row, col, tID, walkDirectionIndex });
    }

    public void addTetVaderArgs(int row, int col, int tID, int walkDirectionIndex) {
        tetVaderArgsList.add(new int[] { row, col, tID, walkDirectionIndex });
    }

    public void addStarMapArgs(int row, int col, int mID, String text) {
        starMapArgsList.add(new Object[] { row, col, mID, text });
    }

    public void addStarAltasArgs(int row, int col, int mID, int[] mIDs, String[] texts) {
        starAltasArgsList.add(new Object[] { row, col, mID, mIDs, texts });
    }

    public void createInstances() {
        for (int i = 0; i < tetRoverArgsList.size(); i++) {
            int[] tetRoverArgs = tetRoverArgsList.get(i);
            TetRover tetRover = new TetRover(tetRoverArgs[0], tetRoverArgs[1], tetRoverArgs[2]);
            tetRover.setDisplayID("R");
            tetRover.setWalkDirections(tetRoverArgs[3]);
            inhibitantList.add(tetRover);
        }
        for (int i = 0; i < tetHeroArgsList.size(); i++) {
            int[] tetHeroArgs = tetHeroArgsList.get(i);
            TetHero tetHero = new TetHero(tetHeroArgs[0], tetHeroArgs[1], tetHeroArgs[2]);
            tetHero.setDisplayID("H");
            tetHero.setWalkDirections(tetHeroArgs[3]);
            inhibitantList.add(tetHero);
        }
        for (int i = 0; i < tetVaderArgsList.size(); i++) {
            int[] tetVaderArgs = tetVaderArgsList.get(i);
            TetVader tetVader = new TetVader(tetVaderArgs[0], tetVaderArgs[1], tetVaderArgs[2]);
            tetVader.setDisplayID("V");
            tetVader.setWalkDirections(tetVaderArgs[3]);
            inhibitantList.add(tetVader);
        }
        for (int i = 0; i < starMapArgsList.size(); i++) {
            Object[] starMapArgs = starMapArgsList.get(i);
            new StarMap((int) starMapArgs[0], (int) starMapArgs[1], (int) starMapArgs[2], (String) starMapArgs[3]);
        }
        for (int i = 0; i < starAltasArgsList.size(); i++) {
            Object[] starAltasArgs = starAltasArgsList.get(i);
            new StarAltas((int) starAltasArgs[0], (int) starAltasArgs[1], (int) starAltasArgs[2],
                    (int[]) starAltasArgs[3], (String[]) starAltasArgs[4]);
        }
    }

}
