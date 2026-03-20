package conwaygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    Grid gridModel;
    GameViewer view;

    public Game(Grid model, GameViewer view) { //TODO: make GamePanel & Game less tightly coupled? (like w/ an an abstract gameviewer)
        this.gridModel = model;
        this.view = view;

        this.view.addMakeCell1AliveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridModel.makeCellAlive(0, 0);
                view.makeCell1Alive();
            }
        });
    }

}
