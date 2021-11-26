package tankGame;

abstract public class Tank {
    //set the coordinate of the tanks
    private int x;
    private int y;
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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

    //set up abstract method to get the type and direction
    abstract int getType();
    abstract int getDirect();
    abstract void setType(int type);
    abstract void setDirect(int Direct);
}
