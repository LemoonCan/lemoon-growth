package cherry.demo.order.core.infrastructure.contract.configure.element.rule;

import cherry.demo.order.core.infrastructure.contract.common.ContractType;
import cherry.demo.order.core.infrastructure.contract.common.ContractNoKeyGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public class ContractNoRule extends RuleWithConfigure{
    private ContractType contractType;

    @Override
    public List<Item> configures() {
        return Arrays.asList(new Item("contractType","合同类型"));
    }

    @Override
    public String key() {
       return ContractNoKeyGenerator.build(contractType);
    }

    @Override
    public String format(Object value) {
        return (String)value;
    }
}
