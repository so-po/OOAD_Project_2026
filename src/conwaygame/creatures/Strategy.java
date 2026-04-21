package conwaygame.creatures;
import java.awt.*;

import static conwaygame.creatures.CreatureType.*;

public abstract class Strategy {

    abstract int getMinimumNeighbours();
    abstract int getMaxNeighbours();
    abstract int getResurrectionNeighbourCount();

    public abstract CreatureType getType();
    public boolean isDefault(){ return getType() == DEFAULT; }
    public boolean isExplosive(){ return getType() == EXPLOSIVE; }
    public boolean isScarcity(){ return getType() == SCARCITY; }

    abstract public Color getAliveColor();
    abstract public Color getDeadColor();

}
