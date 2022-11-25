package cherry.demo.order.core.infrastructure.contract.common;

import lombok.Builder;

/**
 * @author lee
 * @date 2022/11/4
 */
@Builder
public class Show {
    private Boolean admin;
    private Boolean fund;
    private Boolean shop;
    private Boolean customer;
}
