package javabasic.exception;

/**
 * @author lee
 * @since 2020-09-03
 */
public class ExceptionMethods {
    public static void main(String[] args) {
        try {
            throw new Exception("my exception");
        } catch (Exception e) {
            System.out.println("Caught exception");
            System.out.println("getMessage():" + e.getMessage());
            System.out.println("getLocalizedMessage():" + e.getLocalizedMessage());
            System.out.println("toString():" + e);
            System.out.print("printStackTrace():");
            e.printStackTrace(System.out);
        }
    }
}
