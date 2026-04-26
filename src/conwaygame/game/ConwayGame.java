package conwaygame.game;

public class ConwayGame { //Facade for Game MVC

    public ConwayGame(int width, int height) throws Exception {
        GameViewer gameViewer = new GameViewer(width, height);

        GameModel grid = new GameModel.GridBuilder().setDimensions(width, height).addDefaultCreatureToLocation(0,0)
                .addDefaultCreatureToLocation(1,0).addDefaultCreatureToLocation(1,1).build();
        GameController game = new GameController(grid, gameViewer);

        gameViewer.setVisible(true);
    }

    public ConwayGame(GameModel injectedGrid) throws Exception {
        GameModel grid = injectedGrid;
        GameViewer gameViewer = new GameViewer(grid.getWidth(), grid.getHeight());
        GameController game = new GameController(grid, gameViewer);
        gameViewer.setVisible(true);
    }

}
