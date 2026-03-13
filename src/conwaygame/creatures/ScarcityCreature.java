package conwaygame.creatures;

public class ScarcityCreature extends AbstractCreature {

    int getMinimumNeighbours() {
        return 1;
    }

    int getMaxNeighbours() {
        return 2;
    }

    int getResurrectionNeighbourCount() {
        return 1;
    }

}