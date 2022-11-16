package cherry.demo.order.core.infrastructure.domain;

import cherry.demo.order.core.domain.IOrderDomainService;
import cherry.demo.order.core.domain.model.Contract;
import cherry.demo.order.core.domain.repository.IContractRepository;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/16
 */
public class OrderDomainServiceImpl implements IOrderDomainService {
    IContractRepository contractRepository;

    @Override
    public void signContract(String orderNo, List<Contract> contracts) {
        //保存合同
        contractRepository.save(contracts);
        //发出事件
    }
}
