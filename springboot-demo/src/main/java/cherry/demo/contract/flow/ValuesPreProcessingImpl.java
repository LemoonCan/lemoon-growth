package cherry.demo.contract.flow;

import cherry.demo.contract.configure.ContractConfigure;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
public class ValuesPreProcessingImpl implements ValuesPreProcessing {
    private final String CONTRACT_NO_KEY = "conNum_";

    @Override
    public Map<String, Object> query(String orderNo, List<ContractConfigure> contractConfigures) {
        Map<String, Object> values = new HashMap<>(16);
        buildConNum(orderNo, contractConfigures, values);
        buildInternValues(orderNo,contractConfigures,values);
        buildExternValues(orderNo,contractConfigures,values);

        return Collections.unmodifiableMap(values);
    }

    private void buildConNum(String orderNo, List<ContractConfigure> contractConfigures, Map<String, Object> values) {

    }

    private void buildInternValues(String orderNo, List<ContractConfigure> contractConfigures, Map<String, Object> values) {

    }

    private void buildExternValues(String orderNo, List<ContractConfigure> contractConfigures, Map<String, Object> values) {

    }
}
