package tankGame;

public class EnemyTank extends Tank{
    private int type = 1;
    private int direct = 0;

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
}
