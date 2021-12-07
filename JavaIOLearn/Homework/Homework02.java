package JavaIOLearn.Homework;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Homework02 {
    public static void main(String[] args) throws IOException {
        loadDog();
    }

    public static void loadDog() throws IOException {
        String path = "src/JavaIOLearn/dog.properties";
        Properties properties = new Properties();
        properties.load(new FileReader(path));
        Dog myDog = new Dog(properties.getProperty("name"),Integer.parseInt(properties.getProperty("age")),properties.getProperty("color"));
        System.out.println(myDog);


    }
}


class Dog{
    public String name;
    public int age;
    public String color;

    public Dog(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
