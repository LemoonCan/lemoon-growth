package cherry.demo.contract.configure.element.rule;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/9
 */
public abstract class RuleConfigure {
    protected String name;
    /**
     * 配置项
     * @return
     */
    public abstract List<Item> configures();
}
