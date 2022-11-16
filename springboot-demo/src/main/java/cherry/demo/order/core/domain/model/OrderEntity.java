package cherry.demo.order.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lee
 * @date 2022/11/8
 */
@AllArgsConstructor
@Getter
public class OrderEntity {
    private String orderNo;
    private String businessType;
    private String fund;
    private String state;
}
