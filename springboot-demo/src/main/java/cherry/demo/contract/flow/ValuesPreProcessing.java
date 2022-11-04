package cherry.demo.contract.flow;

import cherry.demo.contract.configure.ContractConfigure;

import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
public interface ValuesPreProcessing {
    Map<String,Object> query(String orderNo, List<ContractConfigure> contractConfigures);
}
