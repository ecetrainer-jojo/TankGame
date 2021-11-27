package tankGame;

public class Bullet extends Thread{
    private int xCoordinate; //define the initial position of the bullet
    private int yCoordinate;
    private int speed; //shows how fast the bullet move from the tank
    private int direction; //defines how the bullet is flew

    public Bullet(int x, int y, int sp, int direct){
        xCoordinate = x;
        yCoordinate = y;
        speed = sp;
        direction = direct;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void run() {

    }
}
