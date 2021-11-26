package ThreadLearn;


//Three window simulate the ticket selling
public class SellTicket {
    public static void main(String[] args) throws InterruptedException {
        //create three windows to sell the ticket
        window w = new window();
        new Thread(w).start();
        new Thread(w).start();



    }
}


class window implements Runnable{
    private int num = 100;

    @Override
    public void run() {
        do{
            sell();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(num>0);
        if(num==0)System.out.println("The ticket Selling is done!");
    }

    public synchronized void sell(){
        System.out.println("Ticket id= "+num +" is sold by "+ Thread.currentThread().getName());
        num--;
        return;
    }

}
