package jvm.gc;

/**
 * 如何观察 GC?
 * @author lee
 * @date 2022/7/26
 */
public class LocalVariableTest {
    public static void main(String[]args){
        {
            byte[] placeholder = new byte[64*1024*1024];
        }
        System.gc();
    }
}
