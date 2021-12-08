package tankGame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.Vector;




//Tank game graphing area
public class MyPanel extends JPanel implements KeyListener, MouseListener {
    //define some constant here
    public static final int HERO_START_X = 500;
    public static final int HERO_START_Y = 600;
    public static final int BACKGROUND_X = 1000;
    public static final int BACKGROUND_Y = 750;
    public static final int VALID_X_MAX = 940;
    public static final int VALID_Y_MAX = 670;
    public static final int VALID_X_MIN = 20;
    public static final int VALID_Y_MIN = 20;
    public static final int INITIALIZE_GAP = 70;
    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_HEIGHT = 20;
    public static final int SIDE_X = 200;
    public static final String EXPLODE_IMAGE = "src/tankGame/explosion.jpeg";



    //set hero to be null number
    Hero hero = null;

    //make a default tanks (support multi-thread)
    Vector<Tank> enemies = new Vector<>();

    //keep track of the explosion location
    Vector<Explosion> deadBody = new Vector<>();

    //define the enemySize -> the difficulty of enemy
    int enemySize = 5;
    public boolean gameOver = false;

    //initialize the element scoreboard
    public ScoreBoard myScoreBoard;

    //initialize the startPanel
    public startPanel sp = new startPanel();

    public MyPanel(){
        defaultInitialize();
    }

    public void defaultInitialize(){
        //Initialize the User
        hero = new Hero(HERO_START_X,HERO_START_Y,0,0); //default initialization of tank

        //Initialize the scoreboard
        try {
            myScoreBoard = new ScoreBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Initialize the enemy Randomly
        enemyInitialization();
    }

    public void drawStartScreen(Graphics g){
        sp.graphStart(g,this);
    }

    public void drawGame(Graphics g){
        g.fillRect(0,0,BACKGROUND_X+SIDE_X,BACKGROUND_Y); //This is the background of tank default black
        //draw the scoreboard
        if(myScoreBoard!=null) myScoreBoard.drawScore(g); //check the explosion

        explosionHandle(enemies,hero); //Handle if the explosion occurs

        collisionHandle(); //check the collision

        //Draw the tanks here
        try {
            if(refreshTank(hero,g)){
                if(hero.isDamaged()){
                    System.out.println("here damaged");
                    Thread.sleep(20);
                    hero.setDamaged(false);
                }
                drawTank(hero,g);
                drawBullet(hero,g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            drawEnemyTank(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawExplosion(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(sp.modeSelected==false){
            drawStartScreen(g);
        }
        else drawGame(g);

    }

    //Method to perform the enemy initialization
    public void enemyInitialization(){
        boolean overlap;
        int x,y;
        for(int i=0; i<enemySize;i++){
            int direct = (int)(Math.random()*4);
            //Try the location without any overlap with others
            do{
                x = (int) (Math.random() * VALID_X_MAX) - VALID_X_MIN;
                y = (int) (Math.random() * VALID_Y_MAX) - VALID_Y_MIN;
                overlap = isOverlap(x,y,i);
            }while(overlap);
            //insert that into the vector array
            EnemyTank tankCreated = new EnemyTank(x,y,1,direct);
            (new Thread(tankCreated)).start();
            enemies.add(tankCreated);
        }
    }

    //Method to check initialization overlap
    public boolean isOverlap(int x, int y, int index){

        for(int i=0; i<index; i++){
            if(enemies.get(i)==null) continue;
            if(Math.abs(enemies.get(i).getX() - x)<INITIALIZE_GAP || Math.abs(enemies.get(i).getY() - y)<INITIALIZE_GAP) return true;
        }
        if(Math.abs(HERO_START_X - x)<INITIALIZE_GAP || Math.abs(HERO_START_Y - y)<INITIALIZE_GAP) return true;
        return false;
    }

    //Fresh the tank and invoke the explosion if the tank is dead
    public boolean refreshTank(Tank myTank, Graphics g) throws IOException {
        if(!myTank.getAlive()){
            Explosion e = new Explosion(myTank.getX(),myTank.getY());
            deadBody.add(e);
            e.start();
            if(myTank.getType()!=0){
                enemySize--;
                myScoreBoard.addCurrentKill();
                if(enemySize==0){
                    myScoreBoard.logUpdate();
                    gameOver = true;
                }
            }
            else{
                gameOver = true;
                myScoreBoard.logUpdate();
            }
            return false;
        }
        return true;
    }

    //Method to draw the tank
    public void drawTank(Tank myTank, Graphics g){

        switch (myTank.getType()) {
            case 0 -> //our tank
                    g.setColor(Color.CYAN);
            case 1 -> g.setColor(Color.orange);
        }

        if(myTank.isDamaged()) g.setColor(Color.gray);

        //according to the direction of the tank draw the tank
        switch (myTank.getDirect()) {
            case 0 -> { //when bomb points to the top
                g.fill3DRect(myTank.getX(), myTank.getY(), 10, 60, false);
                g.fill3DRect(myTank.getX() + 10, myTank.getY() + 10, 20, 40, false);
                g.fill3DRect(myTank.getX() + 30, myTank.getY(), 10, 60, false);
                g.fillOval(myTank.getX() + 10, myTank.getY() + 20, 20, 20);
                g.drawLine(myTank.getX() + 20, myTank.getY() + 20, myTank.getX() + 20, myTank.getY());
            }
            case 1 -> { //when the bomb points to the bottom
                g.fill3DRect(myTank.getX(), myTank.getY(), 10, 60, true);
                g.fill3DRect(myTank.getX() + 10, myTank.getY() + 10, 20, 40, false);
                g.fill3DRect(myTank.getX() + 30, myTank.getY(), 10, 60, false);
                g.fillOval(myTank.getX() + 10, myTank.getY() + 20, 20, 20);
                g.drawLine(myTank.getX() + 20, myTank.getY() + 20, myTank.getX() + 20, myTank.getY() + 60);
            }
            case 2 -> {//when the bomb points to the left
                g.fill3DRect(myTank.getX(), myTank.getY(), 60, 10, false);
                g.fill3DRect(myTank.getX() + 10, myTank.getY() + 10, 40, 20, false);
                g.fill3DRect(myTank.getX(), myTank.getY() + 30, 60, 10, false);
                g.fillOval(myTank.getX() + 20, myTank.getY() + 10, 20, 20);
                g.drawLine(myTank.getX() + 20, myTank.getY() + 20, myTank.getX(), myTank.getY() + 20);
            }
            case 3 -> {//when the bomb points to the right
                g.fill3DRect(myTank.getX(), myTank.getY(), 60, 10, true);
                g.fill3DRect(myTank.getX() + 10, myTank.getY() + 10, 40, 20, false);
                g.fill3DRect(myTank.getX(), myTank.getY() + 30, 60, 10, false);
                g.fillOval(myTank.getX() + 20, myTank.getY() + 10, 20, 20);
                g.drawLine(myTank.getX() + 20, myTank.getY() + 20, myTank.getX() + 60, myTank.getY() + 20);
            }
            default -> System.out.println("Temporarly not proceed");
        }

    }

    //Method to drawEnemyTank
    public void drawEnemyTank(Graphics g) throws IOException {
        Iterator<Tank> enemyIt = enemies.iterator();
        while(enemyIt.hasNext()){
            Tank currentT = enemyIt.next();

            //check if the enemy should be deleted
            boolean checkDead = refreshTank(currentT,g);
            if(!checkDead){
                enemyIt.remove();
            }
            else {
                drawTank(currentT,g);
                drawBullet(currentT,g);
            }
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
            }
            else{
                if(b.getDirection()==0 || b.getDirection()==1){
                    g.fillOval(b.getxCoordinate(),b.getyCoordinate(),BULLET_WIDTH,BULLET_HEIGHT);
                }
                else{
                    g.fillOval(b.getxCoordinate(),b.getyCoordinate(),BULLET_HEIGHT,BULLET_WIDTH);
                }
                if(b.getState()==Thread.State.NEW){
                    b.start();
                }
            }
        }
    }

    //function to draw the explosion
    public void drawExplosion(Graphics g){
        Image explosion = Toolkit.getDefaultToolkit().getImage(EXPLODE_IMAGE);
        Iterator<Explosion> it = deadBody.iterator();
        while(it.hasNext()){
            Explosion e = it.next();
            if(e.getAlive()){
                g.drawImage(explosion,e.getX(),e.getY(),80,60, this);
            }
            else it.remove();
        }


    }

    //explosion handler from enemy to hero
    public void explosionHandle(Vector<Tank> enemy, Tank myTank){
        //check if hero bullets will kill the enemy
        for(Bullet b: myTank.getBullets()){
            if(!b.isAlive() || myTank.isDamaged()) continue;
            for(int i=0; i<enemySize; i++){
                checkExplode(b,enemies.get(i));
            }
        }

        //check if enemy bullets will kill the hero
        for(Tank enemyT : enemy){
            for(Bullet b: enemyT.getBullets()){
                if(!b.isAlive() || myTank.isDamaged()) continue;
                checkExplode(b,myTank);
            }
        }
    }


    //Create the shooting judgement for the bullets
    public boolean checkExplode(Bullet b, Tank myTank){
        int bulletX = b.getxCoordinate();
        int bulletY = b.getyCoordinate();
        int tankX = myTank.getX();
        int tankY = myTank.getY();
        if(myTank.getDirect()==0 || myTank.getDirect()==1){
            if((bulletX>tankX && bulletX < tankX+40) && (bulletY>tankY && bulletY < tankY+60)){
                myTank.setArmor(myTank.getArmor()-1);
                if(myTank.getArmor()==0){
                    myTank.setAlive(false);
                }
                else{
                    myTank.setDamaged(true);
                }
                b.setAlive(false);
                return true;
            }
        }
        else{
            if((bulletX>tankX && bulletX < tankX+60) && (bulletY>tankY && bulletY < tankY+40)){
                myTank.setArmor(myTank.getArmor()-1);
                if(myTank.getArmor()==0){
                    myTank.setAlive(false);
                }
                else{
                    myTank.setDamaged(true);
                }
                b.setAlive(false);
                return true;
            }
        }
        return false;

    }

    //The logic to detect possible collision and try to prevent the overlap of graph
    public void collisionHandle() {
        boolean heroPass = true;
        for(int i=0; i<enemies.size();i++){
            if(enemies.get(i).getAlive() && (hero.getAlive())){
                if(Math.abs(enemies.get(i).getX()-hero.getX())<INITIALIZE_GAP && Math.abs(enemies.get(i).getY()-hero.getY())<INITIALIZE_GAP){
                    enemies.get(i).setCollision(true);
                    hero.setCollision(true);
                    heroPass = false;
                }
            }
            for(int j=0; j<enemies.size();j++){
                //check if the Collision is likely to happen among the enemies
                if(i==j) continue;
                if(!enemies.get(i).getAlive() || !enemies.get(j).getAlive()) continue;
                if(Math.abs(enemies.get(i).getX()-enemies.get(j).getX())<INITIALIZE_GAP && Math.abs(enemies.get(i).getY()-enemies.get(j).getY())<INITIALIZE_GAP){
                    enemies.get(i).setCollision(true);
                }
            }
        }
        if(heroPass) hero.setCollision(false);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing here
    }

    //when we pressed WASD to move it towards different ways
    //It cost one pressing to change the direction
    @Override
    public void keyPressed(KeyEvent e) {
        //Do the overall control with Game over
        if(gameOver) return;
        while(hero.getCollision()) {
            return;
        }
        //Here is the direction key
        if(e.getKeyChar()=='w'){
            if(hero.getDirect()!=0) hero.setDirect(0);
            else hero.setY(Math.max(hero.getY()-VALID_Y_MIN,VALID_Y_MIN));
        }
        else if(e.getKeyChar()=='s'){
            if(hero.getDirect()!=1) hero.setDirect(1);
            else hero.setY(Math.min(hero.getY()+VALID_Y_MIN,VALID_Y_MAX));

        }
        else if(e.getKeyChar()=='a'){
            if(hero.getDirect()!=2) hero.setDirect(2);
            else hero.setX(Math.max(hero.getX()-VALID_X_MIN,VALID_X_MIN));

        }
        else if(e.getKeyChar()=='d'){
            if(hero.getDirect()!=3) hero.setDirect(3);
            else hero.setX(Math.min(hero.getX()+VALID_X_MIN,VALID_X_MAX));
        }

        //Here is the bullet lunching key
        else if(e.getKeyChar()=='j'){
            hero.lunchBullet();
        }
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(sp.modeSelected==false){
            if(e.getX()>100 && e.getX()<475 && e.getY()>500 && e.getY()<635){
                System.out.println("clicked start");
                sp.modeSelected = true;
            }
            else if(e.getX()>750 && e.getX()<1124 && e.getY()>500 && e.getY()<635) {
                System.out.println("clicked resume");
                sp.modeSelected = true;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
