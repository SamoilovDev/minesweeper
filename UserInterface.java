package minesweeper;

import java.util.ArrayList;

public class UserInterface extends MainMechanisms {

    protected UserInterface(int lengthX, int lengthY, int numberOfMines) {
        super(lengthX, lengthY, numberOfMines);
    }

    protected void printField(ArrayList<int[]> fieldState) {
        if (super.LENGTH_X >= 10) System.out.print(" ");
        System.out.print(" | ");

        for (int i = 1; i <= super.LENGTH_X; i++) System.out.print(i + " ");

//        if (super.LENGTH_X >= 10) System.out.print(" ");
        if (super.LENGTH_X < 10) {
            System.out.print("|\n-|-");
        } else System.out.print("|\n -| ");

        for (int i = 1; i <= super.LENGTH_X; i++) {
            if (i >= 10) System.out.print("-");
            System.out.print("--");
        }

        System.out.println("|");
        int count = 0;

        for (int[] lineOnField : fieldState) {
            count++;
            if (count < 10 && super.LENGTH_Y >= 10) System.out.print(" ");
            System.out.print(count+"| ");
            int count2 = 0;

            for (int placeOnLine : lineOnField) {
                count2++;
                if (count2 >= 10) System.out.print(" ");
                if (placeOnLine >= -10 && placeOnLine < 10) System.out.print(". ");
                if (placeOnLine > 10 && placeOnLine < 20) System.out.print(placeOnLine - 10 + " ");
                if (placeOnLine >= 9 && placeOnLine < 11) System.out.print("/ ");
                if (placeOnLine >= 100 && placeOnLine < 110 || placeOnLine == -100) System.out.print("* ");
                if (placeOnLine == -404) System.out.print("X ");
            }

            System.out.println("|");
        }
        if (super.LENGTH_X >= 10) System.out.print(" ");
        System.out.print("-|-");
        for (int i = 1; i <= super.LENGTH_X; i++) {
            if (i >= 10) System.out.print("-");
            System.out.print("--");
        }
        System.out.println("|\n");
    }

    protected int userMarkOnField(int x, int y, int counterOfMinesFind) { // set user's mine-mark on the field
        if (super.getPlaceOnField(x - 1, y - 1) >= -10 && super.getPlaceOnField(x - 1, y - 1) < 0
                || super.getPlaceOnField(x - 1, y - 1) >= 100 && super.getPlaceOnField(x - 1, y - 1) < 110) {

            if (super.getPlaceOnField(x - 1, y - 1) >= -10 && super.getPlaceOnField(x - 1, y - 1) < 0) {
                super.setPlaceOnField(x - 1, y - 1, -100);
            } else {
                super.setPlaceOnField(x - 1, y - 1, super.getPlaceOnField(x - 1, y - 1) - 100);
            }

            counterOfMinesFind++;
            printField(super.field);

        } else if (super.getPlaceOnField(x - 1, y - 1) >= 0 && super.getPlaceOnField(x - 1, y - 1) < 10
                || super.getPlaceOnField(x - 1, y - 1) == -100) {

            if (super.getPlaceOnField(x - 1, y - 1) == -100) {
                super.setPlaceOnField(x - 1, y - 1, -10);
            } else {
                super.setPlaceOnField(x - 1, y - 1, super.getPlaceOnField(x - 1, y - 1) + 100);
            }
            counterOfMinesFind--;
            printField(super.field);

        }  else if (super.getPlaceOnField(x - 1, y - 1) >= 10 && super.getPlaceOnField(x - 1, y - 1) < 20) {
            System.out.println("It was opened!");
        }
        return counterOfMinesFind;
    }

    protected boolean isClosedSpace() { // check closed space without mines
        boolean question = false;

        for (int[] lineOnField : field) {
            for (int placeOnLine : lineOnField) {
                if (placeOnLine >= 0 && placeOnLine < 10 || placeOnLine >= 100 && placeOnLine < 110) {
                    question = true;
                }
            }
        }
        return question;
    }

}
