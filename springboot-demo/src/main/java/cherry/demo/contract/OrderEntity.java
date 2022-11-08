package cherry.demo.contract;

import lombok.Getter;

/**
 * @author lee
 * @date 2022/11/8
 */
@Getter
public class OrderEntity {
    private String orderNo;
    private String businessType;
    private String fund;
    private String state;
}
