package ThreadLearn;



public class ThreadLearn {
    public static void main(String[] args) {
        //create a cat object, could be treated as a bew thread
        Cat cat = new Cat();
        Dog dog = new Dog();
        Thread thread1 = new Thread(cat);
        Thread thread2 = new Thread(dog);
        thread1.start();
        thread2.start();


    }
}

class Cat implements Runnable{
    @Override
    public void run() {
        int num = 0;
        while(num<8000) {
            System.out.println("Hello! This is a cat with "+Thread.currentThread().getName());
            num+=1000;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Dog implements Runnable{
    @Override
    public void run() {
        int num = 0;
        while(num<5000) {
            System.out.println("Hello! This is a dog with "+Thread.currentThread().getName());
            num+=1000;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
