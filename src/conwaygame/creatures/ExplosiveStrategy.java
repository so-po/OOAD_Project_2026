package conwaygame.creatures;

import java.awt.*;

public class ExplosiveStrategy extends Strategy {


    int getMinimumNeighbours() {
        return 0;
    }

    int getMaxNeighbours() {
        return 5;
    }

    int getResurrectionNeighbourCount() {
        return 1;
    }

    public Color getAliveColor() {
        return Color.YELLOW;
    }

    public Color getDeadColor() {
        return new Color(60, 60, 0);
    }

    public CreatureType getType() {
        return CreatureType.EXPLOSIVE;
    }


}
