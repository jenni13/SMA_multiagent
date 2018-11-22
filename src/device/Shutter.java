package device;

import static java.lang.Math.abs;

public class Shutter extends Device {

    //between 0 and 100
    private double level;

    public Shutter(int consumptionPower, double level) {
        super(consumptionPower);
        this.level = level;
    }

    @Override
    public void consume() {
        this.totalConsumption += this.consumptionPower;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double newlevel) {
        final int diff = Math.abs((int)level - (int)newlevel);
        for (int i = 0; i < diff; i++) {
            this.consume();
        }
        this.level = newlevel;
    }

    public void close() {
        this.setLevel(0);
    }

    public void open() {
        this.setLevel(100);
    }
}
