package cherry.demo.order.core.infrastructure.contract.configure.element.format;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public abstract class Format {
    public Format(String pattern) {
    }

    public abstract String translate(Object origin);

    public abstract List<String> patternOptions();
}
