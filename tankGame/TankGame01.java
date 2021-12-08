package tankGame;

import javax.swing.*;
import java.io.IOException;

//The main of the game
public class TankGame01 extends JFrame {
    static MyPanel mp = null;
    public static final int REFRESH_DURATION = 500;

    public static void main(String[] args) throws InterruptedException, IOException {
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
            else if(mp.gamePaused){
                refresh();
                saveGameStatus();
                break;
            }
        }

        System.exit(0);


    }

    public static void saveGameStatus(){
        if(mp.gameOver==false){
            try {
                mp.resumeGame.saveLog(mp.enemies,mp.hero,mp.myScoreBoard);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public TankGame01() throws IOException {
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
