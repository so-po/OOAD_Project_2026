package conwaygame.Game;

import conwaygame.creatures.Creature;
import conwaygame.creatures.Strategy;
import conwaygame.creatures.StrategyFactory;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
    public enum types {
        DEAD, DEFAULT, EXPLOSIVE, SCARCITY
    }

    final int GRID_COLUMNS;
    final int GRID_ROWS;

    Creature[][] cells;
    StrategyFactory strategyFactory = new StrategyFactory();
    Random random;

    public Grid(int width, int height) throws Exception {
        if (width <= 0 || height <= 0) {
            throw new Exception("Bad grid width & height input.");
        }

        GRID_COLUMNS = width;
        GRID_ROWS = height;

        random = new Random();
        cells = new Creature[GRID_ROWS][GRID_COLUMNS];

        for (Creature[] row : cells) { //initialize list to be full of dead cells
            for (int i = 0; i < GRID_COLUMNS; i++) {
                row[i] = new Creature();
            }
        }
    }

    private Creature getCell(int x, int y) { return cells[y][x]; }

    private void setCell(int x, int y, Creature newCell) { cells[y][x] = newCell; }

    //Do we still want this function? is this useful for testing? just needs to be refactored
//    public void makeRandomCellAlive() {
//        //TODO: prevent duplicate placement.
//        makeCellAlive(random.nextInt(GRID_ROWS -1), random.nextInt(GRID_COLUMNS -1));
//    }

    public void toggleCellState(int x, int y, String selectedType) {
        if (cellExists(x, y)) {
            Creature cell = getCell(x, y);
            if (cell.isDead()){
                cell.setStrategy(strategyFactory.getStrategy(selectedType));
            }
            else {
                cell.kill();
            }
        }
    }

    public void playTurn() {
        for (int y = 0; y < GRID_ROWS; y++) {
            for (int x = 0; x < GRID_COLUMNS; x++) {
                Creature creature = getCell(x, y);
                creature.setStrategyBasedOnNeighbors(getCellNeighbors(x, y));
            }
        }
    }

    private List<Creature> getCellNeighbors(int x, int y) {
        ArrayList<Creature> neighbors = new ArrayList<>();
        for (int x_offset = -1; x_offset<=1; x_offset++) {
            for (int y_offset = -1; y_offset <=1; y_offset++) {
                if (isCellAlive(x+x_offset, y+y_offset) && !(x_offset==0 && y_offset==0)) {
                    neighbors.add(getCell(x+x_offset, y+y_offset));
                }
            }
        }
        return neighbors;
    }

    public Color getCellColor(int x, int y) { //TODO: does this break single responsibility principle?
        return getCell(x, y).getColor();
    }

    protected boolean isCellAlive(int x, int y) {
        return cellExists(x, y) && !getCell(x, y).isDead();
    }
    protected boolean isCellDefault(int x, int y) {
        return cellExists(x, y) && getCell(x, y).isDefault();
    }
    protected boolean isCellExplosive(int x, int y) {
        return cellExists(x, y) && getCell(x, y).isExplosive();
    }
    protected boolean isCellScarcity(int x, int y) {
        return cellExists(x, y) && getCell(x, y).isScarcity();
    }

    private boolean cellExists(int x, int y) {
        return (x >= 0 && y >= 0 && x < GRID_COLUMNS && y < GRID_ROWS) && (getCell(x, y) != null);
    }

}