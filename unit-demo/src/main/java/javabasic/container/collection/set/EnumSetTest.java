package javabasic.container.collection.set;

import java.time.DayOfWeek;
import java.util.EnumSet;

/**
 * @author lee
 * @since 6/28/21
 */
public class EnumSetTest {
    public static void main(String[] args) {
        EnumSet enumSet = EnumSet.noneOf(DayOfWeek.class);
        enumSet.add(DayOfWeek.SATURDAY);
        enumSet.add(DayOfWeek.SUNDAY);
    }
}
