package tankGame;

import java.awt.*;
import java.io.*;

public class ScoreBoard {
    private final File gameLog = new File("src/tankGame/Record.txt");
    private int currentKill = 0;
    private int historicalKill = 0;
    public EnemyTank enemyTank1;

    //The constructor the scoreboard
    public ScoreBoard() throws IOException {
        logAccess();

        //Prepare for the drawing the tank
        enemyTank1 = new EnemyTank(MyPanel.BACKGROUND_X + 70, 90,1,0);
    }


    //Game log handling
    public void logAccess() throws IOException {
        if(gameLog.exists()) {
            BufferedReader bf = new BufferedReader(new FileReader(gameLog));
            String readContent;
            while ((readContent = bf.readLine()) != null) {
                if(readContent.equals("")) continue;
                historicalKill = Math.max(historicalKill, Integer.parseInt(readContent));
            }
        }
        else {
            gameLog.createNewFile();
        }
    }

    //function to refresh the current kills
    public void addCurrentKill(){
        currentKill++;
    }

    public void drawScore(Graphics g) {
        //draw out the sidebar to show the mark the user get
        g.setColor(Color.white);
        g.fillRect(MyPanel.BACKGROUND_X, 0, MyPanel.BACKGROUND_X + MyPanel.SIDE_X, MyPanel.BACKGROUND_Y);
        g.setColor(Color.BLACK);
        g.drawString("Your Best Score:  "+ historicalKill, MyPanel.BACKGROUND_X + 15,20);
        g.drawString("Your Score This Game ", MyPanel.BACKGROUND_X + 15,45);

        if (enemyTank1 != null) {
            drawTank(enemyTank1, g);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(currentKill), MyPanel.BACKGROUND_X + 150,  120);
        }
    }

    //Method to draw the tank
    public void drawTank(Tank myTank, Graphics g){

        switch (myTank.getType()) {
            case 1 -> g.setColor(Color.orange);
        }

        g.fill3DRect(myTank.getX(), myTank.getY(), 10, 60, true);
        g.fill3DRect(myTank.getX() + 10, myTank.getY() + 10, 20, 40, true);
        g.fill3DRect(myTank.getX() + 30, myTank.getY(), 10, 60, false);
        g.fillOval(myTank.getX() + 10, myTank.getY() + 20, 20, 20);
        g.drawLine(myTank.getX() + 20, myTank.getY() + 20, myTank.getX() + 20, myTank.getY());


    }

    //update the game log
    public void logUpdate() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(gameLog,true));
        bufferedWriter.newLine();
        bufferedWriter.write(Integer.toString(currentKill));
        bufferedWriter.close();
    }

}
