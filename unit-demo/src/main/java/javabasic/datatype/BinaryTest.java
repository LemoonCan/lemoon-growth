package javabasic.datatype;

import console.ColorfulPrintln;

/**
 * @author lee
 * @date 3/12/21
 */
public class BinaryTest {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));

        System.out.println();
        ColorfulPrintln.colorfulBack("=========================================================");
        System.out.println("16右移2：");
        System.out.println(16 >> 2);
        System.out.println("-16右移2：");
        System.out.println(-16 >> 2);
        System.out.println("-16右移2 二进制表示：");
        System.out.println(Integer.toBinaryString(-16 >> 2));

        System.out.println();
        ColorfulPrintln.colorfulBack("=========================================================");
        System.out.println("16无符号右移2：");
        System.out.println(16 >>> 2);
        System.out.println("-16无符号右移2：");
        System.out.println(-16 >>> 2);
        System.out.println("-16无符号右移2 二进制表示：");
        System.out.println(Integer.toBinaryString(-16 >>> 2));
        int x = 0b00111111111111111111111111111100;
        System.out.println("-16无符号右移2 二进制反推值：");
        System.out.println(x);

        System.out.println();
        ColorfulPrintln.colorfulBack("=========================================================");
        System.out.println("-6二进制：");
        System.out.println(Integer.toBinaryString(-6));
        System.out.println("-6无符号右移2：");
        System.out.println(-6 >>> 2);
        System.out.println("-6无符号右移2 二进制表示：");
        System.out.println(Integer.toBinaryString(-6 >>> 2));
        int y = 0b00111111111111111111111111111110;
        System.out.println("-6无符号右移2 二进制反推值：");
        System.out.println(y);

        System.out.println();
        System.out.println(1L<<63);
        System.out.println(Long.toBinaryString(1L<<63));
        System.out.println(Long.toBinaryString(1L<<64));

        int z = 0b11000000000000000000000000000000;
        System.out.println(z>>29);
        System.out.println(Integer.toBinaryString(z>>29));
        System.out.println(z>>32);
        System.out.println(Integer.toBinaryString(z>>32));
    }
}
