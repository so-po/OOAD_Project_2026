package conwaygame.Game;

import conwaygame.creatures.CreatureFactory;

public class ConwayGame { //Facade for Game MVC

    public ConwayGame(int width, int height) throws Exception {
        GameViewer gameViewer = new GameViewer(width, height);
        Grid grid = new Grid(width, height, new CreatureFactory());
        GameController game = new GameController(grid, gameViewer);

        gameViewer.setVisible(true);
    }

}
