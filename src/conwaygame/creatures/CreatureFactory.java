package conwaygame.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CreatureFactory {

    public AbstractCreature createCreature(String type){
        switch (type){
            case "DEFAULT": return new DefaultCreature();
            case "EXPLOSIVE": return new ExplosiveCreature();
            case "SCARCITY": return new ScarcityCreature();
            case "DEAD": return new DeadCreature();
            default: return new DeadCreature();
        }
    }

    public AbstractCreature createDeadCreature() {return new DeadCreature();}
    public AbstractCreature createDefaultCreature() {return new DefaultCreature();}
    public AbstractCreature createExplosiveCreature() {return new ExplosiveCreature();}
    public AbstractCreature createScarcityCreature() {return new ScarcityCreature();}

    public List<AbstractCreature> getDefaultCreatures(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(_ -> createDefaultCreature())
                .toList();
    }

}
