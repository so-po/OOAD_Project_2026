package conwaygame.creatures;

public class DefaultCreature extends AbstractCreature {

    int getMinimumNeighbours() {
        return 2;
    }

    int getMaxNeighbours() {
        return 3;
    }

    int getResurrectionNeighbourCount() {
        return 3;
    }

}