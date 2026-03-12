package conwaygame.creatures;

public abstract class AbstractCreature {
    //TEMPLATE PATTERN
    private boolean isAlive = false;

    public final boolean willBeAlive(int numberOfNeighbours) {
        //Return whether the cell is alive or not given its number of neighbours
        if (isAlive) {
            return (numberOfNeighbours < getMinimumNeighbours() || numberOfNeighbours > getMaxNeighbours());
        } else {
            return (numberOfNeighbours == getResurrectionNeighbourCount());
        }
    }

    public void kill() {
        this.isAlive = false;
    }

    public void resurrect() {
        this.isAlive = true;
    }

    public abstract int getMinimumNeighbours();
    public abstract int getMaxNeighbours();
    public abstract int getResurrectionNeighbourCount();

}