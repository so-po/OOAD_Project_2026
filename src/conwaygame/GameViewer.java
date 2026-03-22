package conwaygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameViewer extends JFrame {

    GamePanel gamePanel;
    JLabel xCoordLabel = new JLabel("x: ");
    JTextField xCoordInput = new JTextField();
    JLabel yCoordLabel = new JLabel("y: ");
    JTextField yCoordInput = new JTextField();
    JButton toggleCellStateButton = new JButton("toggle cell state (alive/dead)");
    JButton playTurnButton = new JButton("play a turn");

    public GameViewer() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Conway's Game of Life");

        JPanel mainPanel = new JPanel();
        gamePanel = new GamePanel();
        mainPanel.add(gamePanel);
        mainPanel.add(xCoordLabel);
        mainPanel.add(xCoordInput);
        mainPanel.add(yCoordLabel);
        mainPanel.add(yCoordInput);
        mainPanel.add(toggleCellStateButton);
        mainPanel.add(playTurnButton);
        this.add(mainPanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void toggleCellStateListener(ActionListener actionListener){
        toggleCellStateButton.addActionListener(actionListener);
    }

    public void addPlayTurnListener(ActionListener actionListener) {
        playTurnButton.addActionListener(actionListener);
    }

    public void setCellState(int x, int y, boolean alive) {
        if (x == 0 && y == 0) { //not my finest moment
            this.gamePanel.cell1Alive = alive;
        } else if (x==1 && y == 0) {
            this.gamePanel.cell2Alive = alive;
        } else if (x==0 && y ==1) {
            this.gamePanel.cell3Alive = alive;
        } else if (x ==1 && y==1) {
            this.gamePanel.cell4Alive = alive;
        }
        this.repaint();
    }



    class GamePanel extends JPanel{

        //SCREEN SETTINGS
        final int originalTileSize = 16; //16x16 sprite size
        final int scale = 3;

        final int tileSize = originalTileSize * scale;
        final int maxScreenCol = 16;
        final int maxScreenRow = 12;
        final int screenWidth = tileSize * maxScreenCol;
        final int screenHeight = tileSize * maxScreenRow;
        boolean cell1Alive = false;
        boolean cell2Alive = false;
        boolean cell3Alive = false;
        boolean cell4Alive = false;

        Thread gameThread;

        public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);

            int origin = 100;
            Graphics g2 = (Graphics2D)g;


            if (cell1Alive) {
                g2.setColor(Color.green);
            } else {
                g2.setColor(Color.red);
            }

            g2.fillRect(origin,origin,tileSize, tileSize);

            if (cell2Alive) {
                g2.setColor(Color.green);
            } else {
                g2.setColor(Color.red);
            }

            g2.fillRect(origin+tileSize,origin,tileSize, tileSize);

            if (cell3Alive) {
                g2.setColor(Color.green);
            } else {
                g2.setColor(Color.red);
            }

            g2.fillRect(origin,origin+tileSize,tileSize, tileSize);

            if (cell4Alive) {
                g2.setColor(Color.green);
            } else {
                g2.setColor(Color.red);
            }

            g2.fillRect(origin+tileSize,origin+tileSize,tileSize, tileSize);

            g2.dispose();
        }


    }


}