package cherry.demo.order.core.infrastructure.contract.parambuilder;

import cherry.demo.order.core.infrastructure.contract.configure.ContractConfigure;

import java.util.List;
import java.util.Map;

/**
 * 签署值预处理
 * @author lee
 * @date 2022/11/4
 */
public interface ValuesPreProcessing {
    /**
     * 以订单维度 缓存预处理签署值
     * @param orderNo
     * @param contractConfigures
     */
    void cache(String orderNo,List<ContractConfigure> contractConfigures);

    /**
     * 清除缓存
     * @param orderNo
     */
    void clearCache(String orderNo);

    Map<String,Object> query(String orderNo, List<ContractConfigure> contractConfigures);
    Map<String,Object> query(String orderNo, ContractConfigure contractConfigure);

    String getAContractNo(Map<String,Object> values,ContractConfigure configure);
}
