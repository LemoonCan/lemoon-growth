package cherry.demo.contract.flow;

import cherry.demo.contract.configure.ContractConfigure;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public interface Params {
    SignParam build(String orderNo, List<ContractConfigure> contracts);
}
