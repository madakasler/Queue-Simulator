import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Scheduler{

    private ArrayList<Server> servers=new ArrayList<Server>();
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
         this.maxNoServers=maxNoServers;
         this.maxTasksPerServer=maxTasksPerServer;
         //List<Thread> list= Collections.synchronizedList(new ArrayList<Thread>());
        /*for (int i = 1; i <= maxNoServers; i++) {
          synchronized (Main.class) {
              Server server = new Server(i);

              this.servers.add(server);
              Thread t1 = new Thread(new Server(i));
              list.add(t1);
              t1.start();
          }


        }*/

    }
    public void setOpen(Server server)
    {
        server.setOpen();
    }


    public void changeStrategy(SelectionPolicy policy){

        if(policy==SelectionPolicy.SHORTEST_QUEUE)
        {
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME)
        {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchClient(Client c) throws InterruptedException {
        strategy.addClient(this.servers,c);


    }

    public Strategy getStrategy() {
        return strategy;
    }


    public List<Server> getServers(){
        return servers;
    }

    public void setServers(ArrayList<Server> servers) {
        this.servers = servers;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}