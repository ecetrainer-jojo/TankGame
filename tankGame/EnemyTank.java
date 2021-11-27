package tankGame;

public class EnemyTank extends Tank implements Runnable{
    private int type = 1;
    private int direct = 0;
    private int speed = 1;

    public EnemyTank(int x, int y, int type, int direct) {
        super(x, y);
        this.type = type;
        this.direct = direct;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getDirect() {
        return direct;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void setDirect(int Direct) {
        this.direct = Direct;
    }

    @Override
    public void run() {
        //Make it randomly move on the map
        //design a random number to determine the direction
        //design a random number to determine the distance travel
        while(true){
            try {
                travel();
                enemyAttack();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void travel() throws InterruptedException {
        //generate random number
        int reDirect = (int)(Math.random()*4);
        setDirect(reDirect);
        int travelDis;
        //according to the direction find the possible travel distance
        System.out.println("the enemy at "+getX() +','+getY()+ "is moving");
        if(reDirect==0){

            travelDis = (int)(Math.random()*getY());
            System.out.println(travelDis);
            while(travelDis>0 && getY()>20){
                setY(getY()-1);
                Thread.sleep(10);
                travelDis--;
            }
        }
        else if(reDirect==1){
            travelDis = (int)(Math.random()*(670-getY()));
            System.out.println(travelDis);
            while(travelDis>0 && getY()<670){
                setY(getY()+1);
                Thread.sleep(10);
                travelDis--;
            }
        }
        else if(reDirect==2){
            travelDis = (int)(Math.random()*getX());
            System.out.println(travelDis);
            while(travelDis>0 && getX()>20){
                setX(getX()-1);
                Thread.sleep(10);
                travelDis--;
            }
        }
        else if(reDirect==3){
            travelDis =(int)(Math.random()*(940-getX()));
            System.out.println(travelDis);
            while(travelDis>0 && getX()<940){
                setX(getX()+1);
                Thread.sleep(10);
                travelDis--;
            }
        }

    }

    public void enemyAttack() throws InterruptedException {
        lunchBullet();
        Thread.sleep(200);
    }
}
