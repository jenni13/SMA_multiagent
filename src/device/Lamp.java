package device;

public class Lamp extends Device {

    //between 0 and 100
    private double lightIntensity;


    public Lamp(int consumptionPower, double lightIntensity) {
        super(consumptionPower);
        this.lightIntensity = lightIntensity;
    }

    @Override
    public void consume() {
        this.totalConsumption += lightIntensity * consumptionPower;
    }

    public double getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(double lightIntensity) {
        this.lightIntensity = lightIntensity;
    }
}
