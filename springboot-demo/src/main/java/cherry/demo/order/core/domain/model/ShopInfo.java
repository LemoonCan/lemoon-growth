package cherry.demo.order.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lee
 * @date 2022/11/30
 */
@AllArgsConstructor
@Getter
public class ShopInfo {
    private String orderNo;
    private String code;
    private String name;
    private String creditCode;
    private String salePersonName;
    private String salePersonIdCardNo;
    private String salePersonMobile;
}
