package javabasic.exception;

/**
 * @author lee
 * @date 2020-09-08
 */
public class LostMessage {
    public static void main(String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();
            } finally{
                try {
                    lm.dispose();
                }finally {
                    lm.g();
                }
            }
        } catch (Exception e) {
            System.out.println("=============try-finally块，finally抛出异常的话，会吃掉try块抛出的原异常");
            e.printStackTrace(System.out);
        }

        LostMessage lm = new LostMessage();
        System.out.println("=============try-catch-finally块，try块中的异常被捕获，若finally抛出异常，try块异常不保留");
        try {
            lm.g();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                lm.dispose();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        System.out.println("=============try-catch-finally块，try块中的异常未被捕获，若finally抛出异常，try块异常保留，会由上层调用方处理");
        try {
            lm.g();
        } catch (VeryImportantException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                lm.dispose();
            } catch (HomeHumException e) {
                e.printStackTrace(System.out);
            }
        }

        System.out.println("我不会被执行");
    }

    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void g() throws VeryImportantException {
        throw new NullPointerException();
    }

    void s()throws VeryImportantException {
        try {
            throw new VeryImportantException();
        } finally {
            return;
        }
    }

    void dispose() throws HomeHumException {
        throw new HomeHumException();
    }
}

class VeryImportantException extends Exception {
    @Override
    public String toString() {
        return "A very important Exception";
    }
}

class HomeHumException extends Exception {
    @Override
    public String toString() {
        return "A trivial exception";
    }
}
