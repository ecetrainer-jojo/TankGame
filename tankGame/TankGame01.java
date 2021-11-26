package tankGame;

import javax.swing.*;
//The main of the game
public class TankGame01 extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame01 gameStart = new TankGame01();
    }

    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
