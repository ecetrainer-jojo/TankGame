package tankGame;

import java.util.Vector;

abstract public class Tank {
    //set the coordinate of the tanks
    private int x;
    private int y;
    private Vector<Bullet> bullets;
    boolean isAlive;


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        bullets = new Vector<>();
        isAlive = true;
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

    public void setAlive(boolean alive) {
        isAlive = alive;
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
        bullets.add(bullet);
    }


    public boolean checkBullet(){
        if(bullets.size()==0){
            return false;
        }
        return true;
    }

    public void lunchBullet(){
        switch(getDirect()){
            case 0:
                setBullet(new Bullet(getX()+15,getY()-20,1, 0));
                break;

            case 1:
                setBullet(new Bullet(getX()+15,getY()+60,1, 1));
                break;

            case 2:
                setBullet(new Bullet(getX()-20,getY()+15,1,2));
                break;

            case 3:
                setBullet(new Bullet(getX()+60,getY()+15,1, 3));
                break;

        }

    }


}
