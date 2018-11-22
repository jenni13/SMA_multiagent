package environment;

import device.Lamp;
import device.LightSensor;
import device.Shutter;
import fr.irit.smac.amak.Environment;
import fr.irit.smac.amak.Scheduling;
import fr.irit.smac.lxplot.LxPlot;
import fr.irit.smac.lxplot.commons.ChartType;
import main.Config;

import java.util.ArrayList;
import java.util.List;

public class Room extends Environment {

    private List<Shutter> shutters;
    private List<Lamp> lamps;
    private List<LightSensor> indoorSensors;
    private List<LightSensor> outdoorSensors;

    private Brightness indoorBrightness;
    private Brightness outdoorBrightness;
    private Time time;

    public Room() {
        super(Scheduling.DEFAULT);
    }

    @Override
    public void onInitialization() {
        //
        time = new Time();
        time.start();

        //
        outdoorBrightness = new Brightness(0);
        indoorBrightness = new Brightness(0);

        initializeDevices();

    }

    @Override
    public void onCycle() {
        outdoorBrightness.updateAccordingToSunLight(time.getDateTime());

        setIndoorBrightness(
                (getIndoorBrightnessAfterConsideringWindows() + getIndoorBrightnessAfterConsideringLamps()) / 2
        );

        writeToConsole();

        LxPlot.getChart("Luminosité exterieur et interieur", ChartType.BAR, true).add(0, getOutdoorBrightness());
        LxPlot.getChart("Luminosité exterieur et interieur", ChartType.BAR, true).add(1, getIndoorBrightness());
    }

    private double getIndoorBrightnessAfterConsideringWindows(){
        double openingAverage = 0;
        //moyenne
        for (Shutter shutter: shutters)
            openingAverage += shutter.getLevel()/Config.SHUTTERS_NUMBER;

        if (((outdoorBrightness.getIntensity() * (openingAverage/100)) - Config.LOST_BRIGHTNESS) > 0)
            return (outdoorBrightness.getIntensity() * (openingAverage/100)) - Config.LOST_BRIGHTNESS;
        return 0;
    }

    private double getIndoorBrightnessAfterConsideringLamps() {
        double lightingAverage = 0;
        //moyenne
        for (Lamp lamp: lamps) {
            lightingAverage += lamp.getLightIntensity()/Config.LAMPS_NUMBER;
        }

        return lightingAverage;
    }

    private void writeToConsole() {
        System.out.println("---------------------------------------------------");

        System.out.println(time.toString());
        System.out.println("outdoor brightness : " + getOutdoorBrightness());
        System.out.println("indoor brightness  : " + getIndoorBrightness());
        System.out.print("shutters state    :   ");
        for (Shutter shutter: shutters) {
            System.out.print(" // (" + shutters.indexOf(shutter) + ") " +shutter.getLevel());
        }
        System.out.println();

        System.out.print("lamps state       :   ");
        for (Lamp lamp: lamps) {
            System.out.print(" // (" + lamps.indexOf(lamp) + ") " + lamp.getLightIntensity());
        }
        System.out.println("");
        System.out.println("total consumption: " + getdevicesTotalConsumption() + " kWh");
    }

    private float getdevicesTotalConsumption() {
        int total = 0;
        for (Lamp lamp: lamps) {
            total += lamp.getTotalConsumption();
        }
        for (LightSensor sensor: indoorSensors) {
            total += sensor.getTotalConsumption();
        }
        for (LightSensor sensor: outdoorSensors) {
            total += sensor.getTotalConsumption();
        }
        for (Shutter shutter: shutters) {
            total += shutter.getTotalConsumption();
        }
        return (float) total/1000;
    }

    private void initializeDevices() {

        shutters = new ArrayList<Shutter>();
        lamps = new ArrayList<Lamp>();
        indoorSensors = new ArrayList<LightSensor>();
        outdoorSensors = new ArrayList<LightSensor>();

        //
        for (int i = 0; i < Config.SHUTTERS_NUMBER; i++) {
            shutters.add(new Shutter(Config.SHUTTER_CONSUMPTION_POWER, 0));
        }

        //
        for (int i = 0; i < Config.LAMPS_NUMBER; i++) {
            lamps.add(new Lamp(Config.LAMP_CONSUMPTION_POWER, 0));
        }

        //
        for (int i = 0; i < Config.INDOOR_SENSORS_NUMBER; i++) {
            indoorSensors.add(new LightSensor(Config.SENSOR_CONSUMPTION_POWER, 0));
        }

        //
        for (int i = 0; i < Config.OUTDOOR_SENSORS_NUMBER; i++) {
            outdoorSensors.add(new LightSensor(Config.SENSOR_CONSUMPTION_POWER, 0));
        }
    }

    public double getIndoorBrightness() {
        return indoorBrightness.getIntensity();
    }

    public void setIndoorBrightness(double indoorBrightness) {
        this.indoorBrightness.setIntensity(indoorBrightness);
    }

    public double getOutdoorBrightness() {
        return outdoorBrightness.getIntensity();
    }

    public void setOutdoorBrightness(double outdoorBrightness) {
        this.outdoorBrightness.setIntensity(outdoorBrightness);
    }

    public List<Shutter> getShutters() {
        return shutters;
    }

    public void setShutters(List<Shutter> shutters) {
        this.shutters = shutters;
    }

    public List<Lamp> getLamps() {
        return lamps;
    }

    public void setLamps(List<Lamp> lamps) {
        this.lamps = lamps;
    }

    public List<LightSensor> getIndoorSensors() {
        return indoorSensors;
    }

    public void setIndoorSensors(List<LightSensor> indoorSensors) {
        this.indoorSensors = indoorSensors;
    }

    public List<LightSensor> getOutdoorSensors() {
        return outdoorSensors;
    }

    public void setOutdoorSensors(List<LightSensor> outdoorSensors) {
        this.outdoorSensors = outdoorSensors;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
