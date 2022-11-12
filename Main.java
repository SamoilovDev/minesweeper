package minesweeper;

import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("What size field do you want? ");
        int size = scanner.nextInt();
        System.out.print("How many mines do you want on the field? ");
        int minesCount = scanner.nextInt();


        MainMechanisms game = new ProcessesInUserGame(size, size, minesCount);
        game.fieldFilling(); // create a field
        ((ProcessesInUserGame) game).userGame(); // starts the game
    }
}
