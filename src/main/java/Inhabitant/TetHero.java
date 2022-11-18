package main.java.Inhabitant;

import main.java.Map.StarMap;
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
    }

    @Override
    public boolean positionCheck(int row, int col) {
        Locatable object = tFace.Surface[row][col];
        return !(object instanceof TetRover || object instanceof River);
    }

    @Override
    public int nextActionEnterMapBase(MapBase mapBase) {
        return mapBase.hasMap() ? 1 : 2;
    }

    @Override
    public void action() {
        switch (nextAction) {
            case 0:
                walk();
                break;
            case 1:
                actionToMapInMapBase((StarMap) ((MapBase) tFace.getBase(getRow(), getCol())).getMap());
                System.out.println("action to map in the map base");
                break;
            case 2:
                requestTFlier();
                System.out.println("Hero request a tFlier");
                break;
            case 3:
                flyTo(tFace.TetVaderBaseRow, tFace.TetVaderBaseCol);
                System.out.println("Hero flies to tetVaderBase and check if the map is there.");
                check(findMapID);
                break;
            case 4:
                actionToMapInVaderBase();
                flyTo(heroBase.getRow(), heroBase.getCol());
                System.out.println("Hero retrieve the map, encrypt it, restore it, and flies back to his base.");
                break;
        }
    }

    public void actionToMapInMapBase(StarMap starMap) {
        if (starMap.isEncrypted()) {
            if (starMap.getEncryptHeroID() != tID) {
                starMap.addHero(this);
                System.out.println(
                        "Map in the map base is already encrypted by other hero, add id to the header and display encrypted content.");
            } else {
                decrypt(starMap);
                System.out.println("Display the decrpyted map in the map base.");
            }
        } else {
            System.out.println("Display unencrypted map in the map base.");
        }
        display(starMap);
        this.nextAction = 0;
    }

    public void display(StarMap starMap) {
        printSymbol(starMap.getEncryptSymbol());
        String Date = "Dec 2022";
        System.out.println("ID: " + starMap.getEncryptHeroID() + " Date: " + Date + " (Tetra Time)");
        String message = starMap.getText();
        System.out.println("Text: " + message);
        printSymbol(starMap.getEncryptSymbol());
    }

    public void requestTFlier() {
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
            System.out.println("Where the hell map " + findMapID + " go?");
        }
    }

    public void actionToMapInVaderBase() {
        this.tFlier = false;
        String key = tFace.convertToKey(getRow(), getCol());
        VaderBase vaderBase = (VaderBase) tFace.baseMap.get(key);
        StarMap starMap = vaderBase.removeMap(findMapID);
        cloneMap(starMap);
        restore(starMap);
        System.out.println("Clone and restore the map in the vader base.");
        if (!starMap.isEncrypted()) {
            encrypt(starMap);
            System.out.println("Encrypt the map in the vader base.");
            display(starMap);
        } else if (starMap.getEncryptHeroID() != tID) {
            starMap.addHero(this);
            System.out.println("Map in the vader base is already encrypted by other hero, add id to the header.");
        } else {
            incrementRestorationCounter(starMap);
            System.out.println("Map in the vader base is already encrypted, increments the restoration_counter.");
        }
        this.nextAction = 0;
    }

    private String caesarCipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public void encrypt(StarMap starMap) {
        String message = starMap.getText();
        starMap.setText(caesarCipher(message, cipherKey));
        starMap.encrpyt();
        starMap.setEncryptHeroID(tID);
    }

    public void decrypt(StarMap starMap) {
        String message = starMap.getText();
        starMap.setText(caesarCipher(message, -cipherKey));
        starMap.decrpyt();
        starMap.setEncryptHeroID(0);
    }

    public void restore(StarMap starMap) {
        MapBase mapBase = starMap.getMapBase();
        starMap.setRow(mapBase.getRow());
        starMap.setCol(mapBase.getCol());
        mapBase.setMap(starMap);
    }

    public void cloneMap(StarMap starMap) {
        StarMap clone = starMap.clone();
        heroBase.cloneMap(clone);

    }

    public void incrementRestorationCounter(StarMap starMap) {
        starMap.setRestorationCounter(starMap.getRestorationCounter() + 1);
    }

    public void setHeroBase(HeroBase heroBase) {
        this.heroBase = heroBase;
    }

    private void printSymbol(String symbol) {
        for (int i = 0; i < 20; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }

}
