package cherry.demo.order.core.domain.repository;

import cherry.demo.order.core.domain.model.CustomerInfo;
import cherry.demo.order.core.domain.model.OrderEntity;
import cherry.demo.order.core.domain.model.ShopInfo;

/**
 * @author lee
 * @date 2022/11/8
 */
public interface IOrderQuery {
    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    OrderEntity order(String orderNo);

    /**
     * 查询客户
     * @param orderNo
     * @return
     */
    CustomerInfo customer(String orderNo);

    /**
     * 查询店铺
     * @param orderNo
     * @return
     */
    ShopInfo shop(String orderNo);
}
