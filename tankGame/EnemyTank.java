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
                int reDirect = (int)(Math.random()*4);
                boolean res = travelDirect(reDirect);
                while(!res){
                    if(reDirect==0){
                        setCollision(false);
                        res = travelDirect(1);
                    }
                    else if(reDirect==1){
                        setCollision(false);
                        res = travelDirect(0);
                    }
                    else if(reDirect==2){
                        setCollision(false);
                        res = travelDirect(3);
                    }
                    else if(reDirect==3){
                        setCollision(false);
                        res = travelDirect(2);
                    }
                }
                enemyAttack();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean travelDirect(int direct) throws InterruptedException {
        setDirect(direct);
        int travelDis;
        if(direct==0){
            travelDis = (int)(Math.random()*getY());
            while(travelDis>0 && getY()>20){
                if(getCollision()){
                    return false;
                }
                setY(getY()-1);
                Thread.sleep(10);
                travelDis--;
            }
        }
        else if(direct==1){
            travelDis = (int)(Math.random()*(670-getY()));
            while(travelDis>0 && getY()<670){
                if(getCollision()){
                    return false;
                }
                setY(getY()+1);
                Thread.sleep(10);
                travelDis--;
            }
        }
        else if(direct==2){
            travelDis = (int)(Math.random()*getX());
            while(travelDis>0 && getX()>20){
                if(getCollision()){
                    return false;
                }
                setX(getX()-1);
                Thread.sleep(10);
                travelDis--;
            }
        }
        else if(direct==3){
            travelDis =(int)(Math.random()*(940-getX()));
            while(travelDis>0 && getX()<940){
                if(getCollision()){
                    return false;
                }
                setX(getX()+1);
                Thread.sleep(10);
                travelDis--;
            }
        }
        return true;
    }


    public void enemyAttack() throws InterruptedException {
        lunchBullet();
        Thread.sleep(60);
    }

}
