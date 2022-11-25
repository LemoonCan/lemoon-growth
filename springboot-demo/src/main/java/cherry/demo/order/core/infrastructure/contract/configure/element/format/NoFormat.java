package cherry.demo.order.core.infrastructure.contract.configure.element.format;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/25
 */
public class NoFormat extends Format{
    public NoFormat() {
        super(null);
    }

    @Override
    public String translate(Object origin) {
        return (String)origin;
    }

    @Override
    public List<String> patternOptions() {
        return null;
    }
}
