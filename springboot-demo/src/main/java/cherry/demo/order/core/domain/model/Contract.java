package cherry.demo.order.core.domain.model;

import cherry.demo.order.core.infrastructure.contract.common.ContractType;
import cherry.demo.order.core.infrastructure.contract.common.Show;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author lee
 * @date 2022/11/4
 */
@Builder
public class Contract {
    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    private String contractNo;
    private String name;
    private ContractType contractType;
    private LocalDateTime signDate;
    private Show show;
    private String blankContractUrl;

    public void generateBlankContract(String blankContractUrl) {
        this.blankContractUrl = blankContractUrl;
    }
}
