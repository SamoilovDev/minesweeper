package minesweeper;

import java.util.Random;
import java.util.ArrayList;

public class MainMechanisms {

    final protected int LENGTH_X;

    final protected int LENGTH_Y;

    final protected int NUMBER_OF_MINES;

    protected ArrayList<int[]> field;

    protected int[][] mineSearch;

    public MainMechanisms(int lengthX, int lengthY, int numberOfMines) {
        this.LENGTH_X = lengthX;
        this.LENGTH_Y = lengthY;
        this.NUMBER_OF_MINES = numberOfMines;
    }

    protected void setPlaceOnField(int X, int Y, int state) {
        this.field.get(Y)[X] = state;
    }

    protected int getPlaceOnField(int X, int Y) {
        return field.get(Y)[X];
    }

    protected void createPlayingField() {
        this.mineSearch = new int[2][this.NUMBER_OF_MINES]; // 0 - y; 1 - x

        for (int i = 0; i < this.LENGTH_Y; i++) {
            for (int j = 0; j < this.LENGTH_X; j++) {
                this.setPlaceOnField(j, i, 0);
            }
        }
    }

    protected void fieldFilling() { // fill the gaming field at the start of game
        field = new ArrayList<>(LENGTH_X);

        for (int i = 0; i < LENGTH_Y; i++) {
            field.add(new int[LENGTH_X]);
        }

        createPlayingField();
    }

    protected void setMines(int X, int Y, Random random) { // set mines at the start of game
        int counter = 0;

        while (counter < this.NUMBER_OF_MINES) {
            int randomY = random.nextInt(this.LENGTH_Y);
            int randomX = random.nextInt(this.LENGTH_X);

            if (this.field.get(randomY)[randomX] == 0 && (randomX != X || randomY != Y)) {
                this.field.get(randomY)[randomX] = -10;
                this.mineSearch[0][counter] = randomY;
                this.mineSearch[1][counter] = randomX;
                counter++;
            }
        }

    }

    protected void checkNeighbours() { // set numbers around mines

        for (int i = 0; i < this.NUMBER_OF_MINES; i++) {

            if (this.mineSearch[1][i] > 0 && this.mineSearch[1][i] < this.LENGTH_X - 1) {
                setNumbersNotAroundEdgesX(i);
            } else if (this.mineSearch[1][i] == 0 || this.mineSearch[1][i] == this.LENGTH_X - 1) {
                setNumbersAroundEdgesX(i);
            }
        }
    }

    private void setNumbersNotAroundEdgesX(int i) {
        setPlaceOnField(this.mineSearch[1][i] - 1, this.mineSearch[0][i],
                this.field.get(this.mineSearch[0][i])[this.mineSearch[1][i] - 1] + 1);

        setPlaceOnField(this.mineSearch[1][i] + 1, this.mineSearch[0][i],
                this.field.get(this.mineSearch[0][i])[this.mineSearch[1][i] + 1] + 1);

        for (int k = this.mineSearch[1][i] - 1; k <= this.mineSearch[1][i] + 1; k++) {

            if (this.mineSearch[0][i] > 0) {
                this.setPlaceOnField(k, mineSearch[0][i] - 1,
                        this.field.get(mineSearch[0][i] - 1)[k] + 1);
            }

            if (mineSearch[0][i] < this.LENGTH_Y - 1) {
                this.setPlaceOnField(k, this.mineSearch[0][i] + 1,
                        this.field.get(this.mineSearch[0][i] + 1)[k] + 1);
            }
        }
    }

    private void setNumbersAroundEdgesX(int i) {
        if (this.mineSearch[1][i] == 0) {
            setPlaceOnField(this.mineSearch[1][i] + 1, this.mineSearch[0][i],
                    this.field.get(this.mineSearch[0][i])[this.mineSearch[1][i] + 1] + 1);

            for (int k = this.mineSearch[1][i]; k <= this.mineSearch[1][i] + 1; k++) {

                if (this.mineSearch[0][i] > 0) {
                    setPlaceOnField(k, this.mineSearch[0][i] - 1, this.field.get(mineSearch[0][i] - 1)[k] + 1);
                }
                if (mineSearch[0][i] < this.LENGTH_Y - 1) {
                    setPlaceOnField(k, this.mineSearch[0][i] + 1, this.field.get(mineSearch[0][i] + 1)[k] + 1);
                }

            }

        } else {
            setPlaceOnField(this.mineSearch[1][i] - 1, this.mineSearch[0][i],
                    this.field.get(this.mineSearch[0][i])[this.mineSearch[1][i] - 1] + 1);

            for (int k = this.mineSearch[1][i] - 1; k <= this.mineSearch[1][i]; k++) {

                if (this.mineSearch[0][i] > 0) {
                    setPlaceOnField(k, this.mineSearch[0][i] - 1, this.field.get(mineSearch[0][i] - 1)[k] + 1);
                }
                if (this.mineSearch[0][i] < this.LENGTH_Y - 1) {
                    setPlaceOnField(k, this.mineSearch[0][i] + 1, this.field.get(mineSearch[0][i] + 1)[k] + 1);
                }

            }
        }
    }
}