package tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

//Tank game graphing area
public class MyPanel extends JPanel implements KeyListener {
    Hero hero = null;
    //make a default tanks (support multi-thread)
    Vector<Tank> enemies = new Vector<>();
    int enemySize = 3;
    public MyPanel(){
        //Initialize the User
        hero = new Hero(500,600,0,0); //default initialization of tank
        //Initialize the enemy
        for(int i=0; i<enemySize;i++){
            enemies.add(new EnemyTank(450+100*i,100,1,1));
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750); //This is the background of tank default black

        //Draw the tanks here
        drawTank(hero,g);
        for(int i=0; i<enemySize;i++){
            drawTank(enemies.get(i),g);
        }

        if(hero.checkBullet()){
            drawBullet(hero,g);
        }

    }

    //Method to draw the tank
    public void drawTank(Tank myTank, Graphics g){
        switch(myTank.getType()){
            case 0: //our tank
                g.setColor(Color.CYAN);
                break;

            case 1:
                g.setColor(Color.orange);
                break;
        }

        //according to the direction of the tank draw the tank
        switch (myTank.getDirect()){

            case 0: //when bomb points to the top
                g.fill3DRect(myTank.getX(),myTank.getY(),10,60,false);
                g.fill3DRect(myTank.getX()+10,myTank.getY()+10,20,40,false);
                g.fill3DRect(myTank.getX()+30,myTank.getY(),10,60,false);
                g.fillOval(myTank.getX()+10,myTank.getY()+20,20,20);
                g.drawLine(myTank.getX()+20,myTank.getY()+20,myTank.getX()+20,myTank.getY());
                break;

            case 1: //when the bomb points to the bottom
                g.fill3DRect(myTank.getX(),myTank.getY(),10,60,false);
                g.fill3DRect(myTank.getX()+10,myTank.getY()+10,20,40,false);
                g.fill3DRect(myTank.getX()+30,myTank.getY(),10,60,false);
                g.fillOval(myTank.getX()+10,myTank.getY()+20,20,20);
                g.drawLine(myTank.getX()+20,myTank.getY()+20,myTank.getX()+20,myTank.getY()+60);
                break;

            case 2://when the bomb points to the left
                g.fill3DRect(myTank.getX(),myTank.getY(),60,10,false);
                g.fill3DRect(myTank.getX()+10,myTank.getY()+10,40,20,false);
                g.fill3DRect(myTank.getX(),myTank.getY()+30,60,10,false);
                g.fillOval(myTank.getX()+20,myTank.getY()+10,20,20);
                g.drawLine(myTank.getX()+20,myTank.getY()+20,myTank.getX(),myTank.getY()+20);
                break;

            case 3://when the bomb points to the right
                g.fill3DRect(myTank.getX(),myTank.getY(),60,10,false);
                g.fill3DRect(myTank.getX()+10,myTank.getY()+10,40,20,false);
                g.fill3DRect(myTank.getX(),myTank.getY()+30,60,10,false);
                g.fillOval(myTank.getX()+20,myTank.getY()+10,20,20);
                g.drawLine(myTank.getX()+20,myTank.getY()+20,myTank.getX()+60,myTank.getY()+20);
                break;

            default:
                System.out.println("Temporarly not proceed");
        }

    }

    //Method to draw bullet
    public void drawBullet(Tank myTank, Graphics g){
        Vector<Bullet> bullets = myTank.getBullets();
        g.setColor(Color.RED);
        Iterator<Bullet> it = bullets.iterator();
        while(it.hasNext()){
            Bullet b = it.next();
            if(b.getState()==Thread.State.TERMINATED){
                it.remove();
                System.out.println("bullet dead");
            }
            else{
                System.out.println("start drawing bullets hahahaha");
                System.out.println("Bullet"+ b.getxCoordinate() +","+b.getyCoordinate());
                if(b.getDirection()==0 || b.getDirection()==1){
                    g.fillOval(b.getxCoordinate(),b.getyCoordinate(),10,20);
                }
                else{
                    g.fillOval(b.getxCoordinate(),b.getyCoordinate(),20,10);
                }
                if(b.getState()==Thread.State.NEW){
                    b.start();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing here
    }

    //when we pressed WASD to move it towards different ways
    //It cost one pressing to change the direction
    @Override
    public void keyPressed(KeyEvent e) {
        //Here is the direction key
        if(e.getKeyChar()=='w'){
            if(hero.getDirect()!=0) hero.setDirect(0);
            else hero.setY(Math.max(hero.getY()-20,20));
        }
        else if(e.getKeyChar()=='s'){
            if(hero.getDirect()!=1) hero.setDirect(1);
            else hero.setY(Math.min(hero.getY()+20,670));

        }
        else if(e.getKeyChar()=='a'){
            if(hero.getDirect()!=2) hero.setDirect(2);
            else hero.setX(Math.max(hero.getX()-20,20));

        }
        else if(e.getKeyChar()=='d'){
            if(hero.getDirect()!=3) hero.setDirect(3);
            else hero.setX(Math.min(hero.getX()+20,940));
        }

        //Here is the bullet lunching key
        else if(e.getKeyChar()=='j'){
            hero.lunchBullet();
        }
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //do nothing here
    }
}
