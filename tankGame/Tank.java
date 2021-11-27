package tankGame;

import java.util.Vector;

abstract public class Tank {
    //set the coordinate of the tanks
    private int x;
    private int y;
    private Vector<Bullet> bullets;
    private int numBullets;
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        bullets = new Vector<>();
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    //set up abstract method to get the type and direction
    abstract int getType();
    abstract int getDirect();
    abstract void setType(int type);
    abstract void setDirect(int Direct);

    //method to setBullet
    public void setBullet(Bullet bullet) {
        System.out.println("bullet created");
        bullets.add(bullet);
    }


    public boolean checkBullet(){
        if(bullets.size()==0){
            return false;
        }
        return true;
    }


}
