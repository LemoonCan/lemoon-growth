package javabasic.exception;

/**
 * @author lee
 * @since 2020-09-03
 */
public class ReThrowNew {
    public static void main(String[] args) {
        try {
            try {
                f();
            } catch (OneException e) {
                System.out.println("caught in inner try,e.printStackTrace():");
                e.printStackTrace();
                throw new TwoException("from main inner");
            }
        }catch (Exception e){
            System.out.println("caught in outer try,e.printStackTrace():");
            e.printStackTrace();
        }
    }

    public static void f() throws OneException{
        System.out.println("original exception in f():");
        throw new OneException("thrown from f()");
    }
}

class OneException extends Exception {
    public OneException(String s) {
        super(s);
    }
}

class TwoException extends Exception {
    public TwoException(String s) {
        super(s);
    }
}
