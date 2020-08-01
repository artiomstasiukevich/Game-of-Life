package life;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Universe {
    int size;
    Random random;
    Cell[][] universe;
    int generation = 1;

    public Universe(int size) {
        this.size = size;
        random = new Random();
        universe = new Cell[size][size];
    }


    public void Creation() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                universe[i][j] = new Cell(random.nextBoolean());
            }
        }
    }

    public int getAlive() {
        int result = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(universe[i][j].isAlive) {
                    result += 1;
                }
            }
        }
        return result;
    }

    public void showUniverse() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                universe[i][j].printCell();
            }
            System.out.println();
        }
    }

    public void NextEvolutionStep() {
        generation += 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int aliveNeighbors = FindAliveNeighbors(i, j);
                if (aliveNeighbors == 3) {
                    universe[i][j].nextStep = true;
                } else if (universe[i][j].isAlive && aliveNeighbors == 2) {
                    universe[i][j].nextStep = true;
                } else {
                    universe[i][j].nextStep = false;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                universe[i][j].Evolution();
            }
        }
    }

    private int FindAliveNeighbors(int i, int j) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        neighbors.add(universe[i == 0 ? size - 1 : i - 1][j == 0 ? size - 1 : j - 1]);
        // * . .
        // . 0 .
        // . . .
        neighbors.add(universe[i][j == 0 ? size - 1 : j - 1]);
        // . . .
        // * 0 .
        // . . .
        neighbors.add(universe[i == 0 ? size - 1 : i - 1][j]);
        // . . .
        // . 0 .
        // . * .
        neighbors.add(universe[(i + 1) % size][j == 0 ? size - 1 : j - 1]);
        // . . .
        // . 0 .
        // * . .
        neighbors.add(universe[i == 0 ? size - 1 : i - 1][(j + 1) % size]);
        // . . *
        // . 0 .
        // . . .
        neighbors.add(universe[(i + 1) % size][j % size]);
        // . * .
        // . 0 .
        // . . .
        neighbors.add(universe[i][(j + 1) % size]);
        // . . .
        // . 0 *
        // . . .
        neighbors.add(universe[(i + 1) % size][(j + 1) % size]);
        // . . .
        // . 0 .
        // . . *
        int answer = 0;
        for (Cell one : neighbors) {
            if (one.isAlive) {
                answer += 1;
            }
        }
        return answer;
    }
}
