package cherry.demo.order.core.infrastructure.contract.configure.element.rule;

import cherry.demo.order.api.dto.ItemDTO;
import cherry.demo.order.core.infrastructure.contract.configure.element.format.Format;

import java.util.Arrays;
import java.util.List;

/**
 * 外部数据规则
 * @author lee
 * @date 2022/11/4
 */
public class ExternRule extends AbstractRuleAndConfigure {
    private String key;
    private Format format;

    @Override
    public List<ItemDTO> configures() {
        return Arrays.asList(new ItemDTO("format","格式化参数"));
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
