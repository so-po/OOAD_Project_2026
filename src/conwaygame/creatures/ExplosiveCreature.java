package conwaygame.creatures;

public class ExplosiveCreature extends AbstractCreature {

    public int getMinimumNeighbours() {
        return 0;
    }

    public int getMaxNeighbours() {
        return 5;
    }

    public int getResurrectionNeighbourCount() {
        return 1;
    }

}