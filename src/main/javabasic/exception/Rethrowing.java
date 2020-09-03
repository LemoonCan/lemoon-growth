package main.javabasic.exception;

/**
 * @author lee
 * @date 2020-09-03
 */
public class Rethrowing {
    public static void main(String[] args) {
        try {
            g();
        } catch (Exception e) {
            System.out.println("------main(无fill) e.printStackTrace()------");
            e.printStackTrace();
        }
        try {
            h();
        } catch (Exception e) {
            System.out.println("------main(fill) e.printStackTrace()------");
            e.printStackTrace();
        }
    }

    public static void f() throws Exception{
        System.out.println("Original exception in f():");
        throw new Exception("thrown from f");
    }

    public static void g() throws Exception{
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside g(),e.printStackTrace():");
            e.printStackTrace();
            throw e;
        }
    }

    public static void h() throws Exception{
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside h(),e.printStackTrace():");
            e.printStackTrace();
            throw (Exception) e.fillInStackTrace();
        }
    }

}
