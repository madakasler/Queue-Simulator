public class Client implements Comparable<Client> {
    private int id;
    private int timeArrival;
    private int timeService;
    private int waitingTime;

    public Client(int id, int timeArrival, int timeService) {
        this.id = id;
        this.timeArrival = timeArrival;
        this.timeService = timeService;
        this.waitingTime=0;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(int timeArrival) {
        this.timeArrival = timeArrival;
    }

    public int getTimeService() {
        return timeService;
    }

    public void setTimeService(int timeService) {
        this.timeService = timeService;
    }


    @Override
    public int compareTo(Client o) {
        if(this.getTimeArrival()>o.getTimeArrival())
        {
            return 1;
        }
        else {
            return -1;
        }
    }


}
