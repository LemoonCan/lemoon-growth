package cherry.demo.order.core.infrastructure.contract.configure.element.rule;

import cherry.demo.order.api.dto.ItemDTO;
import cherry.demo.order.core.infrastructure.contract.common.ContractType;
import cherry.demo.order.core.infrastructure.contract.common.ContractNoKeyGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * 合同编号规则
 * @author lee
 * @date 2022/11/4
 */
public class ContractNoRule extends AbstractRuleAndConfigure {
    private ContractType contractType;

    @Override
    public List<ItemDTO> configures() {
        return Arrays.asList(new ItemDTO("contractType","合同类型"));
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
