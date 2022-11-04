package cherry.demo.contract.flow;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public interface PreviewList {
    List<ContractDTO> contractList(String orderNo);
}
