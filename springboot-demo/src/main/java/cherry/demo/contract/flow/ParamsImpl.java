package cherry.demo.contract.flow;

import cherry.demo.contract.configure.ContractConfigure;

import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
public class ParamsImpl implements Params {
    private ValuesPreProcessing valuesPreProcessing;

    @Override
    public SignParam build(String orderNo, List<ContractConfigure> contracts) {
        Map<String, Object> values = valuesPreProcessing.query(orderNo, contracts);

        for (ContractConfigure item : contracts) {
            List<Object> partys = buildParty(orderNo, item);
            Map<String, Object> elements = bulidElements(values, item);
        }
        return null;
    }

    private List<Object> buildParty(String orderNo, ContractConfigure contract) {
        return null;
    }

    private Map<String, Object> bulidElements(Map<String, Object> values, ContractConfigure contract) {

        return null;
    }

}
