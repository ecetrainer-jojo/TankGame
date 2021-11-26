package EventHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ballMove extends JFrame {
    MyPanel p = null;
    public static void main(String[] args) {
        new ballMove();
    }

    public ballMove(){
        p = new MyPanel();
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        //allow JFrame to listen to the keyboard events
        this.addKeyListener(p);
        this.setVisible(true);
    }

}

class MyPanel extends JPanel implements KeyListener {
    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.PINK);
        g.fillOval(x,y,50,50);
        System.out.println("coord: "+ "(" +x + ","+y+")");
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //according to the different insertion, make the ball move
        if(e.getKeyCode()==KeyEvent.VK_UP){
            y=Math.max(0,y-20);
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            y=Math.min(525,y+20);
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            x=Math.max(0,x-20);
        }
        else{
            x=Math.min(550,x+20);
        }
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
