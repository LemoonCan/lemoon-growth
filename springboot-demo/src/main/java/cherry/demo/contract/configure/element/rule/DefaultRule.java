package cherry.demo.contract.configure.element.rule;

import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public class DefaultRule extends RuleWithConfigure {
    private String defaultValue;

    @Override
    public List<Item> configures() {
        return Arrays.asList(new Item("defaultValue", "默认值"));
    }

    @Override
    public String key() {
        return null;
    }

    @Override
    public String format(Object value) {
        return defaultValue;
    }
}
