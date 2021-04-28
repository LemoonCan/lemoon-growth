package main.jvm.load;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lee
 * @date 4/28/21
 */
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;
        private String packagePathPrefix;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        public void setPackagePathPrefix(String packagePathPrefix) {
            this.packagePathPrefix = packagePathPrefix;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = getByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        private byte[] getByte(String name) throws IOException {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        /**
         * 打破双亲委派机制
         * 只有自己的包使用此加载器
         *
         * @param name
         * @param resolve
         * @return
         * @throws ClassNotFoundException
         */
        @Override
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();

                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();

                        if (!name.startsWith(packagePathPrefix)) {
                            c = this.getParent().loadClass(name);
                        } else {
                            c = findClass(name);
                        }

                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }

    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader("/Users/lemoncandy/Desktop/WorkSpace/jvm/load/test1");
        myClassLoader.setPackagePathPrefix("com.load.User");
        System.out.println(myClassLoader.getParent().getClass().getName());
        invokeMethod(myClassLoader,"com.load.User","say");

        MyClassLoader myClassLoader2 = new MyClassLoader("/Users/lemoncandy/Desktop/WorkSpace/jvm/load/test2");
        myClassLoader2.setPackagePathPrefix("com.load.User");
        invokeMethod(myClassLoader2,"com.load.User","say");
    }

    private static void invokeMethod(MyClassLoader classLoader,
                                    String clazzName,
                                    String method) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
        Class clazz = classLoader.loadClass(clazzName);
        Object obj = clazz.newInstance();
        Method say = clazz.getMethod(method);
        say.invoke(obj);
    }
}
