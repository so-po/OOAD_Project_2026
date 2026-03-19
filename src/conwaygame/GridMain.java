package conwaygame;

import javax.swing.*;

public class GridMain {
    public static void main(String[] args) throws Exception {
        //adapted from https://web.archive.org/web/20130224053924/https://www.newthinktank.com/2013/02/mvc-java-tutorial/

        //make two hardcoded creatures in 2x1 grid and monitor if they're alive or not, turn by turn

        /*
        model view controller reference:
        - https://www.geeksforgeeks.org/system-design/mvc-design-pattern/
        - https://web.archive.org/web/20130224053924/https://www.newthinktank.com/2013/02/mvc-java-tutorial/
        - https://www.youtube.com/watch?v=dTVVa2gfht8
        */

        GridViewer gridViewer = new GridViewer();
        Grid grid = new Grid(2, 1);
        grid.makeCellAlive(0, 0);
        grid.makeCellAlive(1, 0);
        GridController gridController = new GridController(grid, gridViewer);
        gridViewer.setVisible(true);

    }

}