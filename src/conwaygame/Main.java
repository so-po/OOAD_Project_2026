package conwaygame;

public class Main {

    public static void main(String[] args) throws Exception {

        GameViewer gameViewer = new GameViewer();
        Grid grid = new Grid(2, 2);
        Game game = new Game(grid, gameViewer);

        gameViewer.play();

    }
}
