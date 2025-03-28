package javabasic.str;

/**
 * @author lee
 * @since 6/25/21
 */
public class StringTest {
    public static void main(String[] args) {
        String a = new StringBuilder("计算机").append("软件").toString();
        System.out.println(a.intern() == a);

        String b = new StringBuilder("ja").append("va").toString();
        System.out.println(b.intern() == b);

        String c = new String("哈哈哈");
        System.out.println(c.intern() == c);
    }
}
