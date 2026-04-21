package conwaygame.creatures;

import java.awt.*;

public class ScarcityStrategy extends Strategy {

    int getMinimumNeighbours() {
        return 1;
    }

    int getMaxNeighbours() {
        return 2;
    }

    int getResurrectionNeighbourCount() { return 1; }

    public Color getAliveColor() {
        return Color.BLUE;
    }

    public Color getDeadColor() {
        return new Color(0, 25, 50);
    }

    public CreatureType getType() {
        return CreatureType.SCARCITY;
    }

}
