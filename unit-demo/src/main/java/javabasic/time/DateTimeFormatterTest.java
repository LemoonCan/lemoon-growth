package javabasic.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lee
 * @since 2022/10/8
 */
public class DateTimeFormatterTest {
    public static void main(String[] args) {
        String s = "2020-11-11 11:11:11";
        ZoneId timeZoneNY = ZoneId.of("America/New_York");
        ZoneId timeZoneSH = ZoneId.of("Asia/Shanghai");
        ZoneId timeZoneJST = ZoneOffset.ofHours(9);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.parse(s, inputFormatter), timeZoneJST);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        System.out.println(dateTime.format(outputFormatter));
        System.out.println(timeZoneNY.getId() + " " + outputFormatter.withZone(timeZoneNY).format(dateTime));
        System.out.println(timeZoneSH.getId() + " " + outputFormatter.withZone(timeZoneSH).format(dateTime));
        System.out.println(timeZoneJST.getId() + " " + outputFormatter.withZone(timeZoneJST).format(dateTime));
    }
}
