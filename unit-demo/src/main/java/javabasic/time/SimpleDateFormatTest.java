package javabasic.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author lee
 * @since 2022/10/8
 */
public class SimpleDateFormatTest {
    public static void main(String[] args) throws ParseException {
        System.out.println("默认时区："+ TimeZone.getDefault().getID());

        String s = "2020-11-11 11:11:11";
        testParseDiffZone(s);
        System.out.println();
        testFormatDiffZone(s);
    }

    private static void testParseDiffZone(String s)throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //默认时区解析
        Date date1 = dateFormat.parse(s);
        System.out.println(date1 + ":" + date1.getTime());

        dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        //纽约时区解析 +13?
        Date date2 = dateFormat.parse(s);
        System.out.println(date2 + ":" + date2.getTime());
    }

    private static void testFormatDiffZone(String s)throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //默认时区
        Date date = dateFormat.parse(s);

        //默认时区格式化
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(date));
        //纽约时区格式化
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(date));
    }
}
