package javabasic.dynamic.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/**
 * @author lee
 * @since 6/3/21
 * 注解上有@Inherited 子类继承时注解也被继承，否则不被继承
 */
public class InheritDemo {
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface Test {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface NoInheritTest {
    }

    @Test
    @NoInheritTest
    static class Base {
    }


    class Extension extends Base {
    }

    public static void main(String[] args) {
        System.out.println("声明了@Inherited子类可继承" + Extension.class.isAnnotationPresent(Test.class));
        System.out.println("未声明@Inherited子类未继承" + Extension.class.isAnnotationPresent(NoInheritTest.class));

        System.out.println("Base注解" + Arrays.toString(Base.class.getAnnotations()));
        System.out.println("Extension注解" + Arrays.toString(Extension.class.getAnnotations()));
    }
}
