package jvm.load;

/**
 * @author lee
 * @since 2022/3/7
 */
public class LoadTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("=================");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("=================");
        System.out.println(System.getProperty("java.class.path"));

        LoadTest loadTest = new LoadTest();
        loadTest.classLoader();

        StringBuffer hello = new StringBuffer("hello");
        System.out.println(hello.getClass().getClassLoader());
    }

    public void classLoader(){
        System.out.println(this.getClass().getClassLoader().getClass().getName());
    }
}
