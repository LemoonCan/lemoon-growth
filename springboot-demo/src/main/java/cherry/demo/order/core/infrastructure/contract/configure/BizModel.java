package cherry.demo.order.core.infrastructure.contract.configure;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lee
 * @date 2022/11/4
 */
@AllArgsConstructor
@Getter
public class BizModel {
    private String businessType;
    private String fund;
    private String state;
}
