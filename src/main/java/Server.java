import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread implements Runnable{
    private BlockingQueue<Client> clients;
    private int indice;
    private int time;
    private AtomicInteger waitingPeriod;
    private boolean open;

    public Server(int i) {
        this.clients = new LinkedBlockingDeque<Client>(2000);
        this.waitingPeriod = new AtomicInteger(0);
        this.time=0;
        this.indice=i;
        this.open=false;


    }

    public void addClient(Client client) throws InterruptedException {
        this.clients.put(client);


        this.waitingPeriod.getAndAdd(client.getTimeService());
        client.setWaitingTime(this.waitingPeriod.get());
    }

    @Override
    public void run() {

        while(true) {

            try {

                for(Client c:this.getClientsArray()) {


                        Thread.sleep((c.getTimeService()*1000));
                        this.waitingPeriod.getAndAdd(-c.getTimeService());
                        this.clients.take();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public BlockingQueue<Client> getClientsArray() throws InterruptedException {

        return this.clients;
    }

    public int getSize()
    {
       return this.clients.size();
    }


    public void setClients(BlockingQueue<Client> clients) {
        this.clients = clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setOpen() {
        this.open = true;
    }

    public boolean isOpen() {
        return open;
    }
    void removeClient(Client c)
    {
        this.clients.remove(c);
    }
}
