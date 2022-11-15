package cherry.demo.contract.configure.element.rule;

import cherry.demo.contract.configure.element.format.Format;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lee
 * @date 2022/11/4
 */
public class ExternRule extends RuleWithConfigure{
    private String key;
    private Format format;

    @Override
    public List<Item> configures() {
        return Arrays.asList(new Item("format","格式化参数"));
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String format(Object value) {
        return format.translate(value);
    }
}
