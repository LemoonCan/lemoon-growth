package cherry.demo.contract.flow;

import cherry.demo.contract.common.ContractType;
import cherry.demo.contract.common.Show;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author lee
 * @date 2022/11/4
 */
@Builder
public class Contract {
    private Long id;
    private Long configureId;
    private String contractNo;
    private String name;
    private ContractType contractType;
    private LocalDateTime signDate;
    private Show show;
    private String blankContractUrl;

    public void setBlankContractUrl(String blankContractUrl) {
        this.blankContractUrl = blankContractUrl;
    }
}
