package cherry.demo.contract.configure.element.rule;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public abstract class Rule {
    private String name;

    public abstract List<Item> configures();

    public abstract String format(Object value);
}
