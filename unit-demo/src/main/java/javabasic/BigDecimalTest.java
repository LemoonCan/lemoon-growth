package javabasic;

import java.math.BigDecimal;

/**
 * @author lee
 * @since 2022/10/8
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        // 浮点数使用BigDecimal时，使用String存储才可正常展示
        double x = 0.1d;
        System.out.println(new BigDecimal(x));

        String y = "0.1";
        System.out.println(new BigDecimal(y));

        System.out.println(new BigDecimal(Double.toString(0.1000000000000000055511151231257827021181583404541015625d)));
        System.out.println(new BigDecimal(Double.toString(0.1)));

        // 去除无效的 0
        double z1 = 2076.00000;
        BigDecimal z2 = BigDecimal.valueOf(z1);
        System.out.println(z2);
        System.out.println(z2.toPlainString());
        System.out.println(z2.stripTrailingZeros().toPlainString());
    }
}
