package JavaIOLearn;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    public static void main(String[] args){
        fileCreator3();
    }

    public static void fileCreator1(){
        String path = "src/JavaIOLearn/test.txt";
        File f = new File(path);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileCreator2(){
        String parentPath = "src/JavaIOLearn/";
        File parentF = new File(parentPath);
        String fName = "test.txt";
        File F = new File(parentF, fName);
        try {
            F.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileCreator3(){
        String parentPath = "src/JavaIOLearn/";
        String fName = "test.txt";
        File F = new File(parentPath, fName);
        try {
            F.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


