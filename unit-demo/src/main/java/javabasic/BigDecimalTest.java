package javabasic;

import java.math.BigDecimal;

/**
 * @author lee
 * @date 2022/10/8
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        double x = 0.1d;
        System.out.println(new BigDecimal(x));

        String y = "0.1";
        System.out.println(new BigDecimal(y));

        System.out.println(new BigDecimal(Double.toString(0.1000000000000000055511151231257827021181583404541015625d)));
        System.out.println(new BigDecimal(Double.toString(0.1)));
    }
}
