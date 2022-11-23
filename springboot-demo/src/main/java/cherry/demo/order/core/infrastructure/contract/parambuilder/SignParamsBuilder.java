package cherry.demo.order.core.infrastructure.contract.parambuilder;

import cherry.demo.order.core.infrastructure.contract.configure.ContractConfigure;
import cherry.demo.order.remote.dto.MultiSignParam;

import java.util.List;
import java.util.Map;

/**
 * 签署参数构建
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
