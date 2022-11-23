package cherry.demo.order.core.infrastructure.contract.configure.element.format;

import java.util.List;

/**
 * 日期格式化
 * @author lee
 * @date 2022/11/4
 */
public class DateFormat extends Format {

    public DateFormat(String pattern) {
        super(pattern);
    }

    @Override
    public String translate(Object origin) {
        return null;
    }

    @Override
    public List<String> patternOptions() {
        return null;
    }
}
