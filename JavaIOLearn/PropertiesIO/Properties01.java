package JavaIOLearn.PropertiesIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Properties01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/JavaIOLearn/PropertiesIO/default.properties"));
        String line = "";
        while((line=br.readLine())!=null){
            System.out.println((line.split("="))[1]);
        }
    }
}
