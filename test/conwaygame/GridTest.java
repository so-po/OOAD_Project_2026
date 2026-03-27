package conwaygame;
import conwaygame.Game.Grid;
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

    //Test default creature rules:
    //Rule 1: Any live cell with fewer than two live neighbours dies, as if by underpopulation.
    @Test
    public void testUnderpopulation() throws Exception{
        Grid grid = new Grid(3, 3);
        grid.makeCellAlive(2, 2);
        grid.makeCellAlive(1, 2);
        grid.playTurn(); //both cells should die
        assert(!grid.isCellAlive(2, 2));
        assert(!grid.isCellAlive(1, 2));
    }
    //Rule 2: Any live cell with two or three live neighbours lives on to the next generation.
    @Test
    public void testLiveToNextGen() throws Exception{
        Grid grid = new Grid(3, 3);
        grid.makeCellAlive(2, 2);
        grid.makeCellAlive(1, 2);
        grid.makeCellAlive(2, 1);
        grid.playTurn(); //all made cells should be alive after turn
        assert(grid.isCellAlive(2, 2));
        assert(grid.isCellAlive(1, 2));
        assert(grid.isCellAlive(2, 1));
    }
    //Rule 3: Any live cell with more than three live neighbours dies, as if by overpopulation.
    @Test
    public void testOverpopulation() throws Exception{
        Grid grid = new Grid(3, 3);
        grid.makeCellAlive(1, 1);//this cell is surrounded by 4 neighbors
        grid.makeCellAlive(0, 1);
        grid.makeCellAlive(1, 0);
        grid.makeCellAlive(1, 2);
        grid.makeCellAlive(2, 1);
        grid.playTurn(); //cell(2,2) should die after turn is played
        grid.playTurn();
        assert(!grid.isCellAlive(1, 1));
    }
    //Rule 4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    @Test
    public void testBirth() throws Exception{
        Grid grid = new Grid(3, 3);
        grid.makeCellAlive(2, 2);
        grid.makeCellAlive(1, 2);
        grid.makeCellAlive(2, 1);
        grid.playTurn(); //cell(1,1) should be born after turn is played
        assert(grid.isCellAlive(1, 1));
    }
}
