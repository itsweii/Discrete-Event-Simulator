class SelfCheck implements Server {

    private final int num;
    private final int maxQueSize;
    private final double availTime;
    private final ImList<Customer> waitEvents;

    SelfCheck(int name, int qm) {
        
        this.num = name;
        this.maxQueSize = qm;
        this.availTime = 0.0;
        this.waitEvents = new ImList<>();

    }

    SelfCheck(int name,int qm, ImList<Customer> waitEvents, double aT) {
        
        this.num = name;
        this.maxQueSize = qm; 
        this.availTime = aT;
        this.waitEvents = waitEvents;

    }

    public int getNum() {
        return this.num;
    }
    
    public ImList<Customer> getWaitQueue() {
        return waitEvents;
    }

    public double getRT() {
        return 0.0;
    }


    public double getAT() {
        return this.availTime;
    }


    public boolean isFull() {
        return this.waitEvents.size() == this.maxQueSize;
    }

    public ImList<Server> beginServing(double aT, ImList<Server> allServers) {

        SelfCheck ser = new SelfCheck(num, maxQueSize, waitEvents, aT);
        allServers = allServers.set(num - 1, ser);

        return allServers;
    }

    public ImList<Server> finishServing(double aT, ImList<Server> allServers) {

        SelfCheck ser = new SelfCheck(num, maxQueSize, waitEvents, aT);
        allServers = allServers.set(num - 1, ser);

        return allServers;
        
    }

    public ImList<Server> beginQueuing(Customer cus, ImList<Server> allServers, int numOfServers) {

        ImList<Customer> updatedWQ = this.waitEvents.add(cus);

        for (int i = numOfServers; i < allServers.size(); i++) {

            Server ser = allServers.get(i);
            SelfCheck selfCheck = new SelfCheck(ser.getNum(), 
                this.maxQueSize, updatedWQ, ser.getAT());
            allServers = allServers.set(i, selfCheck);
        }
        
        return allServers;
        
    }
    
    public ImList<Server> finishQueuing(Customer cus, ImList<Server> allServers, int numOfServers) {

        ImList<Customer> updatedWQ = this.waitEvents.remove(0);

        for (int i = numOfServers; i < allServers.size(); i++) {

            Server ser = allServers.get(i);
            SelfCheck selfCheck = new SelfCheck(ser.getNum(), 
                this.maxQueSize, updatedWQ, ser.getAT());
            allServers = allServers.set(i, selfCheck);
        }
        
        return allServers;
    }


}
    

