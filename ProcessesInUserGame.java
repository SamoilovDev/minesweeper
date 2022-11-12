package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class ProcessesInUserGame extends UserInterface {

    private int counterOfMinesFind = 0;

    private boolean firstMove = true;

    private final Random random = new Random();

    private ArrayList<int[]> coordinates = new ArrayList<>();

    private boolean isFirstMove() {
        return firstMove;
    }

    private void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    private int getCounterOfMinesFind() {
        return counterOfMinesFind;
    }

    private void setCounterOfMinesFind(int counterOfMinesFind) {
        this.counterOfMinesFind = counterOfMinesFind;
    }

    protected ProcessesInUserGame(int lengthX, int lengthY, int numberOfMines) {
        super(lengthX, lengthY, numberOfMines);
    }

    protected void userGame() {

        while (counterOfMinesFind < super.NUMBER_OF_MINES && super.isClosedSpace()) {
            if (isFirstMove()) printField(super.field);
            System.out.print("Set/unset mine marks or claim a cell as free: ");

            int x = Main.scanner.nextInt();
            int y = Main.scanner.nextInt();
            String moveChoice = Main.scanner.next();

            switch (moveChoice) {
                case "free" -> free(x, y);
                case "mine" -> setCounterOfMinesFind(super.userMarkOnField(x, y, getCounterOfMinesFind()));
                default -> System.out.println("Wrong action!");
            }
        }

        if (counterOfMinesFind == super.NUMBER_OF_MINES || !super.isClosedSpace()) {
            System.out.println("Congratulations! You found all the mines!");
        } else {
            System.out.println("You stepped on a mine and failed!");
        }
    }

    private void addToArraylist(int x, int y) { // adds coordinates of the next free place

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((x + i) >= 0 && (x + i) < super.LENGTH_X && (y + j) >= 0 && (y + j) < super.LENGTH_Y) {
                   if (super.getPlaceOnField(x + i, y + j) == 0) {

                       coordinates.add(new int[]{x + i, y + j});
                       super.setPlaceOnField(x + i, y + j, super.getPlaceOnField(x + i, y + j) + 10);

                   } else if (super.getPlaceOnField(x + i, y + j) > 0 &&
                           super.getPlaceOnField(x + i, y + j) < 10) {

                       super.setPlaceOnField(x + i, y + j, super.getPlaceOnField(x + i, y + j) + 10);

                   } else if (super.getPlaceOnField(x + i, y + j) >= 100 &&
                           super.getPlaceOnField(x + i, y + j) < 110) {

                       super.setPlaceOnField(x + i, y + j,super.getPlaceOnField(x + i, y + j) - 90);
                       counterOfMinesFind++;
                   }

                }
            }
        }
    }

    private void free(int x, int y) {

        if (field.get(y - 1)[x - 1] == 0 || field.get(y - 1)[x - 1] == 100) {

            if (isFirstMove()) {
                super.setMines(x, y, random);
                checkNeighbours();
                setFirstMove(false);
            }
            addToArraylist(x - 1, y - 1);

            while (coordinates.size() > 0) {
                int[] temporaryCoordinates = coordinates.get(coordinates.size() - 1);

                coordinates.remove(temporaryCoordinates);
                addToArraylist(temporaryCoordinates[0], temporaryCoordinates[1]);
            }

        } else if (field.get(y - 1)[x - 1] > 0 && field.get(y - 1)[x - 1] < 9) {

            setPlaceOnField(x - 1, y - 1, field.get(y - 1)[x - 1] + 10);

        } else if (field.get(y - 1)[x - 1] >= -10 && field.get(y - 1)[x - 1] < 0) {

            for (int i = 0; i < mineSearch[0].length; i++) {
                setPlaceOnField(mineSearch[1][i], mineSearch[0][i], -404);
            }

            setCounterOfMinesFind(NUMBER_OF_MINES + 1);
        } else {
            System.out.println("It was opened or marked early!");
        }
        printField(super.field);
    }

}
