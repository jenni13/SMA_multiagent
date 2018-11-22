package agent;

import environment.Room;
import device.Lamp;
import device.LightSensor;
import fr.irit.smac.amak.Agent;
import fr.irit.smac.lxplot.LxPlot;
import fr.irit.smac.lxplot.commons.ChartType;
import main.Config;

import java.time.format.TextStyle;
import java.util.Locale;

public class SmartLightingAgent extends Agent<MyAMAS, Room> {

    private int id;

    private Lamp lamp;

    private LightSensor sensor;

    public SmartLightingAgent(int id, MyAMAS amas) {
        super(amas);
        this.id = id;
        this.lamp = getEnvironment().getLamps().get(id);
        this.sensor = getEnvironment().getOutdoorSensors().get(id);
    }

    @Override
    protected void onDecideAndAct() {
        String currentDay = getEnvironment().getTime().getDateTime().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US).toUpperCase();
        int currentHour = getEnvironment().getTime().getDateTime().getHour();

        sensor.setLuminosity(getEnvironment().getIndoorBrightness());

        if (Config.COURSES_DAYS.contains(currentDay) || Config.ROBOTIC_ACTIVITIES_DAYS.contains(currentDay)) {
            if (Config.COURSES_SCHEDULES_INTERVAL.containsInteger(currentHour) || Config.ROBOTIC_ACTIVITIES_SCHEDULES_INTERVAL.containsInteger(currentHour)) {
                if (sensor.getLuminosity() > Config.REQUIRED_LUMINOSITY) {
                    lamp.setLightIntensity(lamp.getLightIntensity() - 5);
                } else {
                    lamp.setLightIntensity(lamp.getLightIntensity() + 5);
                }
                sensor.consume();
                lamp.consume();
            } else {
                lamp.setLightIntensity(0);
            }
        } else {
            lamp.setLightIntensity(0);
        }
    }

    @Override
    protected void onUpdateRender()
    {
        LxPlot.getChart("Etat des Lampes", ChartType.BAR, true).add(id, lamp.getLightIntensity());
    }
}
