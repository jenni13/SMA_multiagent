package environment;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Time extends Thread {

    private long epochSecond = System.currentTimeMillis() / 1000l;
    private LocalDateTime dateTime;

    @Override
    public void run() {
        while (true) {
            epochSecond += 1800;
            dateTime = LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.UTC);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy - HH:mm", Locale.US);
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
