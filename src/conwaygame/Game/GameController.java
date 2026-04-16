package conwaygame.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class GameController {
    Grid gridModel;
    GameViewer view;
    RunGameTask runGameTask;

    public GameController(Grid model, GameViewer view) { //TODO: make GamePanel & Game less tightly coupled? (like w/ an an abstract gameviewer)
        this.gridModel = model;
        this.view = view;
        this.runGameTask = new RunGameTask();
        runGameTask.start();
        playOneTurnAndUpdateView(); //plays one turn to load in the grid

        this.view.setDefaultCreatureListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                runGameTask.setCreatureType("DEFAULT");
            }
        });

        this.view.setExplosiveCreatureListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                runGameTask.setCreatureType("EXPLOSIVE");
            }
        });

        this.view.setScarcityCreatureListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                runGameTask.setCreatureType("SCARCITY");
            }
        });

        this.view.toggleCellStateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x, y;
                try {
                    x = Integer.parseInt(view.xCoordInput.getText());
                    y = Integer.parseInt(view.yCoordInput.getText());
                } catch (NumberFormatException ex) {
                    return; //just ignore incorrect input for now
                }
                gridModel.toggleCellState(x, y, runGameTask.getCreatureType());
                updateCellColor(x, y);
            }
        });

        this.view.addPauseUnpauseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runGameTask.togglePaused();
            }
        });
    }

    private void updateCellColor(int x, int y) {
        view.setCellColor(x, y, gridModel.getCellColor(x, y));
    }

    private void playOneTurnAndUpdateView() {
        gridModel.playTurn();
        for (int x = 0; x < gridModel.GRID_COLUMNS; x++) {
            for (int y = 0; y < gridModel.GRID_ROWS; y++) {
                updateCellColor(x, y);
            }
        }
    }

    class RunGameTask extends Thread {
        boolean paused = true;
        String selectedType = "DEFAULT";

        public void run() {

            while (true) { //TODO: is this a problem? need to kill this thread?
                view.gamePausedLabel.setText("Game Paused");
                view.currentlySelectedCreatureLabel.setText("Selected: " + selectedType);
                while(!paused) {
                    view.gamePausedLabel.setText("Game Unpaused");
                    playOneTurnAndUpdateView();
                    try {
                        sleep(500);
                        view.currentlySelectedCreatureLabel.setText("Selected: " + selectedType);
                    } catch (InterruptedException e) {
                        //just ignore this exception instead of throwing an exception like:
                        //throw new RuntimeException(e);
                    }
                }
            }
        }

        public void togglePaused() { paused = !paused; }
        public void setCreatureType(String type) { selectedType = type; }
        public String getCreatureType() { return selectedType; }

    }


}
