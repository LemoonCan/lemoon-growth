package javabasic.dynamic.annotation;

import com.alibaba.fastjson2.JSON;
import console.ColorfulPrintln;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lee
 * @since 6/3/21
 */
public class MethodDemo {
    @Decorate(value = {1, 2, 3})
    public void plant(@Required String tree,
                      @Required @Flower(value = {"x", "y", "z"}) String water) {
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = MethodDemo.class.getMethod("plant", String.class, String.class);
        System.out.println(JSON.toJSONString(method.getParameterAnnotations()));

        System.out.println();
        ColorfulPrintln.colorfulBack("获取方法上的注解");
        Flower flower = method.getAnnotation(Flower.class);
        System.out.println("flower: " + flower);

        Decorate decorate = method.getAnnotation(Decorate.class);
        System.out.println("decorate: " + decorate.getClass().getName());
        System.out.println("decorate value：" + Arrays.toString(decorate.value()));
    }
}
