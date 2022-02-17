import java.util.List;

public interface Strategy {
    public void addClient(List<Server> servers, Client  client) throws InterruptedException;

}
enum SelectionPolicy{
    SHORTEST_QUEUE, SHORTEST_TIME;
}