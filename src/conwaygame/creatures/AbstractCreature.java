package conwaygame.creatures;

import java.awt.Color;

public abstract class AbstractCreature {
    //TEMPLATE PATTERN
    public final int isAliveStateBasedOnNeighbours(int numberOfNeighbours) {
        //Kill or resurrect the cell given its number of neighbours
        if (!isDead() && (numberOfNeighbours < getMinimumNeighbours() || numberOfNeighbours > getMaxNeighbours())) {
            return 0; //kill
        } else if (numberOfNeighbours == getResurrectionNeighbourCount()) {
            return 1; //revive
        }
        return -1; //do nothing
    }

    abstract int getMinimumNeighbours();
    abstract int getMaxNeighbours();
    abstract int getResurrectionNeighbourCount();

    public abstract Color getColor();

    public boolean isDefault(){ return false; }
    public boolean isExplosive(){ return false; }
    public boolean isScarcity(){ return false; }
    public boolean isDead(){ return false; }
}