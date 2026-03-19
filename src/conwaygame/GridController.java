package conwaygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridController {

    Grid gridModel;
    GridViewer gridViewer;

    public GridController(Grid gridModel, GridViewer gridViewer) {
        this.gridModel = gridModel;
        this.gridViewer = gridViewer;

        this.gridViewer.addPlayTurnButtonListener(new PlayTurnListener());
    }

    class PlayTurnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (gridModel.isCellAlive(0, 0)) {
                gridViewer.setAliveStatus1("Alive");
            } else {
                gridViewer.setAliveStatus1("Dead");
            }

            if (gridModel.isCellAlive(1, 0)) {
                gridViewer.setAliveStatus2("Alive");
            } else {
                gridViewer.setAliveStatus2("Dead");
            }

            if (gridModel.isCellAlive(0, 1)) {
                gridViewer.setAliveStatus3("Alive");
            } else {
                gridViewer.setAliveStatus3("Dead");
            }

            if (gridModel.isCellAlive(1, 1)) {
                gridViewer.setAliveStatus4("Alive");
            } else {
                gridViewer.setAliveStatus4("Dead");
            }

            gridModel.playTurn();

        }
    }
}
