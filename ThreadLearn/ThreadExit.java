package ThreadLearn;

public class ThreadExit {
    public static void main(String[] args) throws InterruptedException {
        T thread = new T();
        thread.start();
        Thread.sleep(8000);
        thread.interrupt();

    }
}

class T extends Thread{
    private boolean loop = true;
    @Override
    public void run() {

        int count = 0;
        while(loop){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("The thread is interrupted");
            }
            System.out.println("The thread is still running");
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}