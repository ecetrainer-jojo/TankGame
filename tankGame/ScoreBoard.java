package tankGame;

import java.awt.*;
import java.io.*;
import java.util.HashSet;

public class ScoreBoard {
    private final File gameLog = new File("src/tankGame/Record.txt");
    //shows the killing for different tanks

    private int[] currentKill = new int[3];
    private int historicalKill = 0;

    //add the prototype of the Existing Tanks for drawing on scoreboard
    public EnemyTank enemyTank1 = null;
    public EnemyTank enemyTank2 = null;
    public EnemyTank enemyTank3 = null;


    //The constructor the scoreboard
    //Prepare for the drawing the tank
    public ScoreBoard(HashSet<Integer> enemyTypes) throws IOException {
        logAccess();
        int count = 0;

        //Decide the position of the tanks
        for(Integer i:enemyTypes){
            if(i==1) enemyTank1 = new EnemyTank(MyPanel.BACKGROUND_X + 70, 90 + count *100,i,0);
            else if(i==2) enemyTank2 = new EnemyTank(MyPanel.BACKGROUND_X + 70, 90 + count *100,i,0);
            else enemyTank3 = new EnemyTank(MyPanel.BACKGROUND_X + 70, 90 + count *100,i,0);
            count++;
        }
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
    public void addCurrentKill(Tank myTank){
        if(myTank.getType()==1){
            currentKill[0]++;
        }
        else if(myTank.getType()==2){
            currentKill[1]++;
        }
        else{
            currentKill[2]++;
        }
    }

    public void setCurrentKill(int[] kills) {
        currentKill[0] = kills[0];
        currentKill[1] = kills[1];
        currentKill[2] = kills[2];
    }

    public int[] getCurrentKill() {
        return currentKill;
    }

    public void drawScore(Graphics g) {
        //draw out the sidebar to show the mark the user get
        g.setColor(Color.white);
        g.fillRect(MyPanel.BACKGROUND_X, 0, MyPanel.BACKGROUND_X + MyPanel.SIDE_X, MyPanel.BACKGROUND_Y);
        g.setColor(Color.BLACK);
        g.drawString("Your Best Score:  "+ historicalKill, MyPanel.BACKGROUND_X + 15,20);
        g.drawString("Your Score This Game ", MyPanel.BACKGROUND_X + 15,45);

        int count = 0;
        if (enemyTank1 != null) {
            drawTank(enemyTank1, g);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(currentKill[0]), MyPanel.BACKGROUND_X + 150,  120+100*count);
            count++;
        }
        if (enemyTank2 != null) {
            drawTank(enemyTank2, g);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(currentKill[1]), MyPanel.BACKGROUND_X + 150,  120+100*count);
            count++;
        }
        if (enemyTank3 != null) {
            drawTank(enemyTank3, g);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(currentKill[2]), MyPanel.BACKGROUND_X + 150,  120+100*count);
            count++;
        }




    }

    //Method to draw the tank
    public void drawTank(Tank myTank, Graphics g){

        switch (myTank.getType()) {
            case 1 -> g.setColor(Color.orange);
            case 2 -> g.setColor(Color.PINK);
            case 3 ->g.setColor(Color.red);
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
        bufferedWriter.write(Integer.toString(currentKill[0]+currentKill[1]+currentKill[2]));
        bufferedWriter.close();
    }

}
