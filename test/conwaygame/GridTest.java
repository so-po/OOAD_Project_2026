package conwaygame;
import org.junit.jupiter.api.Test;

public class GridTest {

    @Test
    public void testGrid() throws Exception {
        //just fail for now. inspect it manually w/ the debugger to see if it's working
        Grid grid = new Grid(3, 3);
        //grid.makeRandomCellAlive();
        grid.makeCellAlive(2, 2);
        grid.makeCellAlive(1, 2);
        grid.makeCellAlive(1, 1);
        grid.playTurn(); //this should make a fourth cell alive, in a square shape
        grid.playTurn(); //this should stay as a square shape
        assert(false);
    }

}
