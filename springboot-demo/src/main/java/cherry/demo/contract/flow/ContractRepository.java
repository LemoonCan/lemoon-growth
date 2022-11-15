package cherry.demo.contract.flow;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/10
 */
public interface ContractRepository {
    void save(List<Contract> contracts);
}
