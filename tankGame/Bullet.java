package tankGame;

public class Bullet extends Thread{
    private int xCoordinate; //define the initial position of the bullet
    private int yCoordinate;
    private int speed; //shows how fast the bullet move from the tank
    private int direction; //defines how the bullet is flew
    private boolean alive;

    public Bullet(int x, int y, int sp, int direct){
        xCoordinate = x;
        yCoordinate = y;
        speed = sp;
        direction = direct;
        alive =true;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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
        //it will keep moving to one direction until hitting and enemy or boundary
        while((xCoordinate<MyPanel.BACKGROUND_X && xCoordinate>0) && (yCoordinate<MyPanel.BACKGROUND_Y && yCoordinate>0)){
            if(alive == false){
                return;
            }
            switch(direction){
                case 0:
                    yCoordinate = Math.max(0, yCoordinate-speed);
                    break;
                case 1:
                    yCoordinate = Math.min(MyPanel.BACKGROUND_Y, yCoordinate+speed);
                    break;
                case 2:
                    xCoordinate = Math.max(0, xCoordinate-speed);
                    break;
                case 3:
                    xCoordinate = Math.min(MyPanel.BACKGROUND_X, xCoordinate+speed);

            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
