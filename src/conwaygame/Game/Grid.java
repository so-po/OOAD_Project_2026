package conwaygame.Game;

import conwaygame.creatures.Creature;
import conwaygame.creatures.CreatureType;
import conwaygame.creatures.StrategyFactory;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Grid {

    final int GRID_COLUMNS;
    final int GRID_ROWS;

    Creature[][] cells;
    StrategyFactory strategyFactory = new StrategyFactory();
    Random random;

    //used for injecting creatures
    public record SpawnEntry(String type, int x, int y){}

    public Grid(int width, int height, ArrayList<SpawnEntry> spawnCreatureLocations) throws Exception {
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

        //spawn based on injected creatures
        for (SpawnEntry creature : spawnCreatureLocations){
            toggleCellState(creature.x, creature.y, creature.type);
//            getCell(creature.x,creature.y);
            reviveCell(creature.x,creature.y);
        }
    }

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

    public static GridBuilder getNewBuilder() {
        return new GridBuilder();
    }

    public static class GridBuilder {
        int gridWidth;
        int gridHeight;
        ArrayList<SpawnEntry> spawnCreatureLocations = new ArrayList<>();

        public GridBuilder() {}

        public GridBuilder setDimensions(int x, int y) {
            this.gridWidth = x;
            this.gridHeight = y;
            return this;
        }

        public GridBuilder addDefaultCreatureToLocation(int x, int y){
            spawnCreatureLocations.add(new SpawnEntry("DEFAULT", x, y));
            return this;
        }

        public GridBuilder addExplosiveCreatureToLocation(int x, int y){
            spawnCreatureLocations.add(new SpawnEntry("EXPLOSIVE", x, y));
            return this;
        }

        public GridBuilder addScarcityCreatureToLocation(int x, int y){
            spawnCreatureLocations.add(new SpawnEntry("SCARCITY", x, y));
            return this;
        }

        public Grid build() throws Exception {
            return new Grid(gridWidth, gridHeight, spawnCreatureLocations);
            //TODO: if dimensions not set, make dimensions based on the existing cells
        }
    }

    private Creature getCell(int x, int y) { return cells[y][x]; }

    private boolean cellExists(int x, int y) {
        return (x >= 0 && y >= 0 && x < GRID_COLUMNS && y < GRID_ROWS) && (getCell(x, y) != null);
    }

    protected void reviveCell(int x, int y) { //for testing, to revive the cell w/out changing strategy
        if (cellExists(x, y)) {
            Creature cell = getCell(x, y);
            cell.makeAlive();
        }
    }

    public void toggleCellState(int x, int y, String selectedType) {
        if (cellExists(x, y)) {
            Creature cell = getCell(x, y);
            if (cell.isDead()){
                cell.reviveWithStrategy(strategyFactory.getStrategy(CreatureType.valueOf(selectedType)));
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
                creature.setStateBasedOnNeighbors(getCellNeighbors(x, y));
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

    // For testing:
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

    public Integer getHeight() {return GRID_ROWS;}
    public Integer getWidth() {return GRID_COLUMNS;}
}