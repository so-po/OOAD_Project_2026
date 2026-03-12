package conwaygame.creatures;

public class DefaultCreature extends AbstractCreature {

    public int getMinimumNeighbours() {
        return 2;
    }

    public int getMaxNeighbours() {
        return 3;
    }

    public int getResurrectionNeighbourCount() {
        return 3;
    }

}