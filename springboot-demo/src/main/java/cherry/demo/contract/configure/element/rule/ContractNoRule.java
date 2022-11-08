package cherry.demo.contract.configure.element.rule;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public class ContractNoRule extends Rule{
    @Override
    public List<Item> configures() {
        return null;
    }

    @Override
    public String format(Object value) {
        return null;
    }
}
