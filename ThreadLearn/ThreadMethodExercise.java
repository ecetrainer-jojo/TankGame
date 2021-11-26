package ThreadLearn;

public class ThreadMethodExercise {
    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<10; i++){
            System.out.println("HI, this is main");
            Thread.sleep(1000);
            if(i==4){
                Thread t2 = new Thread(new son());
                t2.start();
                t2.join();
            }
        }
    }
}

class son implements Runnable{
    int num = 0;
    @Override
    public void run() {
        while(num<10){
            System.out.println("hello, this is son");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;
        }
    }
}
