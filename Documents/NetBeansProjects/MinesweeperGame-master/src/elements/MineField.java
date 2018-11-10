package elements;

import java.util.*;
import staticValues.*;

public class MineField {

    private int height;
    private int width;
    private double mineChance;
    private Cell[][] field;

    public MineField(int height, int width, double mineChance) {
        this.height = height + 2; // extra cells for coordinates columns
        this.width = width + 2;
        this.mineChance = mineChance; // has to be between (0.0 - 1.0) 
        this.field = new Cell[this.height][this.width];
        hideMines();
        createUnminedCells();
        addCoordinates();
    }

    // MINEFIELD CELL INITIALISATION #1 - hide mines
    private void hideMines() {
        Random random = new Random();
        for (int h = 1; h <= this.height - 2; h++) {
            // we exclude cells that belong to the border
            for (int w = 1; w <= this.width - 2; w++) {
                if (random.nextDouble() <= this.mineChance) {
                    this.field[h][w] = new Cell(false, true); //!!! laikinai 1as - true
                } else {
                    this.field[h][w] = new Cell(false, false); //!!! laikinai 1as - true
                }
            }
        }
    }

    // MINEFIELD CELL INITIALISATION #2 - generate values of other cells
    public void createUnminedCells() {
        for (int h = 1; h <= this.height - 2; h++) {
            // we exclude cells that belong to the border
            for (int w = 1; w <= this.width - 2; w++) {
                if (this.field[h][w].getIsMined()) {
                    continue;
                }
                int minedAdjacentCells = getAdjacentCells(h, w).countMinedCells();
                this.field[h][w].setActualValue(minedAdjacentCells);
            }
        }
    }

    // MINEFIELD CELL INITIALISATION #3 - field border
    private void addCoordinates() {
        // horizontal coordinates
        for (int w = 0; w <= this.width - 1; w++) {
            if (w < 10) {
                this.field[0][w] = new Cell(" " + w);
                this.field[this.height - 1][w] = new Cell(" " + w);
            } else {
                this.field[0][w] = new Cell("" + w);
                this.field[this.height - 1][w] = new Cell("" + w);
            }
        }
        // vertical coordinates
        for (int h = 0; h <= this.height - 1; h++) {
            if (h < 10) {
                this.field[h][0] = new Cell(" " + h);
                this.field[h][this.width - 1] = new Cell(" " + h);
            } else {
                this.field[h][0] = new Cell("" + h);
                this.field[h][this.width - 1] = new Cell("" + h);
            }
        }
        //positions at the corners
        this.field[0][0].setBorderCellValue("  ");
        this.field[this.height - 1][0].setBorderCellValue("  ");
        this.field[0][this.width - 1].setBorderCellValue("  ");
        this.field[this.height - 1][this.width - 1].setBorderCellValue("  ");
    }

// returns an object that contains Cells that are adjacent to give set of coordinates 
    private AdjacentCellsMap getAdjacentCells(int hCoordinate, int wCoordinate) {
        AdjacentCellsMap adjacentCells = new AdjacentCellsMap();
        for (int h = hCoordinate - 1; h <= hCoordinate + 1; h++) {
            for (int w = wCoordinate - 1; w <= wCoordinate + 1; w++) {
                // we exclude boder cells and the cell in the centre itself
                if (w == wCoordinate & h == hCoordinate
                        || w == 0 || h == 0
                        || w == this.width - 1 || h == this.height - 1) {
                    continue;
                }
                // this method gets recursive calls. If a Cell being called is already open,
                // this means it has already been worked upon by this method in previous calls
                if (!this.field[h][w].getIsOpen()) {
                    adjacentCells.addCell(this.field[h][w], new int[]{h, w});
                }
            }
        }
        return adjacentCells;
    }

    private void openAdjacentCells(int hCoordinate, int wCoordinate) {
        AdjacentCellsMap adjacentCells = getAdjacentCells(hCoordinate, wCoordinate);
        // all Cells adjacent to the given coordinates are opened
        for (Cell c : adjacentCells.getAdjacentCells()) {
            c.openCell();
        }
        // if among these there are some empty Cells, we need to
        // open further Cells (those that are adjacent to the empty)
        for (Cell c : adjacentCells.getAdjacentCells()) {
            if (c.getValueActual().equals(CellValue.EMPTY)) {
                int[] cellCoordinates = adjacentCells.getCellCoordinates(c);
                openAdjacentCells(cellCoordinates[0], cellCoordinates[1]);
            }
        }
    }

    public boolean allMinesFound() {
        int allMines = countCellsByValue(CellValue.MINED, true);
        for (int h = 1; h <= this.height - 2; h++) {
            for (int w = 1; w <= this.width - 2; w++) {
                if (this.field[h][w].getValueActual().equals(CellValue.MINED)
                        && this.field[h][w].getValueDisplayed().equals(CellValue.FLAGGED)) {
                    allMines--;
                }
            }
        }
        return allMines == 0;
    }
    
    // returns the number of mines that are not found
    // Note: here any flagged Cell is treated as a mine that was found
    public int countMinesRemaining(){
        int allMines = countCellsByValue(CellValue.MINED, true);
        int flaggedCells = countCellsByValue(CellValue.FLAGGED, false);
        //the result has to be non-negative
        return Math.max(0, allMines - flaggedCells);
    }

    // method calculates number of the Cells with the give value
    // @param value - Cell value to be calculated
    // @param actual - true - actual value of the Cells is looked up; false - value for display is being looked up 
    public int countCellsByValue(CellValue value, boolean actual) {
        int result = 0;
        for (int h = 1; h <= this.height - 2; h++) {
            for (int w = 1; w <= this.width - 2; w++) {
                if (!actual && this.field[h][w].getValueDisplayed().equals(value)) {
                    result++;
                } else if (actual && this.field[h][w].getValueActual().equals(value)) {
                    result++;
                }
            }
        }
        return result;
    }

    public boolean performActionOnCell(String[] command) {
        int hCoordinate = Integer.parseInt(command[0]);
        int wCoordinate = Integer.parseInt(command[1]);
        Cell cell = this.field[hCoordinate][wCoordinate];
        String action = command[2].toLowerCase();

        //action "Open"
        if (action.equals("o")) {
            cell.openCell();
            if (cell.getIsMined()) {
                cell.setActualValue(CellValue.EXPLODED);
                return false;
            } // if a cell is empty - we need to open cells that are surrounding it
            else if (cell.getValueActual().equals(CellValue.EMPTY)) {
                openAdjacentCells(hCoordinate, wCoordinate);
                return true;
            } else {
                return true;
            }
        }
        //action "Flag"
        if (action.equals("f")) {
            // only unopened cells are marked with a flag
            if (!cell.getIsOpen()) {
                cell.setValueDisplayed(CellValue.FLAGGED);
            }
            return true;
        } //action "Mark as uncertain" (action.equals("?"))
        else {
            // only unopened cells can be marked as uncertain
            if (!cell.getIsOpen()) {
                cell.setValueDisplayed(CellValue.UNCERTAIN);
            }
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("-------------------------").append("\n");
        result.append("Mines left to find #").append(countMinesRemaining()).append("\n");
        result.append("\n");
        for (int h = 0; h <= this.height - 1; h++) {
            for (int w = 0; w <= this.width - 1; w++) {
                result.append(this.field[h][w]);

                if (w < this.width - 1) {
                    result.append("|");
                }

            }
            result.append("\n");
        }
        return result.toString();
    }

}
