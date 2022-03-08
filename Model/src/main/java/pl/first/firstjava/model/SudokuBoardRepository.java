package pl.first.firstjava.model;

public class SudokuBoardRepository {
    private SudokuBoard emptyBoard;

    public SudokuBoardRepository() {
        this.emptyBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    }

    public SudokuBoard createEmptyBoard() {
        return emptyBoard.clone();
    }
}
