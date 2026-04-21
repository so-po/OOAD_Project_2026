package conwaygame.creatures;

import java.awt.*;

public class DefaultStrategy extends Strategy {

    int getMinimumNeighbours() { return 2; }

    int getMaxNeighbours() { return 3; }

    int getResurrectionNeighbourCount() { return 3; }


    @Override
    public Color getAliveColor() {
        return Color.GREEN;
    }

    @Override
    public Color getDeadColor() {
        return new Color(25, 50, 0);
    }

    public CreatureType getType() {
        return CreatureType.DEFAULT;
    }

}
