package conwaygame.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CreatureFactory {


    public AbstractCreature createDefaultCreature() {
        return new DefaultCreature();
    }

    public List<AbstractCreature> getDefaultCreatures(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(_ -> createDefaultCreature())
                .toList();
    }

}
