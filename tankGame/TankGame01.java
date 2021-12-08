package tankGame;

import javax.swing.*;
//The main of the game
public class TankGame01 extends JFrame {
    static MyPanel mp = null;
    public static final int REFRESH_DURATION = 500;

    public static void main(String[] args) throws InterruptedException {
        TankGame01 gameStart = new TankGame01();
        while(true){
            Thread.sleep(20);
            refresh();
            if(mp.gameOver){
                System.out.println("game over");
                refresh();
                Thread.sleep(REFRESH_DURATION);
                break;
            }
        }
        System.exit(0);


    }

    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);
        this.addKeyListener(mp);
        this.addMouseListener(mp);
        this.setSize(MyPanel.BACKGROUND_X + MyPanel.SIDE_X,MyPanel.BACKGROUND_Y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void refresh(){
        mp.repaint();
    }
}
