package conwaygame.Game;

import org.junit.jupiter.api.Test;


public class CreatureResurrectionTypeTest {

    @Test
    public void testDefaultBirth() throws Exception{
        Grid grid = new Grid(3, 3);
        grid.toggleCellState(2, 2, "DEFAULT");
        grid.toggleCellState(1, 2, "DEFAULT");
        grid.toggleCellState(2, 1, "EXPLOSIVE");
        grid.playTurn(); //cell(1,1) should turn into a default cell after turn is played
        assert(grid.isCellAlive(1, 1));
        assert(grid.isCellDefault(1, 1));
    }

    @Test
    public void testExplosiveBirth() throws Exception{
        Grid grid = new Grid(3, 3);
        grid.toggleCellState(2, 2, "EXPLOSIVE");
        grid.toggleCellState(1, 2, "EXPLOSIVE");
        grid.toggleCellState(2, 1, "DEFAULT");
        grid.playTurn(); //cell(1,1) should turn into an explosive cell after turn is played
        assert(grid.isCellAlive(1, 1));
        assert(grid.isCellExplosive(1, 1));
    }
}
