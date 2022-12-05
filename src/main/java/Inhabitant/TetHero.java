package main.java.Inhabitant;

import main.java.Map.StarAtlas;
import main.java.Map.StarMap;
import main.java.Map.MapClass;

import java.util.List;

import main.java.BackendConsole;
import main.java.Locatable;
import main.java.Base.HeroBase;
import main.java.Base.MapBase;
import main.java.Base.River;
import main.java.Base.VaderBase;

public class TetHero extends Inhabitant {
    boolean tFlier;
    private int cipherKey = 9;
    private int findMapID;
    HeroBase heroBase;

    public TetHero(int row, int col, int tID) {
        super(row, col, tID);
    }

    public void createBase() {
        heroBase = new HeroBase(row, col, tFace.convertToKey(row, col));
        tFace.addBase(heroBase);
    }

    public boolean positionCheck(int row, int col) {
        Locatable object = tFace.surface[row][col];
        return !(object instanceof Inhabitant || object instanceof River);
    }

    public int nextActionEnterMapBase(MapBase mapBase) {
        if (mapBase.hasMap()) {
            return 1;
        }
        findMapID = mapBase.getMapID();
        return 2;
    }

    public void action() {
        switch (nextAction) {
            case 0:
                walk();
                break;
            case 1:
                actionToMapInMapBase(((MapBase) tFace.getBase(getRow(), getCol())).getMap());
                break;
            case 2:
                requestTFlier();
                break;
            case 3:
                flyTo(tFace.tetVaderBaseRow, tFace.tetVaderBaseCol);
                actionToConsole("Flies to the vader base");
                check(findMapID);
                break;
            case 4:
                actionToMapInVaderBase();
                flyTo(heroBase.getRow(), heroBase.getCol());
                actionToConsole("Flies back to his base.");
                break;
        }
    }

    public void actionToMapInMapBase(MapClass map) {
        String temp1;
        String temp2;
        if (map.isEncrypted()) {
            if (map.getEncryptHeroID() != tID) {
                map.addHero(this);
                temp1 = "already encrypted by other hero, adds id to its header and";
                temp2 = "encrypted content.";
            } else {
                temp1 = "already encrypted by me,";
                temp2 = "decrpyted content.";
            }
        } else {
            temp1 = "not encrypted,";
            temp2 = "unencrypted content.";
        }
        actionToConsole(map.getName() + " in the map base is " + temp1 + " displays the " + temp2);
        display(map);
        this.nextAction = 0;
    }

    public void display(MapClass map) {
        if (map instanceof StarAtlas) {
            StarAtlas atlas = (StarAtlas) map;
            List<StarMap> starMaps = atlas.getStarMaps();
            for (StarMap starMap : starMaps) {
                display(starMap);
            }
        } else {
            String message;
            printSymbol(map.getEncryptSymbol());
            String Date = "Dec 2022";
            BackendConsole.addConsole("ID: " + map.getEncryptHeroID() + " Date: " + Date + " (Tetra Time)");
            message = map.getText();
            if (map.getEncryptHeroID() == tID) {
                message = caesarCipher(message, -cipherKey);
            }
            BackendConsole.addConsole("Text: " + message);
            printSymbol(map.getEncryptSymbol());
        }
    }

    public void requestTFlier() {
        actionToConsole("Requests a tFlier");
        this.tFlier = true;
        this.nextAction = 3;
    }

    private void flyTo(int row, int col) {
        tFace.removeObject(this);
        setRow(row);
        setCol(col);
        tFace.addObject(this);
    }

    public void check(int findMapID) {
        if (tFace.getVaderBase().getStolenMaps().containsKey(findMapID)) {
            MapClass map = tFace.getVaderBase().getMap(findMapID);
            actionToConsole("Found " + map.getName() + " in the vader base.");
            this.nextAction = 4;
        } else {
            this.nextAction = 0;
            actionToConsole("Where the hell map " + findMapID + " go?");
        }
    }

    public void actionToMapInVaderBase() {
        this.tFlier = false;
        VaderBase vaderBase = tFace.getVaderBase();
        MapClass map = vaderBase.removeMap(findMapID);
        cloneMap(map);
        restore(map);
        actionToConsole("Clones and restores " + map.getName() + " in the vader base.");
        if (!map.isEncrypted()) {
            encrypt(map);
            actionToConsole("Encrypts " + map.getName() + " in the vader base.");
        } else if (map.getEncryptHeroID() != tID) {
            map.addHero(this);
            actionToConsole(
                    map.getName() + " is already encrypted by other hero, adds id to the header.");
        } else {
            incrementRestorationCounter(map);
            actionToConsole(
                    map.getName() + " is already encrypted, increments the restoration_counter.");
        }
        this.nextAction = 0;
    }

    private String caesarCipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                if (newAlphabetPosition < 0) {
                    newAlphabetPosition += 26;
                }
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public void encrypt(MapClass map) {
        if (map instanceof StarAtlas) {
            StarAtlas atlas = (StarAtlas) map;
            List<StarMap> starMaps = atlas.getStarMaps();
            for (StarMap starMap : starMaps) {
                encrypt(starMap);
            }
        } else {
            String message = map.getText();
            map.setText(caesarCipher(message, cipherKey));
            map.encrpyt();
            map.setEncryptHeroID(tID);
        }

    }

    public void decrypt(MapClass map) {
        if (map instanceof StarAtlas) {
            StarAtlas atlas = (StarAtlas) map;
            List<StarMap> starMaps = atlas.getStarMaps();
            for (StarMap starMap : starMaps) {
                decrypt(starMap);
            }
        } else {
            String message = map.getText();
            map.setText(caesarCipher(message, -cipherKey));
            map.decrpyt();
            map.setEncryptHeroID(0);
        }
    }

    public void restore(MapClass map) {
        MapBase mapBase = map.getMapBase();
        map.updateMapLocation(mapBase.getRow(), mapBase.getCol());
        mapBase.setMap(map);
    }

    public void cloneMap(MapClass map) {
        MapClass clonedMap = map.clone();
        heroBase.cloneMap(clonedMap);
        tFace.addMap(clonedMap.getRow(), clonedMap.getCol(), clonedMap);
    }

    public void incrementRestorationCounter(MapClass map) {
        map.setRestorationCounter(map.getRestorationCounter() + 1);
    }

    public void setHeroBase(HeroBase heroBase) {
        this.heroBase = heroBase;
    }

    private void printSymbol(String symbol) {
        String border = "";
        for (int i = 0; i < 50; i++) {
            border += symbol;
        }
        BackendConsole.addConsole(border);
    }
}
