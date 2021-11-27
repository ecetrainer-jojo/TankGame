package ThreadLearn;

import java.util.concurrent.locks.Lock;

public class SellTicket2 {
    public static void main(String[] args) throws InterruptedException {
        //initialize two locks
        Object sellLock = new Object();
        new window1(sellLock).start();
        new window1(sellLock).start();
        new window1(sellLock).start();
        new window1(sellLock).start();

    }

}

class window1 extends Thread{
    private static int num = 100;
    private Object sellLock;

    window1(Object o){
        sellLock = o;
    }

    @Override
    public void run() {
        boolean b;

        do{
            synchronized (sellLock) {
                b = sell();
            }
            //Increase Equity by calling yield function
            Thread.yield();
        }while(b);
    }

    public boolean sell(){
        if(getSetNum(true, 0)<0){
            return false;
        }


        System.out.println("The ticket #" + getSetNum(true, 0) + " is gone and taken by " + Thread.currentThread().getName());
        getSetNum(false, getSetNum(true, 0) - 1);
        return true;
    }

    public int getSetNum(boolean get, int num1){
        if (get == true) return num;
        else num = num1;
        return 0;
    }

}


