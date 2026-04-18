package conwaygame.creatures;

import java.awt.*;

public abstract class Strategy {

    abstract int getMinimumNeighbours();
    abstract int getMaxNeighbours();
    abstract int getResurrectionNeighbourCount();

    public boolean isDefault(){ return false; }
    public boolean isExplosive(){ return false; }
    public boolean isScarcity(){ return false; }
    public boolean isDead(){ return false; }

    abstract public Color getColor();

}
