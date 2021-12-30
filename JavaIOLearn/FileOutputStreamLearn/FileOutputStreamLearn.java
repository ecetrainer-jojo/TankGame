package JavaIOLearn.FileOutputStreamLearn;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamLearn {
    public static void main(String[] args) {

    }

    //show how to use the FileOutPutStream and write data into the file
    //if the file not exist then create such file

    public void writeFile() throws IOException {
        String path = "src/JavaIOLearn/test.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(path,true);
        String str = "Hello world";
        fileOutputStream.write(str.getBytes());
        fileOutputStream.close();


    }

}
