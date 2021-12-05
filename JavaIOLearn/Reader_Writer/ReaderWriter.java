package JavaIOLearn.Reader_Writer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReaderWriter {
    public static void main(String[] args) throws IOException {
        String path = "src/JavaIOLearn/test.txt";
        writeFile(path);
        readFile(path);
    }

    public static void readFile(String str) throws IOException {
        FileReader fileReader = new FileReader(str);
        char[] c = new char[50];
        int readLen = 0;
        while((readLen=fileReader.read(c))!=-1){
            System.out.println(new String(c,0,readLen));
        }
        fileReader.close();
    }

    public static void writeFile(String str) throws IOException {
        FileWriter fileWriter = new FileWriter(str, true);
        String appendstr = " This is a new day for 坂井泉水";
        fileWriter.write(appendstr);
        fileWriter.flush();
        fileWriter.close();

    }


}
