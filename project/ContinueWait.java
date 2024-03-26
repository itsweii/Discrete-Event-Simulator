public class ContinueWait implements Event {

    private final Customer customer;
    private final Server server;
    private final double startTime;
    private final int numOfServers;

    ContinueWait(Customer customer, Server server, double startTime, int numOfServers) {
        this.customer = customer;
        this.server = server;
        this.startTime = startTime;
        this.numOfServers = numOfServers;
    }


    @Override
    public Pair<PQ<Event>, ImList<Server>> execute(PQ<Event> pq,ImList<Server> allServers) {
  
        Server ser = allServers.get(server.getNum() - 1);
        double availTime = ser.getAT();
        boolean readytoServe = ser.getWaitQueue().isEmpty() || 
                ser.getWaitQueue().get(0).getName() == customer.getName();

        if (ser.getNum() <= numOfServers) { 

            if (readytoServe && availTime <= startTime) {
                pq = pq.add(new ServeEvent(customer, ser, startTime, numOfServers));
                return new Pair<>(pq,allServers);

            }

        } 
        if (ser.getNum() > numOfServers) { 

            for (int i = numOfServers; i < allServers.size(); i++) {
                Server selfServer = allServers.get(i);
                availTime = Math.min(availTime, allServers.get(i).getAT());
                
                if (readytoServe && availTime <= startTime) {
                    pq = pq.add(new ServeEvent(customer, selfServer, startTime, numOfServers));
                    return new Pair<>(pq,allServers);
                }
            }
        }

        pq = pq.add(new ContinueWait(customer, ser, availTime, numOfServers));
        return new Pair<>(pq,allServers);
        

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

    @Override
    public double getWT(ImList<Server> allServers) {

        Server ser = allServers.get(server.getNum() - 1);
        if (ser.getNum() <= numOfServers) {
            double availTime = ser.getAT();
            return availTime - startTime;

        } else {
            double availTime = ser.getAT();
            for (int i = numOfServers; i < allServers.size(); i++) {

                availTime = Math.min(availTime, allServers.get(i).getAT());
            }
            return availTime - startTime;
        }
        
        
    }

    @Override
    public String toString() {
        return "";
    }

}