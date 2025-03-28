package javabasic.dynamic.annotation.application.format;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @author lee
 * @since 6/3/21
 *
 * 注解功能实现的demo
 */
public class SimpleFormatter {
    static String parse(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);

            String key = field.getAnnotation(Label.class).value();
            Format format = field.getAnnotation(Format.class);
            Object value;
            if(Objects.isNull(format)){
                value = field.get(obj);
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat(format.pattern());
                sdf.setTimeZone(TimeZone.getTimeZone(format.timeZone()));
                value = sdf.format(field.get(obj));
            }
            sb.append(key+": "+value+"\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws ParseException, IllegalAccessException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Student student = new Student("Little",sdf.parse("2020/01/01"),100);
        System.out.println(parse(student));
    }
}
