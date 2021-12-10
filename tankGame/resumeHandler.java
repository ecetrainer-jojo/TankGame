package tankGame;

import java.io.*;
import java.util.HashSet;
import java.util.Vector;
//The resume log will keep record of the Position of the tanks + The number of tanks killed

public class resumeHandler {
    public final static File resumeLog = new File("src/tankGame/resume.txt");
    public int heroX;
    public int heroY;
    public int[] resumeKilling = new int[3];
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public resumeHandler() throws IOException {
        if(!resumeLog.exists()){
            resumeLog.createNewFile();
        }
    }

    public int loadLog(Vector<Tank> enemies, HashSet<Integer> enemyTypes) throws IOException, ClassNotFoundException {
        objectInputStream = new ObjectInputStream(new FileInputStream(resumeLog));
        int enemySize = objectInputStream.readInt();
        System.out.println(enemySize);
        for (int i = 0; i < enemySize; i++) {
            EnemyTank newEnemy = (EnemyTank) readTank(objectInputStream,1,enemyTypes);
            enemies.add(newEnemy);
            (new Thread(newEnemy)).start();
        }
        readTank(objectInputStream,0,enemyTypes);
        resumeKilling[0] = objectInputStream.readInt();
        resumeKilling[1] = objectInputStream.readInt();
        resumeKilling[2] = objectInputStream.readInt();

        objectInputStream.close();
        return enemySize;
    }
     public Tank readTank(ObjectInputStream objectInputStream, int mode, HashSet<Integer> enemyTypes) throws IOException {
        //need to read x, y, type direct
        int x = objectInputStream.readInt();
        int y = objectInputStream.readInt();
        int type = objectInputStream.readInt();
        int direct = objectInputStream.readInt();
        if(mode==0){
            heroX = x;
            heroY = y;
            return null;
        }
        else{
            enemyTypes.add(type);
            return new EnemyTank(x,y,type,direct);
        }
     }

     public void saveTank(ObjectOutputStream objectOutputStream, Tank myTank) throws IOException {
        objectOutputStream.writeInt(myTank.getX());
        objectOutputStream.writeInt(myTank.getY());
        objectOutputStream.writeInt(myTank.getType());
        objectOutputStream.writeInt(myTank.getDirect());
     }

    public void saveLog(Vector<Tank> enemies, Hero hero, ScoreBoard myScoreBoard) throws IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(resumeLog));
        objectOutputStream.writeInt(enemies.size());
        for(int i=0; i<enemies.size();i++){
            saveTank(objectOutputStream,enemies.get(i));
        }
        saveTank(objectOutputStream,hero);
        objectOutputStream.writeInt(myScoreBoard.getCurrentKill()[0]);
        objectOutputStream.writeInt(myScoreBoard.getCurrentKill()[1]);
        objectOutputStream.writeInt(myScoreBoard.getCurrentKill()[2]);
        objectOutputStream.close();
    }

    public static File getResumeLog() {
        return resumeLog;
    }
}
