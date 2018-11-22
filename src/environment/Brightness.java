package environment;

import java.time.LocalDateTime;

public class Brightness {

    //between 0 and 100
    private double intensity;

    public Brightness(double intensity) {
        this.intensity = intensity;
    }

    public void updateAccordingToSunLight(LocalDateTime dateTime){
        int elapsedMinutes = dateTime.getHour() * 60 + dateTime.getMinute();

        int pick = 12 * 60;

        if ((elapsedMinutes < 6 * 60) || (elapsedMinutes > 18 * 60))
            intensity = 0;
        else
            intensity = Math.abs(100 * Math.sin((360 + elapsedMinutes) / (72 * Math.PI)));
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
}
