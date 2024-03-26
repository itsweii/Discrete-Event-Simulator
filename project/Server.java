interface Server {

    public int getNum();

    public double getRT();

    public double getAT();

    public boolean isFull();

    public ImList<Customer> getWaitQueue();

    public ImList<Server> beginQueuing(Customer customer, 
        ImList<Server> allServers, int numOfServers);

    public ImList<Server> finishQueuing(Customer customer, 
        ImList<Server> allServers, int numOfServers);

    public ImList<Server> beginServing(double endSerTime, ImList<Server> allServers);

    public ImList<Server> finishServing(double availTime, ImList<Server> allServers);


    
}
