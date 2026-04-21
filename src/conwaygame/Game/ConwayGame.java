package conwaygame.Game;

public class ConwayGame { //Facade for Game MVC

    public ConwayGame(int width, int height) throws Exception {
        GameViewer gameViewer = new GameViewer(width, height);

        Grid grid = new Grid.GridBuilder().setDimensions(width, height).addDefaultCreatureToLocation(0,0)
                .addDefaultCreatureToLocation(1,0).addDefaultCreatureToLocation(1,1).build();
        GameController game = new GameController(grid, gameViewer);

        gameViewer.setVisible(true);
    }

}
