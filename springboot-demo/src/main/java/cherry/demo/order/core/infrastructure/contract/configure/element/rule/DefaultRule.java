package cherry.demo.order.core.infrastructure.contract.configure.element.rule;

import cherry.demo.order.api.dto.ItemDTO;

import java.util.Arrays;
import java.util.List;

/**
 * 默认规则
 * @author lee
 * @date 2022/11/4
 */
public class DefaultRule extends AbstractRuleAndConfigure {
    private String defaultValue;

    @Override
    public List<ItemDTO> configures() {
        return Arrays.asList(new ItemDTO("defaultValue", "默认值"));
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
