package life;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Universe ouruniverse = new Universe(size);
        ouruniverse.Creation();

        GameOfLife gameWindow = new GameOfLife();
        gameWindow.DrawWindow(ouruniverse);

        for(int i = 1; i < 100; i++) {

            if(gameWindow.stoped) {
                i--;
            } else {
                System.out.printf("Generation #%d", i);
                System.out.println();
                System.out.printf("Alive: %d", ouruniverse.getAlive());
                System.out.println();
                ouruniverse.showUniverse();
                ouruniverse.NextEvolutionStep();

                gameWindow.UpdateWindow();
            }
            if(gameWindow.needToReset) {
                i = 0;
                ouruniverse.generation = 0;
                ouruniverse.Creation();
                gameWindow.UpdateWindow();
                gameWindow.needToReset = false;
            }


            clearConsoleAndWait();

        }
    }


    private static void clearConsoleAndWait() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {
        }
    }
}
