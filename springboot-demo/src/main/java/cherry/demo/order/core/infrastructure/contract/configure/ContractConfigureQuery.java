package cherry.demo.order.core.infrastructure.contract.configure;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/7
 */
public interface ContractConfigureQuery {
    List<ContractConfigure> businessContract(BizModel model);

    ContractConfigure find(Long id);
}
