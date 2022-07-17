package jvm.load;

import java.io.IOException;
import java.io.InputStream;

/**
 * 不同的类加载器加载类不一致
 *
 * @author lee
 * @date 2022/7/13
 */
public class DiffClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("jvm.load.User").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof jvm.load.User);

        Object obj2 = new jvm.load.User();
        System.out.println(obj2.getClass());
        System.out.println(obj2 instanceof jvm.load.User);
    }
}
