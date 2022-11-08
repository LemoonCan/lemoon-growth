package cherry.demo.contract.flow;

import cherry.demo.contract.configure.ContractConfigure;
import cherry.demo.contract.remote.MultiSignParam;
import cherry.demo.contract.remote.SingleSignParam;

import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
public interface SignParamsBuilder {
    MultiSignParam buildMulti(String orderNo, List<ContractConfigure> contractConfigures, String captcha);
    SingleSignParam buildSingle(String orderNo, ContractConfigure contractConfigure, String captcha);
    Map<String, Object> buildElements(String orderNo, ContractConfigure contractConfigure);
}
