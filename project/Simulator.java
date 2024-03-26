import java.util.function.Supplier;

class Simulator {

    private final int numOfServers;
    private final int numOfSelfChecks;
    private final int qmax;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTime;
    private final Supplier<Double> restTime;



    Simulator(int sn, int sc, int qm, ImList<Double> aT, Supplier<Double> sT, Supplier<Double> rT) {
        this.numOfServers = sn;
        this.numOfSelfChecks = sc;
        this.qmax = qm;
        this.arrivalTimes = aT;
        this.serviceTime = sT;
        this.restTime = rT;
    }

    ImList<Server> generateServer() {
        ImList<Server> serList = new ImList<Server>();
        for (int i = 1; i <= numOfServers; i++) {
            serList = serList.add(new HumanServer(i, qmax, restTime));
        }
        for (int i = numOfServers + 1; i <= numOfSelfChecks + numOfServers; i++) {
            serList = serList.add(new SelfCheck(i, qmax));
        }
        return serList;
    }

    PQ<Event> insertArriveEvent() {
        PQ<Event> pq = new PQ<Event>(new EventComparator());
        for (int i = 0; i < arrivalTimes.size(); i++) {
            pq = pq.add(
                    new ArriveEvent(
                            new Customer(i + 1, arrivalTimes.get(i), serviceTime),
                            arrivalTimes.get(i), numOfServers
                    )
            );
        }
        return pq;
    }

    public String simulate() {
        int served = 0;
        int left = 0;
        double waitingTime = 0.0;
        StringBuilder sb = new StringBuilder();
        ImList<Server> serList = this.generateServer();
        PQ<Event> pq = this.insertArriveEvent();

        while (!pq.isEmpty()) {

            Pair<Event, PQ<Event>> pair = pq.poll();
            Event currentEvent = pair.first();
            pq = pair.second();
            waitingTime += currentEvent.getWT(serList);
            sb.append(currentEvent);
            Pair<PQ<Event>, ImList<Server>> execute = currentEvent.execute(pq, serList);
            pq = execute.first();
            serList = execute.second();
            served += currentEvent.getCusServed();
            left += currentEvent.getCusLeft();

        }

        double averageWaitingTime = waitingTime / served;
        if (served == 0) {
            averageWaitingTime = 0;
        }

        sb.append(String.format("[%.3f %d %d]", averageWaitingTime, served, left));
        return sb.toString();
    }
    
}



