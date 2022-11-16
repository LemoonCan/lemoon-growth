package cherry.demo.order.core.domain.repository;

import cherry.demo.order.core.domain.model.Contract;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/10
 */
public interface IContractRepository {
    void save(List<Contract> contracts);
}
