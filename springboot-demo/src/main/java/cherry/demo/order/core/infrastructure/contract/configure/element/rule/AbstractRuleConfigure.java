package cherry.demo.order.core.infrastructure.contract.configure.element.rule;

import cherry.demo.order.api.dto.ItemDTO;

import java.util.List;

/**
 * 规则配置
 * @author lee
 * @date 2022/11/9
 */
public abstract class AbstractRuleConfigure {
    protected String name;
    /**
     * 配置项
     * @return
     */
    public abstract List<ItemDTO> configures();
}
