package cherry.demo.order.core.infrastructure.domain.repository;

import cherry.demo.order.core.domain.model.Contract;
import cherry.demo.order.core.domain.repository.IContractRepository;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/21
 */
@Slf4j
@Component
public class ContractRepositoryImpl implements IContractRepository {
    @Override
    public void save(List<Contract> contracts) {
        log.info("保存合同:{}", JSON.toJSONString(contracts));
    }
}
