package conwaygame.creatures;

import java.awt.Color;

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

    public Color getAliveColor() {
        return Color.GREEN;
    }
}