package tankGame;

import javax.swing.*;
//The main of the game
public class TankGame01 extends JFrame {
    static MyPanel mp = null;
    public static void main(String[] args) throws InterruptedException {
        TankGame01 gameStart = new TankGame01();
        while(true){
            Thread.sleep(5);
            refresh();
            if(mp.isOver()){
                System.out.println("game over");
                refresh();
                Thread.sleep(500);
                break;
            }
        }
        System.exit(0);


    }

    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void refresh(){
        mp.repaint();
    }
}
