package JavaIOLearn.ObjectIOStream;


import java.io.*;

public class ObjectIOStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String destPath = "src/JavaIOLearn/ObjectIOStream/data.dat";
        writeData(destPath);
        readData(destPath);
    }

    public static void writeData(String path) throws IOException {
        File newFile = new File(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(newFile));
        //serialize the data into the new File
        objectOutputStream.writeUTF("听见涛声");
        objectOutputStream.writeObject(new dog(12,"lucky"));
        objectOutputStream.close();



    }

    public static void readData(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
        System.out.println(objectInputStream.readUTF());
        System.out.println(objectInputStream.readObject());
        objectInputStream.close();
    }
}

class dog implements Serializable{
    int age = 0;
    String name = "";

    public dog(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "dog{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
