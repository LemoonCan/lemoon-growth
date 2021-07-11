package javabasic;

/**
 * @author lee
 * @date 6/25/21
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "16712347777";
        StringBuffer sb = new StringBuffer(s);
        sb.replace(3,7,"****");
        System.out.println(sb.toString());
    }
}
