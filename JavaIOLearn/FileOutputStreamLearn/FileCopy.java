package JavaIOLearn.FileOutputStreamLearn;

import JavaIOLearn.FileInpuyStream.FileInputStreamLearn;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    //Complete the explosion jpg into under the FileOutPutStreamLearn
    public static void main(String[] args) {

    }

    @Test
    public void copyOperation() throws IOException {
        String srcPath = "src/tankGame/explosion.jpeg";
        String destPath = "src/JavaIOLearn/FileOutputStreamLearn/copySample.jpeg";
        byte[] b = new byte[100];
        int readLen;
        FileInputStream fileInputStream = new FileInputStream(srcPath);
        FileOutputStream fileOutputStream = new FileOutputStream(destPath,false);
        while((readLen = fileInputStream.read(b))!=-1){
            fileOutputStream.write(b,0, readLen);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
