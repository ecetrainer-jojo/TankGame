package ThreadLearn;


//Three window simulate the ticket selling
public class SellTicket {
    public static void main(String[] args) throws InterruptedException {
        //create three windows to sell the ticket
        window w = new window();
        new Thread(w).start();
        new Thread(w).start();
        new Thread(w).start();



    }
}


class window implements Runnable{
    private int num = 100;
    private Object o = new Object();

    @Override
    public void run() {
        boolean b;
        do{
            synchronized (o) {
                b = sell();
            }
            Thread.yield();
        }while(b);

    }

    public boolean sell(){
        if(num<0) {
            System.out.println("The ticket is sold out at "+ Thread.currentThread().getName());
            return false;
        }
        System.out.println("Ticket id= "+num +" is sold by "+ Thread.currentThread().getName());
        num--;
        return true;
    }

}
