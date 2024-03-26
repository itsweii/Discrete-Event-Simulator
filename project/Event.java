interface Event {

    public Pair<PQ<Event>, ImList<Server>> execute(PQ<Event> pq,ImList<Server> allServers);

    public double getStartTime();

    public Customer customer();

    public int getCusServed();
 
    public int getCusLeft();

    public double getWT(ImList<Server> allServers);

    public String toString();
    
}
