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





    @Override
    public int getType() {
        return type;
    }

    @Override
    int getDirect() {
        return direct;
    }

}
