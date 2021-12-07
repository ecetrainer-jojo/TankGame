package JavaIOLearn.PropertiesIO;

import javax.annotation.processing.Filer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Properties02{
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/JavaIOLearn/PropertiesIO/default.properties"));
        properties.list(System.out);
        String user = properties.getProperty("user");
        System.out.println();
        System.out.println(user);
        properties.setProperty("user","maria");
        properties.store(new FileWriter("src/JavaIOLearn/PropertiesIO/default.properties",false),"chaneged comments");
    }
}
