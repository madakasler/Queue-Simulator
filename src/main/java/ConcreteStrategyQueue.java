import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addClient(List<Server> servers, Client t) throws InterruptedException {
        int size=Integer.MAX_VALUE;
        for(Server server:servers)
        {
            if(server.getSize()<size)
                size= server.getSize();
        }
        for(Server server:servers)
        {
            if(server.getSize()==size)
            {
                server.setOpen();
                server.addClient(t);

                break;
            }
        }

    }
}
