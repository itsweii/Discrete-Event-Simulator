import java.util.function.Supplier;

class Customer {
    private final int name;
    private final double arrivalTime;
    private final Supplier<Double> serviceTime;

    public Customer(int name, double arrivalTime, Supplier<Double> serviceTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getName() {
        return this.name;
    }

    public double getArrTime() {
        return this.arrivalTime;
    }

    public double getSerTime() {
        return this.serviceTime.get();
    }

}
