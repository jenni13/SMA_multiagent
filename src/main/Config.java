package main;

import org.apache.commons.lang.math.IntRange;

import java.util.Arrays;
import java.util.List;

public class Config {

    /**
     *
     */
    public final static int REQUIRED_LUMINOSITY = 60;

    /**
     *
     */
    public final static int TIME_SPEED = 1;

    /**
     *
     */
    public final static int LOST_BRIGHTNESS = 10;

    /**
     *
     */
    public final static int SHUTTERS_NUMBER = 4;


    /**
     *
     */
    public final static int LAMPS_NUMBER = 4;


    /**
     *
     */
    public final static int OUTDOOR_SENSORS_NUMBER = 4;

    /**
     *
     */
    public final static int INDOOR_SENSORS_NUMBER = 4;

    /**
     *
     */
    public final static int LAMP_CONSUMPTION_POWER = 2;

    /**
     *
     */
    public final static int SHUTTER_CONSUMPTION_POWER = 4;

    /**
     *
     */
    public final static int SENSOR_CONSUMPTION_POWER = 1;

    /**
     *
     */
    public final static List<String> COURSES_DAYS = Arrays.asList("MONDAY", "TUESDAY","THURSDAY", "WEDNESDAY", "FRIDAY");

    /**
     *
     */
    public final static IntRange COURSES_SCHEDULES_INTERVAL = new IntRange(8, 17); // 8h -> 17h59

    /**
     *
     */
    public final static List<String> ROBOTIC_ACTIVITIES_DAYS = Arrays.asList("MONDAY", "TUESDAY","THURSDAY");

    /**
     *
     */
    public final static IntRange ROBOTIC_ACTIVITIES_SCHEDULES_INTERVAL = new IntRange(18, 20); // 18h -> 20h59

}
