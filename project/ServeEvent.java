public class ServeEvent implements Event {

    private final Customer customer;
    private final double startTime;
    private final Server server;
    private final int numOfServers;

    ServeEvent(Customer customer, Server server, double startTime, 
        int numOfServers) {
        this.customer = customer;
        this.startTime = startTime;
        this.server = server;
        this.numOfServers = numOfServers;
    }

    @Override
    public Pair<PQ<Event>, ImList<Server>> execute(PQ<Event> pq,ImList<Server> allServers) {

        Server ser = allServers.get(server.getNum() - 1);

       
        if (!ser.getWaitQueue().isEmpty()) { 
            if (ser.getWaitQueue().get(0).getName() 
                == customer.getName()) { 
                allServers = ser.finishQueuing(customer, allServers, numOfServers); 
            } 
            
        }
        Server newSer = allServers.get(server.getNum() - 1);

        double endSerTime = startTime + customer.getSerTime();
        allServers = newSer.beginServing(endSerTime, allServers); 
        pq = pq.add(new DoneEvent(customer, ser, endSerTime, numOfServers));
        return new Pair<>(pq, allServers);
      
    }

    public double getStartTime() {
        return startTime;
    }

    public Customer customer() {
        return customer;
    }

    public int getCusServed() {
        return 1;

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
            return String.format("%.3f %d serves by %d", startTime,
            customer.getName(), server.getNum()) + "\n";
        }

        return String.format("%.3f %d serves by self-check %d", startTime,
            customer.getName(), server.getNum()) + "\n";
    }

}