package cherry.demo.order.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户信息
 * @author lee
 * @date 2022/11/30
 */
@AllArgsConstructor
@Getter
public class CustomerInfo {
    private String orderNo;
    private String name;
    private String idCardNo;
    private String mobile;
}
