package cherry.demo.order.core.infrastructure.contract.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lee
 * @date 2022/11/28
 */
public class MoneyUtil {
    /**
     * 数字部分
     */
    private static final String[] NUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    /**
     * 整数数位部分 521234
     *            伍拾贰万壹贰佰叁拾肆
     */
    private static final String[] I_UNIT = {"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟"};
    /**
     * 小数数位部分
     */
    private static final String[] D_UNIT = {"角", "分", "厘"};

    /**
     * 转成中文的大写金额
     * @param str
     * @return
     */
    public static String toChinese(String str) {
        //判断输入的金额字符串是否符合要求
        if (StringUtils.isBlank(str) || !str.matches("(-)?[\\d]*(.)?[\\d]*")) {
            throw new IllegalArgumentException("请输入数字!");
        }
        //判断输入的金额字符串
        if ("0".equals(str) || "0.00".equals(str) || "0.0".equals(str)) {
            return "零元";
        }

        //判断是否存在负号"-"
        boolean flag = false;
        if (str.startsWith("-")) {
            flag = true;
            str = str.replaceAll("-", "");
        }

        //整数部分数字
        String integerStr;
        //小数部分数字
        String decimalStr;

        //分离整数部分和小数部分
        if (str.indexOf(".") > 0) {
            //整数部分和小数部分
            integerStr = str.substring(0, str.indexOf("."));
            decimalStr = str.substring(str.indexOf(".") + 1);
        } else if (str.indexOf(".") == 0) {
            //只存在小数部分 .34
            integerStr = "";
            decimalStr = str.substring(1);
        } else {
            //只存在整数部分 34
            integerStr = str;
            decimalStr = "";
        }

        //整数部分超出计算能力，直接返回
        if (integerStr.length() > I_UNIT.length) {
            throw new IllegalArgumentException("超出计算能力!");
        }

        //整数部分存入数组  目的是为了可以动态的在字符串数组中取对应的值
        int[] integers = toIntArray(integerStr);

        //判断整数部分是否存在输入012的情况
        if (integers.length > 1 && integers[0] == 0) {
            throw new IllegalArgumentException("请输入数字!");
        }
        //设置万单位
        boolean tenThousand = reachesTenThousand(integerStr);

        //小数部分数字存入数组
        int[] decimals = toIntArray(decimalStr);

        //返回最终的大写金额
        String result = integerToChinese(integers, tenThousand) + decimalToChinese(decimals);

        if (flag) {
            //如果是负数，加上"负"
            return "负" + result;
        } else {
            return result;
        }
    }

    /**
     * 将字符串转为int数组
     * @param number
     * @return
     */
    private static int[] toIntArray(String number) {
        //初始化一维数组长度
        int[] array = new int[number.length()];
        //循环遍历赋值
        for (int i = 0; i < number.length(); i++) {
            array[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        return array;
    }

    /**
     * 将整数部分转为大写的金额
     * @param integers
     * @param tenThousand
     * @return
     */
    private static String integerToChinese(int[] integers, boolean tenThousand) {
        StringBuffer chineseInteger = new StringBuffer();
        int length = integers.length;
        // 对于输入的字符串为 "0." 存入数组后为 0
        if (length == 1 && integers[0] == 0) {
            return "";
        }
        for (int i = 0; i < length; i++) {
            String key = "";
            if (integers[i] == 0) {
                if ((length - i) == 13)
                    //万（亿）
                    key = I_UNIT[4];
                else if ((length - i) == 9) {
                    //亿
                    key = I_UNIT[8];
                } else if ((length - i) == 5 && tenThousand) {
                    //万
                    key = I_UNIT[4];
                } else if ((length - i) == 1) {
                    //元
                    key = I_UNIT[0];
                }
                if ((length - i) > 1 && integers[i + 1] != 0) {
                    key += NUMBERS[0];
                }
            }
            chineseInteger.append(integers[i] == 0 ? key : (NUMBERS[integers[i]] + I_UNIT[length - i - 1]));
        }
        return chineseInteger.toString();
    }

    /**
     * 将小数部分转为大写的金额
     * @param decimals
     * @return
     */
    private static String decimalToChinese(int[] decimals) {
        //角 分 厘   038  壹分捌厘
        StringBuffer chineseDecimal = new StringBuffer();
        for (int i = 0; i < decimals.length; i++) {
            if (i == 3) {
                break;
            }
            chineseDecimal.append(decimals[i] == 0 ? "" : (NUMBERS[decimals[i]] + D_UNIT[i]));
        }
        return chineseDecimal.toString();
    }

    /**
     * 判断整数部分是否达到【万】
     * @param integerStr
     * @return
     */
    private static boolean reachesTenThousand(String integerStr) {
        int length = integerStr.length();
        if (length > 4) {
            String subInteger;
            if (length > 8) {
                subInteger = integerStr.substring(length - 8, length - 4);
            } else {
                subInteger = integerStr.substring(0, length - 4);
            }
            return Integer.parseInt(subInteger) > 0;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(toChinese("500000234.0100"));
    }
}
