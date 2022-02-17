import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.WebSocket;

public class Controller {
    private View view;
    private int timelimit;
    private int maxServiceTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int minServiceTime;
    private int clients;
    private int queues;
    public Controller(View view) {
        this.view = view;
        this.view.confirm(new Listener());


    }

    class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.confirm(e1 -> {
                view.setText("");
               setTimelimit(Integer.parseInt(view.getSimulationTime()));
               setMaxServiceTime(Integer.parseInt(view.getMaximumProcessingTime()));
              setMaxArrivalTime(Integer.parseInt(view.getMaximumArrivalTime()));
              setMinServiceTime(Integer.parseInt(view.getMinimumProcessingTime()));
             setMinArrivalTime(Integer.parseInt(view.getMinimumArrivalTime()));
                setQueues(Integer.parseInt(view.getNrQueues()));
                setClients(Integer.parseInt((view.getNrClients())));
                
                SimulationManager r1 = new SimulationManager(getTimelimit(),getMinServiceTime(), getMinArrivalTime(),getMaxServiceTime(),getMaxArrivalTime(),getQueues(),getClients(),view);
                Thread t1 = new Thread(r1);

                t1.start();
            });
        }
    }

    public int getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        this.clients = clients;
    }

    public int getQueues() {
        return queues;
    }

    public void setQueues(int queues) {
        this.queues = queues;
    }
}
