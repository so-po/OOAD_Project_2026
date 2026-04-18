package conwaygame.Game;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class CreatureRessurectionTypeTest {

    @Disabled
    public void testBirth() throws Exception{

        //TODO
        //Cells should turn into
        Grid grid = new Grid(3, 3);
        grid.toggleCellState(2, 2, "DEFAULT");
        grid.toggleCellState(1, 2, "DEFAULT");
        grid.toggleCellState(2, 1, "EXPLOSIVE");
        grid.playTurn(); //cell(1,1) should turn into a default cell after turn is played
        assert(grid.isCellAlive(1, 1));
        //assert(grid);
    }
}
