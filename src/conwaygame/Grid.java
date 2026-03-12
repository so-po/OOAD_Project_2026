package conwaygame;

import conwaygame.creatures.AbstractCreature;

import java.util.List;

public class Grid {

    AbstractCreature[][] cells;

    public Grid(int width, int height) {
        cells = new AbstractCreature[width][height]; //TODO: check if this is right.

        for (AbstractCreature[] row : cells) { //initialize list to be full of nulls
            for (AbstractCreature creature : row) {
                creature = null;
            }
        }

    }


}