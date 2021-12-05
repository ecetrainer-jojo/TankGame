package JavaIOLearn.FileInpuyStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamLearn {
    public static void main(String[] args) throws IOException {
        String path = "src/JavaIOLearn/test.txt";
        FileInputStreamLearn.readFile02(path);

    }

    public static void readFile01(String path) throws IOException {

        int readData = 0;
        FileInputStream fileInputStream = new FileInputStream(path);
        while((readData = fileInputStream.read())!=-1){
            System.out.print((char)readData);
        }
        fileInputStream.close();

    }

    public static void readFile02(String path) throws IOException {
        int readLen = 0;
        FileInputStream fileInputStream = new FileInputStream(path);
        byte[] b = new byte[10];
        while((readLen = fileInputStream.read(b))!=-1){
            System.out.print(new String(b,0,readLen));
        }
        fileInputStream.close();

    }
}
