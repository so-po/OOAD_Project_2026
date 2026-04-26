package conwaygame.Game;

import org.junit.jupiter.api.Test;

public class GridTest {

//    Grid grid = new Grid.GridBuilder().setDimensions(width, height).addDefaultCreatureToLocation(0,0)
//            .addDefaultCreatureToLocation(1,0).addDefaultCreatureToLocation(1,1).build();


    @Test
    public void testBuilderCreatureSpawner() throws Exception{
        Grid grid = new Grid.GridBuilder().setDimensions(4, 4)
                .addDefaultCreatureToLocation(0,0)
                .addExplosiveCreatureToLocation(1,0)
                .addScarcityCreatureToLocation(0,1)
                .build();
        ConwayGame conwayGame = new ConwayGame(grid);
        assert(grid.isCellDefault(0, 0));
        assert(grid.isCellExplosive(1, 0));
        assert(grid.isCellScarcity(0, 1));
    }
}
