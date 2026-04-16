package conwaygame.creatures;

import java.awt.*;

public class ScarcityCreature extends AbstractCreature {

    int getMinimumNeighbours() {
        return 1;
    }

    int getMaxNeighbours() {
        return 2;
    }

    int getResurrectionNeighbourCount() { return 1; }

    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public boolean isScarcity() { return true; }
}