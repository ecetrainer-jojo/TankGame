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
        //it will keep moving to one direction until hitting and enemy or boundary
        while((xCoordinate<940 && xCoordinate>20) && (yCoordinate<670 && yCoordinate>20)){
            switch(direction){
                case 0:
                    yCoordinate = Math.max(20, yCoordinate-speed);
                    break;
                case 1:
                    yCoordinate = Math.min(670, yCoordinate+speed);
                    break;
                case 2:
                    xCoordinate = Math.max(20, xCoordinate-speed);
                    break;
                case 3:
                    xCoordinate = Math.min(940, xCoordinate+speed);

            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
