package ThreadLearn;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Struct;
import java.util.Scanner;

public class HomeWork01 {
    public static void main(String[] args) {
        inform o = new inform();
        t1 t = new t1(o);
        t.start();
        t2 t2 = new t2(t);
        t2.start();
    }
}

class inform{
    boolean end = false;
}

class t1 extends Thread{
    inform message;

    t1(inform message){
        this.message = message;
    }



    @Override
    public void run() {
        int random;
        while(!message.end){
            random = (int)(Math.random()*100);
            System.out.println("The random number is "+ random);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("t1 is gone!");
    }
}

class t2 extends Thread{
    t1 t;

    t2(t1 thread){
        t = thread;
    }

    @Override
    public void run() {
        Scanner myScanner = new Scanner(System.in);
        char c = ' ';
        while(c!='q'){
            System.out.println("press q to quit");
            c = myScanner.next().charAt(0);
            if(c=='q'){
                t.message.end= true;
            }
        }
        System.out.println("t2 is gone");
        return;


    }
}




