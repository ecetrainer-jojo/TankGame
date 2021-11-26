import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame{
    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }

    public DrawCircle(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(10,10,100,200);
        g.fillRect(10,10,100,100);
        g.setFont(new Font("", Font.ITALIC, 70));
        g.drawString("LOL", 300,300);
    }
}
