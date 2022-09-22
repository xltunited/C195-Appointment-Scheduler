package main.Utilities;

import java.sql.Date;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Utility class used to convert times and dates to and from UTC to the system's default timezone
 */
public class TimeConverter {

    /**
     * @param date the date object obtained from the database
     * @param time the time object obtained from the database
     * @return converted time and date to system's default timezone
     */
    public static String convertDateTimeToDefault(Date date, Time time){

        LocalDate javaDate = date.toLocalDate();
        LocalTime javaTime = time.toLocalTime();

        LocalDateTime converted =LocalDateTime.of(javaDate, javaTime).atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return converted.format(formatter);

    }

    /**
     * @param time the time object obtained from the user
     * @return the converted date
     */
    public static LocalDateTime convertToUTC(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

    /**
     * Gets the offset between system timezone and EST for the purpose of checking inputs against business hours.
     * @return Integer offset between system time and EST.*/
    public static LocalDateTime convertToEST(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
    }

}
