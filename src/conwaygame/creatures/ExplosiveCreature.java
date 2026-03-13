package conwaygame.creatures;

public class ExplosiveCreature extends AbstractCreature {

    int getMinimumNeighbours() {
        return 0;
    }

    int getMaxNeighbours() {
        return 5;
    }

    int getResurrectionNeighbourCount() {
        return 1;
    }

}