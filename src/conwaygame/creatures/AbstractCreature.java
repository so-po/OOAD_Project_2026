package conwaygame.creatures;

import java.awt.Color;

public abstract class AbstractCreature {
    //TEMPLATE PATTERN
    private boolean isAlive = false;

    public final void setAliveStateBasedOnNeighbours(int numberOfNeighbours) {
        //Kill or resurrect the cell given its number of neighbours
        if (isAlive && (numberOfNeighbours < getMinimumNeighbours() || numberOfNeighbours > getMaxNeighbours())) {
                this.kill();

        } else if (numberOfNeighbours == getResurrectionNeighbourCount()) {
            this.resurrect();
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        this.isAlive = false;
    }

    public void resurrect() {
        this.isAlive = true;
    }

    abstract int getMinimumNeighbours();
    abstract int getMaxNeighbours();
    abstract int getResurrectionNeighbourCount();

    public Color getDeadColor() {
        return Color.RED;
    }

    public abstract Color getAliveColor();
}