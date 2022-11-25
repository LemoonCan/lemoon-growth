package cherry.demo.order.core.infrastructure.contract.configure;

import cherry.demo.order.core.infrastructure.contract.common.ContractType;
import cherry.demo.order.core.infrastructure.contract.common.Show;
import cherry.demo.order.core.infrastructure.contract.configure.element.rule.Rule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 合同的配置 实际与订单模型是无关的，订单模型关心的是签署完的合同，至于如何配置后续完全可以替换
 *
 * @author lee
 * @date 2022/11/4
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class ContractConfigure {
    private Long id;
    private BizModel BizModel;
    private ContractType contractType;
    private String templateCode;
    private String contractName;
    private Show show;

    private Boolean blankContract;
    private List<ConfigureParty> configureParties;
    private Map<String, Rule> elementsMap;
}
