package jvm;

/**
 * @author lee
 * @date 2022/7/22
 */
public class Hello {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = (a+b)*5;
        System.out.println(c);

        X x = new X();
    }



}
class X{
    public final static String y = "yy";
    public String z;
}
