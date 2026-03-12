package conwaygame.creatures;

public class ScarcityCreature extends AbstractCreature {

    public int getMinimumNeighbours() {
        return 1;
    }

    public int getMaxNeighbours() {
        return 2;
    }

    public int getResurrectionNeighbourCount() {
        return 1;
    }

}