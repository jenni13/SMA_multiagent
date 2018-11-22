package agent;

import device.LightSensor;
import environment.Room;
import device.Shutter;
import fr.irit.smac.amak.Agent;
import fr.irit.smac.lxplot.LxPlot;
import fr.irit.smac.lxplot.commons.ChartType;
import main.Config;

import java.time.format.TextStyle;
import java.util.Locale;

public class SmartWindowAgent extends Agent<MyAMAS, Room> {

    private int id;

    private Shutter shutter;

    private LightSensor sensor;

    public SmartWindowAgent(int id, MyAMAS amas) {
        super(amas);
        this.id = id;
        this.shutter = getEnvironment().getShutters().get(id);
        this.sensor = getEnvironment().getOutdoorSensors().get(id);
    }

    @Override
    protected void onDecideAndAct() {
        String currentDay = getEnvironment().getTime().getDateTime().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US).toUpperCase();
        int currentHour = getEnvironment().getTime().getDateTime().getHour();
        sensor.setLuminosity(getEnvironment().getOutdoorBrightness());

        if (sensor.getLuminosity() == 0) {
            shutter.close();
        } else if (Config.COURSES_DAYS.contains(currentDay) || Config.ROBOTIC_ACTIVITIES_DAYS.contains(currentDay)) {
            if (Config.COURSES_SCHEDULES_INTERVAL.containsInteger(currentHour) || Config.ROBOTIC_ACTIVITIES_SCHEDULES_INTERVAL.containsInteger(currentHour)) {

                if (getEnvironment().getIndoorBrightness() >= Config.REQUIRED_LUMINOSITY) {
                    shutter.setLevel(shutter.getLevel() - 5);
                } else
                    shutter.open();
                sensor.consume();
            } else {
                shutter.close();
            }
        } else {
            shutter.close();
        }
    }

    @Override
    protected void onUpdateRender() {
        LxPlot.getChart("Etat Volets", ChartType.BAR).add(id, shutter.getLevel());
    }
}
