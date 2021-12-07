package JavaIOLearn.Homework;

import java.io.File;
import java.io.IOException;

public class Homework01 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/JavaIOLearn/hello.txt");
        if(!file.exists()){
            file.createNewFile();
        }
    }
}
