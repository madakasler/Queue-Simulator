import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addClient(List<Server> servers, Client t) throws InterruptedException {
        int time=Integer.MAX_VALUE;
        for(Server server:servers)
        {
            if(server.getWaitingPeriod().get()<time)
                time= server.getWaitingPeriod().get();
        }
        for(Server server:servers)
        {
            if(server.getWaitingPeriod().get()==time)
            {
                server.setOpen();
                server.addClient(t);
                break;
            }
        }

    }
}
