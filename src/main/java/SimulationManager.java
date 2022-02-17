import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimulationManager implements Runnable {
   public int timeLimit;
    public int maxServiceTime;
    public int minServicetime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numberOfServers;
    public int numberOfClients;
    public View view;
   // private Controller controller;
    public SelectionPolicy selectionPolicy=SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler;
    private ArrayList<Client> generatedClients= new ArrayList<Client>();

    public SimulationManager(int timeLimit, int maxServiceTime, int minArrivalTime, int minServicetime, int maxArrivalTime, int numberOfServers, int numberOfClients, View view) {
        this.timeLimit = timeLimit;
        this.maxServiceTime =maxServiceTime;
        this.minServicetime = minServicetime;
        this.minArrivalTime=minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
       // this.controller=controller;
        this.scheduler= new Scheduler(numberOfServers,numberOfClients);
        for(int i=0;i<numberOfServers;i++)
        {
            synchronized (Scheduler.class) {
                this.scheduler.getServers().add(new Server(i));
                this.scheduler.getServers().get(i).start();
            }
        }

        this.scheduler.changeStrategy(selectionPolicy);

        this.view=view;
          this.generateClients();
    }
  private void generateClients()
    {
        for(int i=0;i<numberOfClients;i++)
        {  Random rand = new Random();
            int randomTS= rand.nextInt(maxServiceTime-minServicetime)+minServicetime;
            int randomTA=  rand.nextInt(maxArrivalTime-minArrivalTime)+minArrivalTime;
            int randomID= rand.nextInt(numberOfClients);
            Client client= new Client(i,randomTA,randomTS);
            generatedClients.add(client);
        }
        /*Client client1= new Client(1,2,2);
        Client client2= new Client(2,3,3);
        Client client3= new Client(3,4,3);
        Client client4= new Client(4,10,2);
        generatedClients.add(client1);
        generatedClients.add(client2);
        generatedClients.add(client3);
        generatedClients.add(client4);*/

        Collections.sort(generatedClients);
    }


    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public ArrayList<Client> getGeneratedClients() {
        return generatedClients;
    }

    public void setGeneratedClients(ArrayList<Client> generatedClients) {
        this.generatedClients = generatedClients;
    }

    @Override
    public void run() {
        FileWriter fw = null;
        File file = new File("testout.txt");

        BufferedWriter out = null;

        int currentTime=0;
        int time=0;
        int max=0;
        int avgService=0;
        int process=0;
        int nrc=this.generatedClients.size();
        String fileW="";
        for(int i=0;i<this.generatedClients.size();i++)
        {
            avgService=avgService+this.generatedClients.get(i).getTimeService();
            process=process+this.generatedClients.get(i).getWaitingTime();
        }
        double avg= (double)avgService/nrc;
        int done=0;
        while (currentTime<this.timeLimit && done==0)
        {
            try {
                fw=new FileWriter(file,true);

            } catch (IOException e) {
                e.printStackTrace();
            }
            String s="";
            s=s+"Time "+ currentTime+"\n";
            fileW=fileW+"Time "+ currentTime+"\n";
            System.out.println("Time "+currentTime);
            int ok=0;
           int test=0;
            try {
                Client client = new Client(0,0,0);
                for(int count =0;count<this.generatedClients.size();count++){
                    if(this.generatedClients.get(count).getTimeArrival()==currentTime)
                    {     client = this.generatedClients.get(count);
                          ok=1;
                        this.scheduler.dispatchClient(client);
                        process=process+this.generatedClients.get(count).getWaitingTime();
                        this.generatedClients.remove(client);
                        count--;
                    }
                }
                int i=1;         int count=0;

                for(int j=0;j<scheduler.getServers().size();j++)
                {
                    if(scheduler.getServers().get(j).getClientsArray().size()!=0)
                    {
                        break;
                    }
                    if(this.generatedClients.size()==0)
                    {
                        done=1;   Thread.sleep(500);
                    }
                }
                for(Server server : scheduler.getServers())
                {

                    s=s+"Queue"+i+":";
                    fileW=fileW+"Queue"+i+":";
                    System.out.print("Queue "+i+" :");
                    if(server.isOpen()==false)
                    {
                        System.out.print("closed");
                        fileW=fileW+"closed"+"\n";;
                        s=s+"closed";
                    }
                    int ok1=0;
                    for(Client c:server.getClientsArray()) {
                        if (ok1 == 0) {
                            ok1 = 1;
                            s=s+"(" + c.getId() + " " + c.getTimeArrival() + " " + (c.getTimeService()) + ") ";
                            fileW=fileW+"(" + c.getId() + " " + c.getTimeArrival() + " " + (c.getTimeService()) + ") ";
                            System.out.print("(" + c.getId() + " " + c.getTimeArrival() + " " + (c.getTimeService()) + ") ");
                            count++;
                            c.setTimeService(c.getTimeService() - 1);
                        } else          {
                            count++;
                            s=s+"(" + c.getId() + " " + c.getTimeArrival() + " " + (c.getTimeService()) + ") ";
                            fileW=fileW+"(" + c.getId() + " " + c.getTimeArrival() + " " + (c.getTimeService()) + ") ";
                            System.out.print("(" + c.getId() + " " + c.getTimeArrival() + " " + (c.getTimeService()) + ") ");
                        }
                        fileW=fileW+'\n';
                      if(count>max)
                      {
                          max=count;
                           time=currentTime;
                      }
                    }
                    i++;
                    s=s+"\n";
                    System.out.println();
                }
                 s=s+"Clienti ramasi";
                fileW=fileW+"Clienti ramasi"+"\n";
                for(Client c2:this.generatedClients)
                {
                  s=s+"("+c2.getId()+ " "+ c2.getTimeArrival()+ " "+c2.getTimeService() +")";
                    fileW=fileW+"("+c2.getId()+ " "+ c2.getTimeArrival()+ " "+c2.getTimeService() +")";
                    System.out.print("("+c2.getId()+ " "+ c2.getTimeArrival()+ " "+c2.getTimeService() +") ");
                }
                s=s+"\n";
                fileW=fileW+'\n';
                view.setText(s);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int averageTime=0;
        int nrC=0;
        for(int i=0;i<numberOfServers;i++)
        {
            averageTime=averageTime+this.getScheduler().getServers().get(i).getWaitingPeriod().get();
            nrC=nrC+this.getScheduler().getServers().get(i).getSize();
        }
        double waitTime=(double)averageTime/nrc;      double processTime=(double)process/(nrc*numberOfServers);
        String rez="";
        rez=rez+"Average Service Time "+avg+" Average processing time "+processTime+" Peak Hour "+time;
        view.setText(rez);
        try {
            fw.write(fileW);    fw.write(rez);     fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
