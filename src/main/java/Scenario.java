package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.Inhabitant.Inhabitant;
import main.java.Inhabitant.InhabitantFactory;
import main.java.Map.StarAtlas;
import main.java.Map.StarMap;

public class Scenario {
    public int sufaceColSize;
    public int sufaceRowSize;
    private InhabitantFactory inhabitantFactory = new InhabitantFactory();
    public List<Inhabitant> inhibitantList = new ArrayList<>();
    private List<Object[]> inhabitantArgsList = new ArrayList<Object[]>();
    private List<Object[]> starMapArgsList = new ArrayList<Object[]>();
    private List<Object[]> starAtlasArgsList = new ArrayList<Object[]>();

    public void resetOldInhibitants() {
        inhabitantArgsList.clear();
        starMapArgsList.clear();
        starAtlasArgsList.clear();
        inhibitantList.clear();
    }

    public void addInhabitantArgs(String type, int row, int col, int tID, int walkDirectionIndex) {
        inhabitantArgsList.add(new Object[] { type, row, col, tID, walkDirectionIndex });
    }

    public void addStarMapArgs(int row, int col, int mID, String text) {
        starMapArgsList.add(new Object[] { row, col, mID, text });
    }

    public void addStarAtlasArgs(int row, int col, int mID, int[] mIDs, String[] texts) {
        starAtlasArgsList.add(new Object[] { row, col, mID, mIDs, texts });
    }

    public void createInstances() {
        for (int i = 0; i < inhabitantArgsList.size(); i++) {
            Object[] inhabitantArgs = inhabitantArgsList.get(i);
            Inhabitant inhabitant = inhabitantFactory.createInhabitant((String) inhabitantArgs[0],
                    (int) inhabitantArgs[1], (int) inhabitantArgs[2], (int) inhabitantArgs[3]);
            inhabitant.setWalkDirections((int) inhabitantArgs[4]);
            inhabitant.createBase();
            inhibitantList.add(inhabitant);

        }
        for (int i = 0; i < starMapArgsList.size(); i++) {
            Object[] starMapArgs = starMapArgsList.get(i);
            new StarMap((int) starMapArgs[0], (int) starMapArgs[1], (int) starMapArgs[2], (String) starMapArgs[3],
                    false);
        }
        for (int i = 0; i < starAtlasArgsList.size(); i++) {
            Object[] starAtlasArgs = starAtlasArgsList.get(i);
            new StarAtlas((int) starAtlasArgs[0], (int) starAtlasArgs[1], (int) starAtlasArgs[2],
                    (int[]) starAtlasArgs[3], (String[]) starAtlasArgs[4]);
        }
    }

}
