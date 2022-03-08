package pl.comp.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pl.first.firstjava.model.SudokuBoard;

public enum DifficultyLevel {

    LATWY("10", 10),
    SREDNI("30", 30),
    TRUDNY("50", 50);

    public final String label;
    private int blanks;


    @Override
    public String toString() {
        return label;
    }

    DifficultyLevel(String label, int blanks) {
        this.label = label;
        this.blanks = blanks;
    }

    public void clearFields(SudokuBoard sudokuBoard) {
        int cleared = 0;
        List<Integer> indexesX = new ArrayList<>();
        List<Integer> indexesY = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            indexesX.add(i);
            indexesY.add(i);
        }
        while (cleared < blanks) {
            Collections.shuffle(indexesX);
            Collections.shuffle(indexesY);
            int x = indexesX.get(0);
            int y = indexesY.get(0);
            if (sudokuBoard.get(x, y) != 0) {
                sudokuBoard.set(x, y, 0);
                sudokuBoard.set(x, y, 0);
                cleared++;
            }
        }
    }
}