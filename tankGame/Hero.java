package tankGame;

//This is my tank
public class Hero extends Tank {
    private int type = 0;
    private int direct = 0;


    //provide the constructor
    public Hero(int x, int y) {
        super(x, y);
    }

    public Hero(int x, int y, int type, int direct) {
        super(x, y);
        this.type = type;
        this.direct = direct;

    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public void setType(int type) {
        this.type = type;
    }



    public void lunchBullet(){
        switch(direct){
            case 0:
                super.setBullet(new Bullet(getX()+15,getY()-20,1, direct));
                break;

            case 1:
                super.setBullet(new Bullet(getX()+15,getY()+60,1, direct));
                break;

            case 2:
                super.setBullet(new Bullet(getX()-20,getY()+15,1, direct));
                break;

            case 3:
                super.setBullet(new Bullet(getX()+60,getY()+15,1, direct));
                break;

        }
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    int getDirect() {
        return direct;
    }

}
