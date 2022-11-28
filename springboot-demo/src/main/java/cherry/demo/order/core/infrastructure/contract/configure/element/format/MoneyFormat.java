package cherry.demo.order.core.infrastructure.contract.configure.element.format;

import cherry.demo.order.core.infrastructure.contract.common.MoneyUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 金额格式化
 *
 * @author lee
 * @date 2022/11/4
 */
public class MoneyFormat extends Format {
    private static final String split = "$";
    private static final String split2 = "#";

    private static final String NO_PROCESSING = "NO_PROCESSING";

    private static final String DIVIDE = "DIVIDE";

    private static final String CHINESE_CAPITAL = "CHINESE_CAPITAL";

    public MoneyFormat(String pattern) {
        super(pattern);
    }

    @Override
    public String translate(Object origin) {
        BigDecimal value = (BigDecimal) origin;
        String[] formats = pattern.split(split);
        //第一个规则处理(是否转换单位)
        if(formats[0].contains(DIVIDE)){
            String[] opts= formats[0].split(split2);
            BigDecimal divisorFactor = new BigDecimal(opts[1]);
            Integer scale = Integer.parseInt(opts[2]);

            value = value.divide(divisorFactor,scale, RoundingMode.HALF_UP);
        }

        //第二个规则处理(是否转换大写)
        if(formats[1].equals(CHINESE_CAPITAL)){
            return MoneyUtil.toChinese(value.toString());
        }
        return value.toPlainString();
    }

    @Override
    public List<String> patternOptions() {
        return null;
    }
}
