package javabasic.dynamic.annotation.application.format;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lee
 * @date 6/3/21
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Format {
    String pattern() default "yyyy-MM-dd HH:mm:ss";
    String timeZone() default "GMT+8";
}
