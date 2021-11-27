package tankGame;

public class Explosion extends Thread {
    //This class will keep track of the location of the explosion
    private int x;
    private int y;
    private boolean isAlive;

    Explosion(int x, int y){
        this.x = x;
        this.y = y;
        isAlive = true;
    }

    public boolean getAlive(){
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }



    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isAlive = false;
    }
}
