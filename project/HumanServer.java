import java.util.function.Supplier;

class HumanServer implements Server {
    
    private final int num;
    private final int maxQueSize;
    private final ImList<Customer> waitEvents;
    private final Supplier<Double> restTime;
    private final double availTime;

    HumanServer(int name, int qm, Supplier<Double> rT) {
        
        this.num = name;
        this.maxQueSize = qm;
        this.restTime = rT;
        this.waitEvents = new ImList<>();
        this.availTime = 0.0;

    }

    HumanServer(int name,int qm, ImList<Customer> waitEvents,
        Supplier<Double> rT, double aT) {
        
        this.num = name;
        this.maxQueSize = qm; 
        this.restTime = rT;
        this.waitEvents = waitEvents;
        this.availTime = aT;

    }

    public int getNum() {
        return this.num;
    }

    public ImList<Customer> getWaitQueue() {
        return waitEvents;
    }

    public double getRT() {
        return this.restTime.get();
    }

    public double getAT() {
        return this.availTime;
    }


    public boolean isFull() {
        return this.waitEvents.size() == this.maxQueSize;
    }

    public ImList<Server> beginServing(double aT, ImList<Server> allServers) {
        HumanServer ser = new HumanServer(num, maxQueSize, waitEvents, restTime, aT);
        allServers = allServers.set(num - 1, ser);

        return allServers;
    }

    public ImList<Server> finishServing(double aT, ImList<Server> allServers) {
        HumanServer ser = new HumanServer(num, maxQueSize, waitEvents, restTime, aT);
        allServers = allServers.set(num - 1, ser);

        return allServers;

    }


    @Override
    public ImList<Server> beginQueuing(Customer cus, ImList<Server> allServers, int numOfServers) {
        ImList<Customer> updatedWQ = this.waitEvents.add(cus);
        HumanServer ser = new HumanServer(num, maxQueSize, updatedWQ, restTime, availTime);
        allServers = allServers.set(num - 1, ser);
        return allServers;
    }




    public ImList<Server> finishQueuing(Customer customer, ImList<Server> allServers, 
        int numOfServers) {
            
        ImList<Customer> updatedWQ = this.waitEvents.remove(0);
        HumanServer ser = new HumanServer(num, maxQueSize, updatedWQ, restTime, availTime);
        allServers = allServers.set(num - 1, ser);
        return allServers;

    }




}
