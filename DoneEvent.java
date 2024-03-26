public class DoneEvent implements Event {

    private final Customer customer;
    private final Server server;
    private final double startTime;
    private final int numOfServers;

    DoneEvent(Customer customer, Server server, double startTime, 
        int numOfServers) {
        this.customer = customer;
        this.server = server;
        this.startTime = startTime;
        this.numOfServers = numOfServers;
    }

    @Override
    public Pair<PQ<Event>, ImList<Server>> execute(PQ<Event> pq,ImList<Server> allServers) {

        Server ser = allServers.get(server.getNum() - 1);
        allServers = ser.finishServing(startTime + ser.getRT(), allServers); 
        return new Pair<>(pq, allServers);
      
    }

    public double getStartTime() {
        return startTime;
    }

    public Customer customer() {
        return customer;
    }

    public int getCusServed() {
        return 0;

    }
 
    public int getCusLeft() {
        return 0;
    }

    public double getWT(ImList<Server> allServers) {
        return 0.0;
    }

    @Override
    public String toString() {

        if (server.getNum() <= numOfServers) {
            return String.format("%.3f %d done serving by %d", startTime,
        customer.getName(), server.getNum()) + "\n";
        } 
        return String.format("%.3f %d done serving by self-check %d", startTime,
        customer.getName(), server.getNum()) + "\n";
    }

}
