package JavaIOLearn.bufferedIOStream;

import java.io.*;

public class BufferedIOStream {
    public static void main(String[] args) throws IOException {
        String srcPath = "src/music/oceanwave.mp3";
        String destPath = "src/JavaIOLearn/xmas.mp3";
        musicCopy(srcPath,destPath);
    }

    public static void musicCopy(String srcPath, String destPath) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcPath));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destPath));
        int byteLen;
        byte[] b = new byte[1024];
        while((byteLen=bufferedInputStream.read(b))!=-1){
            bufferedOutputStream.write(b,0,byteLen);
        }
        bufferedOutputStream.close();
        bufferedInputStream.close();
        return;
    }
}
