public class ArriveEvent implements Event {

    private final Customer customer;
    private final double startTime;
    private final int numOfServers;

    ArriveEvent(Customer customer, double startTime, int numOfServers) {
        this.customer = customer;
        this.startTime = startTime;
        this.numOfServers = numOfServers;
    }

    @Override
    public Pair<PQ<Event>, ImList<Server>> execute(PQ<Event> pq,ImList<Server> allServers) {

        for (Server ser : allServers) {
            if (ser.getAT() <= startTime) {
                pq = pq.add(new ServeEvent(customer, ser, customer.getArrTime(), numOfServers));
                return new Pair<>(pq,allServers);
            }
        }

        for (Server ser: allServers) {
            if (!ser.isFull()) {
                pq = pq.add(new WaitEvent(customer, ser, customer.getArrTime(), numOfServers));
                return new Pair<>(pq,allServers);
            }
        }

        pq = pq.add(new LeaveEvent(customer, startTime));
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

    public double getWT(ImList<Server> allServers) {
        return 0.0;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives", customer.getArrTime(), customer.getName()) + "\n";
    }

}









