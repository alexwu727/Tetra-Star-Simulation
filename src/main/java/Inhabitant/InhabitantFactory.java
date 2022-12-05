package main.java.Inhabitant;

public class InhabitantFactory {
    private Inhabitant inhabitant;

    public Inhabitant createInhabitant(String type, int row, int col, int tID) {
        if (type.equalsIgnoreCase("TetRover")) {
            inhabitant = new TetRover(row, col, tID);
        } else if (type.equalsIgnoreCase("TetHero")) {
            inhabitant = new TetHero(row, col, tID);
        } else if (type.equalsIgnoreCase("TetVader")) {
            inhabitant = new TetVader(row, col, tID);
        }
        return inhabitant;
    }
}
