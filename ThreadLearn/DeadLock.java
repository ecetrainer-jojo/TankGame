package ThreadLearn;

public class DeadLock {
    public static void main(String[] args) {
        DeadLockDemo deadlock1 = new DeadLockDemo(false);
        DeadLockDemo deadlock2 = new DeadLockDemo(true);
        deadlock1.start();
        deadlock2.start();

    }
}

class DeadLockDemo extends Thread{
    static Object o1 = new Object();
    static Object o2 = new Object();
    boolean flag;
    public DeadLockDemo(boolean flag){
        this.flag = flag;
    }


    @Override
    public void run(){
        if(flag){
            synchronized (o1){
                System.out.println(Thread.currentThread().getName()+ "enters 1");
                while(getState()== State.BLOCKED){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("gg");
                }
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName() + "enters 2");
                }
            }
        }
        else{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName()+ "enters 3");
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName() + "enters 4");
                }
            }

        }
    }
}
