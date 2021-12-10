package tankGame;

import javax.swing.*;
import java.awt.*;

public class startPanel{
    public int modeSelected = 0;
    public final static String START_PATH = "src/tankGame/Start.png";
    public final static String RESUME_PATH = "src/tankGame/Resume.png";
    public final static String SCREEN_PATH = "src/tankGame/Screen.jpeg";
    public final static String DIFFICULTY_PATH = "src/tankGame/Difficulty_Selection.png";




    Image startPic;
    Image resumePic;
    Image screenPic;
    Image difficultyPic;

    public startPanel(){
        startPic = Toolkit.getDefaultToolkit().getImage(START_PATH);
        resumePic = Toolkit.getDefaultToolkit().getImage(RESUME_PATH);
        screenPic = Toolkit.getDefaultToolkit().getImage(SCREEN_PATH);
        difficultyPic = Toolkit.getDefaultToolkit().getImage(DIFFICULTY_PATH);
    }

    public void graphStart(Graphics g, JPanel p){
        g.drawImage(screenPic,0,0,MyPanel.BACKGROUND_X+MyPanel.SIDE_X, MyPanel.BACKGROUND_Y,p);
        g.drawImage(startPic, 100,500, 374,135, p);
        g.drawImage(resumePic, 750,500, 374,135, p);
    }

    public void difficultyPop(Graphics g, JPanel p){
        g.drawImage(screenPic,0,0,MyPanel.BACKGROUND_X+MyPanel.SIDE_X, MyPanel.BACKGROUND_Y,p);
        g.drawImage(difficultyPic, 300,450, 600,250, p);
    }

}
