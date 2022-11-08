package cherry.demo.contract.configure.element.rule;

import cherry.demo.contract.configure.element.format.Format;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public class InternRule extends Rule{
    private String tableColumn;
    private Format format;

    @Override
    public List<Item> configures() {
        return null;
    }

    @Override
    public String format(Object value) {
        return null;
    }
}
