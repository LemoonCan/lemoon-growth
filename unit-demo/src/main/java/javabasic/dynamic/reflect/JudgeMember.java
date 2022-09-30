package javabasic.dynamic.reflect;

import com.alibaba.fastjson2.JSON;
import console.ColorfulPrintln;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * @author lee
 * @date 5/27/21
 * 1.Declared可以查询到当前类所有成员变量,非 declared可以查到当前类及父类public变量
 * 2.所有成员变量 accessible 都为 false，
 * public 及 default 成员变量默认可以访问(get/set 都可以)
 * private成员变量默认不可以访问(get/set 都不可以)，需调用setAccessible(true)
 */
public class JudgeMember {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = Lemoon.class;
        //getDeclaredFields可获取所有成员变量(private、public、default)
        ColorfulPrintln.colorfulBack("=====getDeclaredFields可获取所有成员变量====");
        System.out.println(clazz.getDeclaredFields().length);
        System.out.println(JSON.toJSONString(clazz.getDeclaredFields()));
        //getFields只能获取公有成员变量
        ColorfulPrintln.colorfulBack("=====getFields可获取所有成员变量====");
        System.out.println(clazz.getFields().length);
        System.out.println(JSON.toJSONString(clazz.getFields()));

        Lemoon l = new Lemoon();
        Field f = clazz.getDeclaredField("name");
        ColorfulPrintln.colorfulBack("====验证public变量====");
        System.out.println("name is accessible?" + f.isAccessible());
        f.set(l, "I'm sunny");
        System.out.println(f.get(l));

        Field fId = clazz.getDeclaredField("id");
        ColorfulPrintln.colorfulBack("====验证default变量====");
        System.out.println("id is accessible?" + fId.isAccessible());
        fId.set(l, 1);
        System.out.println(fId.get(l));

        Field fCan = clazz.getDeclaredField("can");
        ColorfulPrintln.colorfulBack("====验证private变量====");
        System.out.println("can is accessible?" + fId.isAccessible());
        fCan.setAccessible(true);
        fCan.set(l, "can");
        System.out.println(fCan.get(l));

        ColorfulPrintln.colorfulBack("====getGenericType====");
        clazz = GenericLemoon.class;
        Field field = clazz.getDeclaredField("list");
        System.out.println(field.getGenericType() instanceof ParameterizedType);
    }
}
