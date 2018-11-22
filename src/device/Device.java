package device;

abstract class Device {
    int totalConsumption;
    final int consumptionPower;

    Device(int consumptionPower) {
        this.totalConsumption = 0;
        this.consumptionPower = consumptionPower;
    }

    abstract void consume();

    public int getTotalConsumption() {
        return totalConsumption;
    }
}
