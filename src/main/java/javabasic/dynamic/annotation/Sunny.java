package javabasic.dynamic.annotation;

import javabasic.dynamic.reflect.Lemoon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lee
 * @date 6/2/21
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Sunny {
    int identity();
    String desc() default "Sunny";
}
