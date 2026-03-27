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
                gridModel.toggleCellState(x, y);
                if (gridModel.isCellAlive(x, y)) {
                    view.setCellState(x, y, true);
                } else {
                    view.setCellState(x, y, false);
                }
            }
        });

        this.view.addPauseUnpauseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runGameTask.togglePaused();
            }
        });
    }

    class RunGameTask extends Thread {

        boolean paused = true;

        public void run() {
            while (true) { //TODO: is this a problem? need to kill this thread?
                System.out.println(paused); //TODO: why the heck does this code only work when I'm printing this out????!
                while(!paused) {
//                    System.out.println("Thread is running with: " +
//                            Thread.currentThread().getName() + " " + paused);
                    gridModel.playTurn();
                    view.setCellState(0, 0, gridModel.isCellAlive(0, 0));
                    view.setCellState(0, 1, gridModel.isCellAlive(0, 1));
                    view.setCellState(1, 0, gridModel.isCellAlive(1, 0));
                    view.setCellState(1, 1, gridModel.isCellAlive(1, 1));
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        //just ignore the exception instead of:
                        //throw new RuntimeException(e);
                    }
                }
            }
        }

        public void togglePaused() {
            paused = !paused;
            System.out.println("Am I paused? = " + paused);
        }

    }


}
