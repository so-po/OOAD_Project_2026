package conwaygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {

    Grid gridModel;
    GameViewer view;

    public GameController(Grid model, GameViewer view) { //TODO: make GamePanel & Game less tightly coupled? (like w/ an an abstract gameviewer)
        this.gridModel = model;
        this.view = view;

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

        this.view.addPlayTurnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridModel.playTurn();
                view.setCellState(0, 0, gridModel.isCellAlive(0, 0));
                view.setCellState(0, 1, gridModel.isCellAlive(0, 1));
                view.setCellState(1, 0, gridModel.isCellAlive(1, 0));
                view.setCellState(1, 1, gridModel.isCellAlive(1, 1));

            }
        });
    }

}
