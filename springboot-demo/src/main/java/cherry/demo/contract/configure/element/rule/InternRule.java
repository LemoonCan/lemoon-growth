package cherry.demo.contract.configure.element.rule;

import cherry.demo.contract.configure.element.format.Format;

import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public class InternRule extends RuleWithConfigure{
    private String tableColumn;
    private Format format;

    @Override
    public List<Item> configures() {
        return Arrays.asList(
                new Item("tableColumn","表字段"),
                new Item("format","格式化参数")
        );
    }

    @Override
    public String key() {
        return tableColumn;
    }

    @Override
    public String format(Object value) {
        return format.translate(value);
    }
}
