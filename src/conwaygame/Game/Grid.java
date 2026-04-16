package conwaygame.Game;

import conwaygame.creatures.AbstractCreature;
import conwaygame.creatures.CreatureFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
    public enum types {
        DEAD, DEFAULT, EXPLOSIVE, SCARCITY
    }

    final int GRID_COLUMNS;
    final int GRID_ROWS;

    AbstractCreature[][] cells;
    CreatureFactory creatureFactory = new CreatureFactory();
    Random random;


    public Grid(int width, int height) throws Exception {

        if (width <= 0 || height <= 0) {
            throw new Exception("Bad grid width & height input.");
        }

        GRID_COLUMNS = width;
        GRID_ROWS = height;

        random = new Random();
        cells = new AbstractCreature[GRID_ROWS][GRID_COLUMNS];

        for (AbstractCreature[] row : cells) { //initialize list to be full of dead cells
            for (int i = 0; i < GRID_COLUMNS; i++) {
//                row[i] = creatureFactory.createDefaultCreature();
                row[i] = creatureFactory.createDeadCreature();
            }
        }
    }

    private AbstractCreature getCell(int x, int y) {
        return cells[y][x];
    }

    private void setCell(int x, int y, AbstractCreature newCell) {
        cells[y][x] = newCell;
    }

    public void makeRandomCellAlive() {
        //TODO: prevent duplicate placement.
        makeCellAlive(random.nextInt(GRID_ROWS -1), random.nextInt(GRID_COLUMNS -1));
    }

    public void toggleCellState(int x, int y, String selectedType) {
        AbstractCreature newCreature;
        if (cellExists(x, y)) {
            AbstractCreature cell = getCell(x, y);
            if (cell.isDead()){
                newCreature = creatureFactory.createCreature(selectedType);
            }
            else {
                newCreature = creatureFactory.createCreature("DEAD");
            }
            setCell(x,y,newCreature);
//            AbstractCreature cell = getCell(x, y);
//            if (cell.isAlive()) {
//                cell.kill();
//            } else {
//                cell.resurrect();
//            }
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
//                creature.setAliveStateBasedOnNeighbours(countAliveNeighbors(i, j));
                int status = creature.isAliveStateBasedOnNeighbours(countAliveNeighbors(i, j));
                if (status == 0){
                    setCell(i,j, creatureFactory.createDeadCreature());
                }
                else if(status == 1){
                    AbstractCreature newCreature = determineResurrectionType(i, j);
                    setCell(i,j, newCreature);
                }
            }
        }
    }

    private AbstractCreature determineResurrectionType(int x, int y){
        int defaultCount = countDefaultNeighbors(x,y);
        int explosiveCount = countExplosiveNeighbors(x,y);
        int scarcityCount = countScarcityNeighbors(x,y);

        String largestType = "DEFAULT";
        int maxCount = defaultCount;
        if (explosiveCount > maxCount) {
            maxCount = explosiveCount;
            largestType = "EXPLOSIVE";
        }
        if (scarcityCount > maxCount) {
            maxCount = scarcityCount;
            largestType = "SCARCITY";
        }
        return creatureFactory.createCreature(largestType);
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

    private int countDefaultNeighbors(int x, int y){
        int count = 0;
        if (isCellDefault(x-1, y+1)) {count++; }
        if (isCellDefault(x, y+1)) {count++; }
        if (isCellDefault(x+1, y+1)) {count++; }
        if (isCellDefault(x-1, y)) {count++; }
        if (isCellDefault(x+1, y)) {count++; }
        if (isCellDefault(x-1, y-1)) {count++; }
        if (isCellDefault(x, y-1)) {count++; }
        if (isCellDefault(x+1, y-1)) {count++; }
        return count;
    }

    private int countExplosiveNeighbors(int x, int y) {
        int count = 0;
        if (isCellExplosive(x-1, y+1)) { count++; }
        if (isCellExplosive(x,   y+1)) { count++; }
        if (isCellExplosive(x+1, y+1)) { count++; }
        if (isCellExplosive(x-1, y  )) { count++; }
        if (isCellExplosive(x+1, y  )) { count++; }
        if (isCellExplosive(x-1, y-1)) { count++; }
        if (isCellExplosive(x,   y-1)) { count++; }
        if (isCellExplosive(x+1, y-1)) { count++; }
        return count;
    }

    private int countScarcityNeighbors(int x, int y) {
        int count = 0;
        if (isCellScarcity(x-1, y+1)) { count++; }
        if (isCellScarcity(x,   y+1)) { count++; }
        if (isCellScarcity(x+1, y+1)) { count++; }
        if (isCellScarcity(x-1, y  )) { count++; }
        if (isCellScarcity(x+1, y  )) { count++; }
        if (isCellScarcity(x-1, y-1)) { count++; }
        if (isCellScarcity(x,   y-1)) { count++; }
        if (isCellScarcity(x+1, y-1)) { count++; }
        return count;
    }

    public Color getCellColor(int x, int y) { //TODO: does this break single responsibility principle?
        return getCell(x, y).getAliveColor();
//        if (isCellAlive(x, y)) {
//            return getCell(x, y).getAliveColor();
//        } else {
//            return getCell(x, y).getDeadColor();
//        }
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