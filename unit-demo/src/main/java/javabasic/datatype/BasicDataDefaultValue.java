package javabasic.datatype;

/**
 * @author lee
 * @since 2020-09-09
 */
public class BasicDataDefaultValue {
    private static int i;
    private static short s;
    private static long l;
    private static float f;
    private static double d;

    /**
     * 默认\u0000 打印出什么与编码有关
     */
    private static char c;

    private static boolean bo;
    private static byte by;

    public static void main(String[] args) {
        System.out.println("int default " + i);
        System.out.println("short default " + s);
        System.out.println("long default " + l);
        System.out.println("float default " + f);
        System.out.println("double default " + d);
        System.out.println("char default " + c);
        System.out.println("boolean default " + bo);
        System.out.println("byte default " + by);
    }
}
