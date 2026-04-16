package conwaygame.creatures;

import java.awt.*;

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

    public Color getColor() {
        return Color.ORANGE;
    }

    @Override
    public boolean isExplosive() { return true; }
}