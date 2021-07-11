package javabasic.dynamic.annotation.application.inject;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lee
 * @date 6/6/21
 */
public class SimpleContainer {
    private static ConcurrentHashMap<Class<?>, Object> instances = new ConcurrentHashMap<>();

    public static <T> T getInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        boolean isSingleton = clazz.isAnnotationPresent(SimpleSingleton.class);
        if (!isSingleton) {
            createInstance(clazz);
        }
        Object obj = instances.get(clazz);
        if (obj != null) {
            return (T) obj;
        }
        synchronized (clazz) {
            obj = instances.get(clazz);
            if (obj == null) {
                obj = createInstance(clazz);
                instances.put(clazz, obj);
            }
        }
        return (T) obj;
    }

    private static <T> T createInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(SimpleInject.class)){
                field.setAccessible(true);
            }
            field.set(obj,getInstance(field.getType()));
        }
        return obj;
    }
}
