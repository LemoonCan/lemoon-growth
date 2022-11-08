package cherry.demo.contract.configure;

import cherry.demo.contract.configure.element.rule.Rule;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
@Getter
public class ContractConfigure {
    private Long id;
    private BizModel BizModel;
    private ContractType contractType;
    private String templateCode;
    private String contractName;
    private Show show;

    private List<ConfigureParty> configurePartys;
    private Map<String, Rule> elementsMap;
}
