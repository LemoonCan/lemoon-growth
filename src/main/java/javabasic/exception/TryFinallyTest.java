package javabasic.exception;

/**
 * @author lee
 * @date 6/29/21
 */
public class TryFinallyTest {
    public static void main(String[] args) {
        tryReturn();
    }

    public static void tryReturn(){
        try{
            return;
        }finally {
            System.out.println("执行 finally");
        }
    }
}
