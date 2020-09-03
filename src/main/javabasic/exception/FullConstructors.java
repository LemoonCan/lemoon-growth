package main.javabasic.exception;

/**
 * @author lee
 * @date 2020-09-03
 */
public class FullConstructors {
    public static void main(String[] args) {
        try {
            f();
        } catch (MyException e) {
            e.printStackTrace();
        }
        try {
            g();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static void f() throws MyException{
        System.out.println("Throwing MyException from f()");
        throw new MyException();
    }

    public static void g() throws MyException{
        System.out.println("Throwing MyException from g()");
        throw new MyException("Originated in g()");
    }

    class DirtyException extends Exception{
        private String garbage;

        public DirtyException(String message, String garbage) {
            super(message);
            this.garbage = garbage;
        }
    }
}

class MyException extends Exception {
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
