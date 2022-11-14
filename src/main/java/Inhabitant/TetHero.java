package main.java.Inhabitant;

import java.util.Map;

import main.java.StarMap;
import main.java.TFace;
import main.java.Base.HeroBase;
import main.java.Base.MapBase;
import main.java.Base.VaderBase;

public class TetHero extends TetRover {
    int id;
    boolean tFlier;
    int cipherKey;
    HeroBase heroBase;

    public TetHero(int row, int col, int tID, TFace tFace) {
        super(row, col, tID, tFace);
        heroBase = new HeroBase(row, col);
        heroBase.setDisplayID(tID + 10);
        tFace.addBase(heroBase);
    }

    @Override
    public boolean positionCheck(int row, int col) {
        return !(tFace.Surface[row][col] instanceof Inhabitant);
    }

    @Override
    public void setNextAction(int row, int col) {
        // if (tFace.Surface[row][col] instanceof Base)
        // int mapID = tFace.Surface[row][col].getMapID();
        // encrypt(mapID)
        if (tFace.Surface[row][col] instanceof MapBase) {
            if (((MapBase) tFace.Surface[row][col]).hasMap()) {
                this.nextAction = 1;
            }
            this.nextAction = tFlier ? 3 : 2;
        } else if (tFace.Surface[row][col] instanceof VaderBase) {
            this.nextAction = 4;
        } else {
            this.nextAction = 0;
        }
    }

    @Override
    public void action() {
        switch (nextAction) {
            case 0:
                walk();
                System.out.println("Hero walks");
                break;
            case 1:
                actionToMap(((MapBase) tFace.getBase(getRow(), getCol())).getStarMap());
                System.out.println("action to map in the map base");
                break;
            case 2:
                requestTFlier();
                System.out.println("Hero request a tFlier");
                break;
            case 3:
                flyTo(tFace.TetVaderBaseRow, tFace.TetVaderBaseCol);
                System.out.println("Hero flies to tetVaderBase");
                break;
            case 4:
                flyTo(heroBase.getRow(), heroBase.getCol());
                System.out.println("Hero flies to tetHeroBase");
                break;
        }
        setNextAction(getRow(), getCol());
    }

    private void flyTo(int row, int col) {
        tFace.removeObject(this);
        setRow(row);
        setCol(col);
        tFace.addObject(this);
    }

    public void requestTFlier() {
        tFlier = true;
    }

    // public void actionToMap(StarMap starMap){
    public void actionToMap(StarMap starMap) {
        boolean inVaderBase = (tFace.TetVaderBaseRow == getRow() && tFace.TetVaderBaseCol == getCol());

        if (inVaderBase) {
            cloneMap(starMap);
            restore(starMap);
            if (!starMap.isEncrypted()) {
                encrypt(starMap);
            } else if (starMap.getEncryptHeroID() != id) {
                starMap.addHero(this);
            } else {
                incrementRestorationCounter(starMap);
            }
        } else {
            if (starMap.isEncrypted()) {
                if (starMap.getEncryptHeroID() != id) {
                    starMap.addHero(this);
                } else {
                    decrypt(starMap);
                }
            }
            display(starMap);
        }
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
    }

    public void decrypt(StarMap starMap) {
        String message = starMap.getText();
        starMap.setText(caesarCipher(message, -cipherKey));
    }

    public void check(StarMap starMap) {

    }

    public void restore(StarMap starMap) {
        MapBase mapBase = starMap.getMapBase();
        starMap.setRow(mapBase.getRow());
        starMap.setCol(mapBase.getCol());
        mapBase.setStarMap(starMap);

    }

    public void cloneMap(StarMap starMap) {

    }

    public void display(StarMap starMap) {

    }

    public void incrementRestorationCounter(StarMap starMap) {
        starMap.setRestorationCounter(starMap.getRestorationCounter() + 1);
    }

    public void setHeroBase(HeroBase heroBase) {
        this.heroBase = heroBase;
    }

}
