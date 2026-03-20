package conwaygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameViewer extends JFrame {

    GamePanel gamePanel;
    JButton makeCell1AliveButton = new JButton("make cell 1 alive");

    public GameViewer() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Conway's Game of Life");

        JPanel mainPanel = new JPanel();
        gamePanel = new GamePanel();
        mainPanel.add(gamePanel);
        mainPanel.add(makeCell1AliveButton);
        this.add(mainPanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addMakeCell1AliveListener(ActionListener actionListener){
        makeCell1AliveButton.addActionListener(actionListener);
    }

    public void play() {
        this.setVisible(true);
        gamePanel.startGameThread();

    }

    public void makeCell1Alive() {
        this.gamePanel.cell1Alive = true;
    }


    public class GamePanel extends JPanel implements Runnable{

        //SCREEN SETTINGS
        final int originalTileSize = 16; //16x16 sprite size
        final int scale = 3;

        final int tileSize = originalTileSize * scale;
        final int maxScreenCol = 16;
        final int maxScreenRow = 12;
        final int screenWidth = tileSize * maxScreenCol;
        final int screenHeight = tileSize * maxScreenRow;
        boolean cell1Alive = false;

        Thread gameThread;

        public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
        }

        public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public void run() {
            while (gameThread != null){
                //System.out.println("The game loop is running");
                //UPDATE
                update();
                //DRAW
                repaint();
            }
        }

        public void update(){

        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);

            int origin = 100;
            Graphics g2 = (Graphics2D)g;

            g2.setColor(Color.red);

            if (cell1Alive) {
                g2.setColor(Color.green);
            }

            g2.fillRect(origin,origin,tileSize, tileSize);

            g2.setColor(Color.red);

            g2.fillRect(origin+tileSize,origin,tileSize, tileSize);

            g2.fillRect(origin,origin+tileSize,tileSize, tileSize);

            g2.fillRect(origin+tileSize,origin+tileSize,tileSize, tileSize);

            g2.dispose();
        }


    }


}