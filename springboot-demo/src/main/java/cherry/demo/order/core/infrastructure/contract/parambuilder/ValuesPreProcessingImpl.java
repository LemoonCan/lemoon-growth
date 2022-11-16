package cherry.demo.order.core.infrastructure.contract.parambuilder;

import cherry.demo.order.core.infrastructure.contract.configure.ContractConfigure;
import cherry.demo.order.core.infrastructure.contract.common.ContractNoKeyGenerator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
public class ValuesPreProcessingImpl implements ValuesPreProcessing {


    @Override
    public Map<String, Object> query(String orderNo, List<ContractConfigure> contractConfigures) {
        Map<String, Object> values = new HashMap<>(16);
        buildConNum(orderNo, contractConfigures, values);
        buildInternValues(orderNo, contractConfigures, values);
        buildExternValues(orderNo, contractConfigures, values);

        return Collections.unmodifiableMap(values);
    }

    @Override
    public Map<String, Object> query(String orderNo, ContractConfigure contractConfigure) {
        return null;
    }

    @Override
    public String getAContractNo(Map<String, Object> values, ContractConfigure configure) {
        return (String) values.get(ContractNoKeyGenerator.build(configure.getContractType()));
    }


    private void buildConNum(String orderNo, List<ContractConfigure> contractConfigures, Map<String, Object> values) {

    }

    private void buildInternValues(String orderNo, List<ContractConfigure> contractConfigures, Map<String, Object> values) {

    }

    private void buildExternValues(String orderNo, List<ContractConfigure> contractConfigures, Map<String, Object> values) {

    }
}
