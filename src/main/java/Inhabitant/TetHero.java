package main.java.Inhabitant;

import main.java.Map.StarAltas;
import main.java.Map.StarMap;
import main.java.Map.Map;

import java.util.List;

import main.java.BackendConsole;
import main.java.Locatable;
import main.java.Base.HeroBase;
import main.java.Base.MapBase;
import main.java.Base.River;
import main.java.Base.VaderBase;

public class TetHero extends TetRover {
    boolean tFlier;
    int cipherKey = 9;
    int findMapID;
    HeroBase heroBase;

    public TetHero(int row, int col, int tID) {
        super(row, col, tID);
        // only on the edge
        heroBase = new HeroBase(row, col, tFace.convertToKey(row, col));
        tFace.addBase(heroBase);
        this.setDisplayID("TetHero");
    }

    @Override
    public boolean positionCheck(int row, int col) {
        Locatable object = tFace.Surface[row][col];
        return !(object instanceof TetRover || object instanceof River);
    }

    @Override
    public int nextActionEnterMapBase(MapBase mapBase) {
        if (mapBase.hasMap()) {
            return 1;
        }
        findMapID = mapBase.getMapID();
        return 2;
    }

    @Override
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
                flyTo(tFace.TetVaderBaseRow, tFace.TetVaderBaseCol);
                actionToConsole("Flies to tetVaderBase and check if the map is there.");
                check(findMapID);
                break;
            case 4:
                actionToMapInVaderBase();
                flyTo(heroBase.getRow(), heroBase.getCol());
                actionToConsole("Flies back to his base.");
                break;
        }
    }

    public void actionToMapInMapBase(Map map) {
        if (map.isEncrypted()) {
            if (map.getEncryptHeroID() != tID) {
                map.addHero(this);
                actionToConsole(
                        "Map in the map base is already encrypted by other hero, adds id to the header and displays encrypted content.");
            } else {
                actionToConsole(
                        "Map in the map base is already encrypted by me, displays the decrpyted map in the map base.");
            }
        } else {
            actionToConsole("Displays unencrypted map in the map base.");
        }
        display(map);
        this.nextAction = 0;
    }

    public void display(Map map) {
        if (map instanceof StarAltas) {
            StarAltas altas = (StarAltas) map;
            List<StarMap> starMaps = altas.getStarMaps();
            BackendConsole.addConsole("" + starMaps.size());
            for (StarMap starMap : starMaps) {
                display(starMap);
            }
        } else {
            printSymbol(map.getEncryptSymbol());
            String Date = "Dec 2022";
            BackendConsole.addConsole("ID: " + map.getEncryptHeroID() + " Date: " + Date + " (Tetra Time)");
            String message = map.getText();
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
        String key = tFace.convertToKey(getRow(), getCol());
        if (((VaderBase) tFace.baseMap.get(key)).getStolenMaps().containsKey(findMapID)) {
            this.nextAction = 4;
        } else {
            this.nextAction = 0;
            actionToConsole("Where the hell map " + findMapID + " go?");
        }
    }

    public void actionToMapInVaderBase() {
        this.tFlier = false;
        String key = tFace.convertToKey(getRow(), getCol());
        VaderBase vaderBase = (VaderBase) tFace.baseMap.get(key);
        Map map = vaderBase.removeMap(findMapID);
        cloneMap(map);
        restore(map);
        actionToConsole("Clones and restores the map in the vader base.");
        if (!map.isEncrypted()) {
            encrypt(map);
            actionToConsole("Encrypts the map in the vader base.");
        } else if (map.getEncryptHeroID() != tID) {
            map.addHero(this);
            actionToConsole("Map in the vader base is already encrypted by other hero, adds id to the header.");
        } else {
            incrementRestorationCounter(map);
            actionToConsole("Map in the vader base is already encrypted, increments the restoration_counter.");
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

    public void encrypt(Map map) {
        if (map instanceof StarAltas) {
            StarAltas altas = (StarAltas) map;
            List<StarMap> starMaps = altas.getStarMaps();
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

    public void decrypt(Map map) {
        if (map instanceof StarAltas) {
            StarAltas altas = (StarAltas) map;
            List<StarMap> starMaps = altas.getStarMaps();
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

    public void restore(Map map) {
        MapBase mapBase = map.getMapBase();
        map.updateMapLocation(mapBase.getRow(), mapBase.getCol());
        mapBase.setMap(map);
    }

    public void cloneMap(Map map) {
        Map clonedMap = map.clone();
        clonedMap.setDisplayID("CloneMap");
        heroBase.cloneMap(clonedMap);
        tFace.mapMap.put(tFace.convertToKey(clonedMap.getRow(), clonedMap.getCol()), clonedMap.getDisplayID());

    }

    public void incrementRestorationCounter(Map map) {
        map.setRestorationCounter(map.getRestorationCounter() + 1);
    }

    public void setHeroBase(HeroBase heroBase) {
        this.heroBase = heroBase;
    }

    private void printSymbol(String symbol) {
        String border = "";
        for (int i = 0; i < 30; i++) {
            border += symbol;
        }
        BackendConsole.addConsole(border);
    }

}
