package conwaygame.game;

import org.junit.jupiter.api.Test;

public class ModelBuilderTest {

    @Test
    public void testBuilderCreatureSpawner() throws Exception{
        GameModel model = GameModel.getNewBuilder().setDimensions(4, 4)
                .addDefaultCreatureToLocation(0,0)
                .addExplosiveCreatureToLocation(1,0)
                .addScarcityCreatureToLocation(0,1)
                .build();
        ConwayGame conwayGame = new ConwayGame(model);
        assert(model.isCellDefault(0, 0));
        assert(model.isCellExplosive(1, 0));
        assert(model.isCellScarcity(0, 1));
    }
}
