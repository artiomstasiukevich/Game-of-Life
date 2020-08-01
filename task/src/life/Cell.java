package life;

public class Cell {
    boolean isAlive;
    boolean nextStep = false;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void printCell() {
        System.out.print(isAlive ? "O" : " ");
    }

    public void Evolution() {
        isAlive = nextStep;
    }
}
