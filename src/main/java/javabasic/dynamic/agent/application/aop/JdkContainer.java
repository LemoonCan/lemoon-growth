package javabasic.dynamic.agent.application.aop;
import javabasic.dynamic.annotation.application.inject.SimpleInject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lee
 * @date 6/6/21
 */
public class JdkContainer {
    /**
     * 维护类与拦截器的关系
     */
    static Map<Class<?>, Map<InterceptPoint, List<Method>>> interceptMethodsMap = new HashMap<>();
    /**
     * 扫描类
     */
    static Class<?>[] aspects = new Class[]{ServiceLogAspect.class, ExceptionAspect.class};

    static {
        init();
    }

    private static void init() {
        for (Class cls : aspects) {
            Aspect aspect = (Aspect) cls.getAnnotation(Aspect.class);
            if (aspect != null) {
                Method before = null;
                Method after = null;
                Method exception = null;
                try {
                    before = cls.getMethod("before", new Class[]{Object.class, Method.class, Object[].class});
                    after = cls.getMethod("after", new Class[]{Object.class, Method.class, Object[].class, Object.class});
                    exception = cls.getMethod("exception", new Class[]{Object.class, Method.class, Object[].class, Throwable.class});
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

                Class<?>[] intercepttedAttr = aspect.value();
                for (Class<?> interceptted : intercepttedAttr) {
                    addInterceptMethod(interceptted, InterceptPoint.BEFORE, before);
                    addInterceptMethod(interceptted, InterceptPoint.AFTER, after);
                    addInterceptMethod(interceptted, InterceptPoint.EXCEPTION, exception);
                }
            }
        }
    }

    private static void addInterceptMethod(Class<?> cls, InterceptPoint point, Method method) {
        if (method == null) {
            return;
        }
        Map<InterceptPoint, List<Method>> map = interceptMethodsMap.get(cls);
        if (map == null) {
            map = new HashMap<>();
            interceptMethodsMap.put(cls, map);
        }
        List<Method> methods = map.get(point);
        if (method == null) {
            methods = new ArrayList<>();
            map.put(point, methods);
        }
        methods.add(method);
    }

    private static List<Method> getInterceptMethods(Class<?> cls, InterceptPoint point) {
        Map<InterceptPoint, List<Method>> map = interceptMethodsMap.get(cls);
        if (map == null) {
            return Collections.emptyList();
        }
        List<Method> methods = map.get(point);
        if (methods == null) {
            return Collections.emptyList();
        }
        return methods;
    }

    private static ConcurrentHashMap<Class<?>, Object> instances = new ConcurrentHashMap<>();

//    public static <X, Y extends X> X getInstance(Class<X> x, Class<Y> y) throws IllegalAccessException, InstantiationException {
//        X obj = (Y) createInstance(x, y);
//        Field[] fields = y.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.isAnnotationPresent(SimpleInject.class)) {
//                field.setAccessible(true);
//            }
//            field.set(obj, getInstance(field.getType().getSuperclass(), field.getType()));
//        }
//        return obj;
//    }

    private static <X, Y extends X> X createInstance(Class<X> x, Class<Y> y) throws IllegalAccessException, InstantiationException {
        if (!interceptMethodsMap.containsKey(y)) {
            return y.newInstance();
        }
        return (X) Proxy.newProxyInstance(x.getClassLoader(), new Class[]{x}, new AspectInterceptor(y.newInstance()));
    }

    static class AspectInterceptor<T> implements InvocationHandler {
        T obj;

        public AspectInterceptor(T obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            List<Method> beforeMethods = getInterceptMethods(obj.getClass(), InterceptPoint.BEFORE);
            for (Method m : beforeMethods) {
                m.invoke(null, new Object[]{obj, method, args});
            }
            try {
                Object result = method.invoke(obj, args);
                List<Method> afterMethods = getInterceptMethods(obj.getClass(), InterceptPoint.AFTER);
                for (Method m :
                        afterMethods) {
                    m.invoke(null, new Object[]{obj, method, args, result});
                }
                return result;
            } catch (Throwable e) {
                List<Method> exceptionMethods = getInterceptMethods(obj.getClass(), InterceptPoint.AFTER);
                for (Method m : exceptionMethods) {
                    m.invoke(null, new Object[]{obj, method, args, e});
                }
                throw e;
            }
        }
    }
}
