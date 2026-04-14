package conwaygame.Game;

import conwaygame.creatures.AbstractCreature;
import conwaygame.creatures.CreatureFactory;

import java.awt.*;
import java.util.Random;

public class Grid {

    final int GRID_COLUMNS;
    final int GRID_ROWS;

    AbstractCreature[][] cells;
    Random random;


    ///  ======== GRID CONSTRUCTION ========

    public Grid(int width, int height, CreatureFactory creatureFactory) throws Exception {

        if (width <= 0 || height <= 0) {
            throw new Exception("Bad grid width & height input.");
        }

        GRID_COLUMNS = width;
        GRID_ROWS = height;

        random = new Random();
        cells = new AbstractCreature[GRID_ROWS][GRID_COLUMNS];

        for (AbstractCreature[] row : cells) { //initialize list to be full of dead cells
            for (int i = 0; i < GRID_COLUMNS; i++) {
                row[i] = creatureFactory.createDefaultCreature();
            }
        }

    }

    public static GridBuilder getNewBuilder(CreatureFactory creatureFactory) {
        return new GridBuilder(creatureFactory);
    }

    public static class GridBuilder {
        CreatureFactory creatureFactory;
        int gridWidth;
        int gridHeight;


        public GridBuilder(CreatureFactory creatureFactory) {
            this.creatureFactory = creatureFactory;
        }

        public GridBuilder setDimensions(int x, int y) {
            this.gridWidth = x;
            this.gridHeight = y;
            return this;
        }

        public Grid build() throws Exception {
            return new Grid(gridWidth, gridHeight, creatureFactory);
            //TODO: if dimensions not set, make dimensions based on the existing cells
        }

    }

    /// ======== GRID LOGIC ========

    private AbstractCreature getCell(int x, int y) {
        return cells[y][x];
    }

    public void makeRandomCellAlive() {
        //TODO: prevent duplicate placement. Loop through & stop loop if grid is full
        int chosenx = random.nextInt(GRID_ROWS -1);
        int choseny = random.nextInt(GRID_COLUMNS -1);

        if (cellExists(chosenx, choseny)) {
            makeCellAlive(chosenx, choseny);
        }
    }


    public void toggleCellState(int x, int y) {
        if (cellExists(x, y)) {
            AbstractCreature cell = getCell(x, y);
            if (cell.isAlive()) {
                cell.kill();
            } else {
                cell.resurrect();
            }
        }
    }


    // for manually editing the grid before the game starts:
    public void makeCellAlive(int x, int y) {
        //set the cell to a default, alive cell
        if (cellExists(x, y)) {
            getCell(x, y).resurrect();
        }
    }

    public void makeCellDead(int x, int y) {
        if (cellExists(x, y)) {
            getCell(x, y).kill();
        }
    }

    public void playTurn() {
        for (int j = 0; j < GRID_ROWS; j++) {
            for (int i = 0; i < GRID_COLUMNS; i++) {
                AbstractCreature creature = getCell(i, j);
                creature.setAliveStateBasedOnNeighbours(countAliveNeighbors(i, j));
            }
        }
    }

    private int countAliveNeighbors(int x, int y) {
        int count = 0;

        //TODO: make more DRY
        if (isCellAlive(x-1, y+1)) {count++; }
        if (isCellAlive(x, y+1)) {count++; }
        if (isCellAlive(x+1, y+1)) {count++; }

        if (isCellAlive(x-1, y)) {count++; }
        if (isCellAlive(x+1, y)) {count++; }

        if (isCellAlive(x-1, y-1)) {count++; }
        if (isCellAlive(x, y-1)) {count++; }
        if (isCellAlive(x+1, y-1)) {count++; }

        return count;
    }

    public Color getCellColor(int x, int y) { //TODO: does this break single responsibility principle?
        if (isCellAlive(x, y)) {
            return getCell(x, y).getAliveColor();
        } else {
            return getCell(x, y).getDeadColor();
        }
    }

    protected boolean isCellAlive(int x, int y) {
        return cellExists(x, y) && getCell(x, y).isAlive();
    }


    private boolean cellExists(int x, int y) {
        return (x >= 0 && y >= 0 && x < GRID_COLUMNS && y < GRID_ROWS) && (getCell(x, y) != null);
    }

}