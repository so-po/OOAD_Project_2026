
public abstract class AbstractCreature {
    //TEMPLATE PATTERN
    private boolean isAlive = false;

    public final boolean willBeAlive(int numberOfNeighbours) {
        //Return whether the cell is alive or not given its number of neighbours
        if (isAlive) {
            return (numberOfNeighbours < getMinimumNeighbours() || numberOfNeighbours > getMaxNeighbors());
        } else {
            return (numberOfNeighbours == getRessurectionNeighbours());
        }
    }

    public void kill() {
        this.isAlive = false;
    }

    public void ressurect() {
        this.isAlive = true;
    }

    public abstract int getMinimumNeighbours();
    public abstract int getMaxNeighbors();
    public abstract int getRessurectionNeighbours();

}