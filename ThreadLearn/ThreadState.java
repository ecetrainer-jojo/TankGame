package ThreadLearn;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        love luv = new love();
        System.out.println(luv.getName()+" State: "+luv.getState());
        luv.start();
        while(luv.getState()!=Thread.State.TERMINATED){
            Thread.sleep(500);
            System.out.println(luv.getName()+" State: "+luv.getState());
        }


    }
}

class love extends Thread{
    @Override
    public void run() {
        for(int i=0; i<5; i++){
            System.out.println("hi"+ i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}