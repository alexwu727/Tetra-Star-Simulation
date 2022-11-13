package main.java.Inhabitant;

import main.java.TFace;

public class TetHero extends TetRover {

    public TetHero(int row, int col, int tID) {
        super(row, col, tID);
        // TODO Auto-generated constructor stub
    }

    int id;
    boolean tFlier;
    int cipherKey;

    @Override
    public boolean positionCheck(int row, int col) {
        return !(tFace.Surface[row][col] instanceof Inhabitant);
    }

    @Override
    public void newPositionAction(int row, int col) {
        // if (tFace.Surface[row][col] instanceof Base)
        // int mapID = tFace.Surface[row][col].getMapID();
        // encrypt(mapID)
        if (tFace.Surface[row][col] instanceof Inhabitant) {
            System.out.println("Hero " + getTID() + " Found a map base.");
        }
    }

    public void requestTFlier() {
        tFlier = true;
    }

    // public void actionToMap(StarMap starMap){
    // public void actionToMap(StarMap starMap) {
    // boolean inVaderBase;
    // if (inVaderBase) {
    // clone(starMap);
    // restore(starMap);
    // if (!starMap.isEncrypted()) {
    // encrypt(starMap);
    // } else if (starMap.getEncryptHeroID != id) {
    // starMap.addHero();
    // } else {
    // starMap.incrementRestorationCounter();
    // }
    // } else {
    // if (starMap.isEncrypted()) {
    // if (starMap.getEncryptHeroID != id) {
    // starMap.addHero();
    // } else {
    // decrypt(starMap);
    // }
    // }
    // display(starMap);
    // }
    // }

    // private String caesarCipher(String message, int offset) {
    // StringBuilder result = new StringBuilder();
    // for (char character : message.toCharArray()) {
    // if (character != ' ') {
    // int originalAlphabetPosition = character - 'a';
    // int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
    // char newCharacter = (char) ('a' + newAlphabetPosition);
    // result.append(newCharacter);
    // } else {
    // result.append(character);
    // }
    // }
    // return result.toString();
    // }

    // public void encrypt(StarMap starMap) {
    // String message = starMap.getText();
    // starMap.setText(caesarCipher(message, cipherKey));
    // }

    // public void decrypt(StarMap starMap) {
    // String message = starMap.getText();
    // starMap.setText(caesarCipher(message, -cipherKey));
    // }

    // public void check(StarMap starMap) {

    // }

    // public void restore(StarMap starMap) {
    // int row = starMap.getRow();
    // int col = starMap.getCol();
    // int baseRow = starMap.getBaseRow();
    // int baseCol = starMap.getBaseCol();
    // starMap.restore();
    // }

    // public void cloneMap(StarMap starMap) {

    // }

    // public void display(StarMap starMap) {

    // }

}
