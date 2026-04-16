package conwaygame.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameViewer extends JFrame {

    GamePanel gamePanel;
    JLabel xCoordLabel = new JLabel("x: ");
    JTextField xCoordInput = new JTextField("");
    JLabel yCoordLabel = new JLabel("y: ");
    JTextField yCoordInput = new JTextField("");
    JButton toggleCellStateButton = new JButton("toggle cell state (alive/dead)");
    JButton pauseUnpauseButton = new JButton("pause/unpause");
    JLabel gamePausedLabel = new JLabel("");
    JLabel currentlySelectedCreatureLabel = new JLabel("Selected: explosive");
    JButton setDefaultCreature = new JButton("Default");
    JButton setExplosiveCreature = new JButton("Explosive");
    JButton setScarcityCreature = new JButton("Scarcity");
    Color[][] cellColors;

    public GameViewer(int width, int height) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Conway's Game of Life");

        JPanel mainPanel = new JPanel();
        gamePanel = new GamePanel(width, height);
        JPanel controlPanel = new JPanel(new GridLayout(8, 2));
        mainPanel.add(gamePanel);
        controlPanel.add(gamePausedLabel);
        controlPanel.add(pauseUnpauseButton);
        controlPanel.add(xCoordLabel);
        controlPanel.add(xCoordInput);
        controlPanel.add(yCoordLabel);
        controlPanel.add(yCoordInput);
        controlPanel.add(toggleCellStateButton);


        controlPanel.add(new JLabel(""));
        controlPanel.add(currentlySelectedCreatureLabel);
//        controlPanel.add(currentlySelectedCreatureType);
        controlPanel.add(new JLabel(""));
        controlPanel.add(setDefaultCreature);
        controlPanel.add(setExplosiveCreature);
        controlPanel.add(setScarcityCreature);

        controlPanel.add(new JLabel("")); //this is just here for spacing

        mainPanel.add(controlPanel);
        cellColors = new Color[height][width];
        this.add(mainPanel);
        this.pack();

        this.setLocationRelativeTo(null);
    }

    public void setDefaultCreatureListener(ActionListener actionListener){
        setDefaultCreature.addActionListener(actionListener);
    }

    public void setExplosiveCreatureListener(ActionListener actionListener){
        setExplosiveCreature.addActionListener(actionListener);
    }

    public void setScarcityCreatureListener(ActionListener actionListener){
        setScarcityCreature.addActionListener(actionListener);
    }

    public void toggleCellStateListener(ActionListener actionListener){
        toggleCellStateButton.addActionListener(actionListener);
    }

    public void addPauseUnpauseListener(ActionListener actionListener) {
        pauseUnpauseButton.addActionListener(actionListener);
    }

    public void setCellColor(int x, int y, Color color) {
        this.cellColors[y][x] = color;
        this.repaint();
    }

    class GamePanel extends JPanel{

        //SCREEN SETTINGS
        //TODO: clean this up
        final int originalTileSize = 16; //16x16 sprite size
        final int scale = 3;

        final int tileSize = originalTileSize * scale;
        final int maxScreenCol = 16;
        final int maxScreenRow = 12;
        final int screenWidth = tileSize * maxScreenCol;
        final int screenHeight = tileSize * maxScreenRow;
        final int numberOfHorizontalCells;
        final int numberOfVerticalCells;

        public GamePanel(int numberOfHorizontalCells, int numberOfVerticalCells){
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.numberOfHorizontalCells = numberOfHorizontalCells;
            this.numberOfVerticalCells = numberOfHorizontalCells;
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);

            int origin = 100;
            Graphics g2 = (Graphics2D)g;

            for (int x = 0; x < numberOfHorizontalCells; x++) {
                for (int y = 0; y < numberOfVerticalCells; y++) {
                    g2.setColor(Color.BLACK);
                    g2.drawRect(origin+tileSize*x, origin+tileSize*y, tileSize, tileSize);
                    g2.setColor(cellColors[y][x]);
                    g2.fillRect(origin+tileSize*x, origin+tileSize*y, tileSize, tileSize);
                }

            }

            g2.dispose();
        }


    }


}