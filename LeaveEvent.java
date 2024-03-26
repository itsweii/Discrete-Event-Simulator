public class LeaveEvent implements Event {

    private final Customer customer;
    private final double startTime;

    LeaveEvent(Customer customer, double startTime) {
        this.customer = customer;
        this.startTime = startTime;
    }

    @Override
    public Pair<PQ<Event>, ImList<Server>> execute(PQ<Event> pq,ImList<Server> allServers) {

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
        return 1;
    }

    public double getWT(ImList<Server> allServers) {
        return 0.0;
    }


    @Override
    public String toString() {
        return String.format("%.3f %d leaves", startTime, customer.getName()) + "\n";
    }

}
