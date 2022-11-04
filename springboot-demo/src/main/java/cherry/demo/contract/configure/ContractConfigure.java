package cherry.demo.contract.configure;

import cherry.demo.contract.configure.element.rule.Rule;

import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
public class ContractConfigure {
    private BizModel BizModel;
    private ContractType contractType;
    private String templateCode;
    private String contractName;
    private Show show;

    private Party party;
    private Map<String, Rule> elementsMap;
}
