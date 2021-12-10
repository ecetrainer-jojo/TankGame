package tankGame;

//This difficulty handler will determine the difficulty level
//Generate the zone area and the number/types of the enemy tanks
public class difficultyHandler {
    //For difficulty 0 ->easy, 1->medium, 2->hard
    public final static int HARD_X_MIN = 300;
    public final static int HARD_X_MAX = 500;
    public final static int MEDIUM_X_MIN = 500;
    public final static int MEDIUM_X_MAX = 700;
    public final static int EASY_X_MIN = 700;
    public final static int EASY_X_MAX = 900;
    public final static int SELECT_Y_MIN = 450;
    public final static int SELECT_Y_MAX = 700;
    public final static int[] ENEMY_SIZE = {2,4,6};
    private int difficulty = 0;
    private boolean isSelected = false;

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
