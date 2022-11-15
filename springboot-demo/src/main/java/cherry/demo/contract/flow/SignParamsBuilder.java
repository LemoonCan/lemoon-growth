package cherry.demo.contract.flow;

import cherry.demo.contract.configure.ContractConfigure;
import cherry.demo.contract.remote.MultiSignParam;

import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
public interface SignParamsBuilder {
    /**
     * 构建多份合同签署参数
     * @param orderNo
     * @param contractConfigures
     * @param captcha
     * @return
     */
    MultiSignParam buildMulti(String orderNo, List<ContractConfigure> contractConfigures, String captcha);

    /**
     * 构建要素参数
     * @param orderNo
     * @param contractConfigure
     * @return
     */
    Map<String, Object> buildElements(String orderNo, ContractConfigure contractConfigure);
}
