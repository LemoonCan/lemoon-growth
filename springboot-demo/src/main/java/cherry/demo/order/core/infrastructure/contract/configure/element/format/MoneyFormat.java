package cherry.demo.order.core.infrastructure.contract.configure.element.format;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public class MoneyFormat extends Format{
    private final String split = "$";

    public MoneyFormat(String pattern) {
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
