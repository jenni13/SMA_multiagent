package device;

public class LightSensor extends Device {

    private double luminosity;

    public LightSensor(int consumptionPower, double luminosity) {
        super(consumptionPower);
        this.luminosity = luminosity;
    }

    @Override
    public void consume() {
        this.totalConsumption += this.consumptionPower;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }

    public void shutDown() {
        this.luminosity = 0;
    }
}
