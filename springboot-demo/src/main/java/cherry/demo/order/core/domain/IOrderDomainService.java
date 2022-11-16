package cherry.demo.order.core.domain;

import cherry.demo.order.core.domain.model.Contract;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/16
 */
public interface IOrderDomainService {
    /**
     * 签署合同
     * @param orderNo
     * @param contracts
     */
    void signContract(String orderNo, List<Contract> contracts);
}
