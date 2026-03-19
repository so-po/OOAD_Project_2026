package conwaygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GridViewer extends JFrame {

    private final JLabel aliveStatus1 = new JLabel("Unknown");
    private final JLabel aliveStatus2 = new JLabel("Unknown");
    private final JButton playTurnButton = new JButton("Play a Turn");

    public GridViewer() {
        JPanel mainPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        mainPanel.add(aliveStatus1);
        mainPanel.add(aliveStatus2);
        mainPanel.add(playTurnButton);

        this.add(mainPanel);
    }

    public void addPlayTurnButtonListener(ActionListener actionListener){
        playTurnButton.addActionListener(actionListener);
    }

    public void setAliveStatus1(String aliveStatus) {
        aliveStatus1.setText(aliveStatus);
    }

    public void setAliveStatus2(String aliveStatus) {
        aliveStatus2.setText(aliveStatus);
    }


}
