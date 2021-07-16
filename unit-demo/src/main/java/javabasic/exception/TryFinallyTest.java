package javabasic.exception;

/**
 * @author lee
 * @date 6/29/21
 * try 内部 return，会执行 finally
 * try 块前 return，不会执行 finally
 */
public class TryFinallyTest {
    public static void main(String[] args) {
        tryReturn();
        tryExternalReturn(false);
    }

    public static void tryReturn(){
        try{
            return;
        }finally {
            System.out.println("执行 finally");
        }
    }

    public static void tryExternalReturn(Boolean flag){
        if(!flag) {
            return;
        }
        try{
            System.out.println("try 执行");
        }finally {
            System.out.println("finally 执行");
        }
    }
}
