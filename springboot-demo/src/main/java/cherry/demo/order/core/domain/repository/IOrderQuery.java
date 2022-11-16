package cherry.demo.order.core.domain.repository;

import cherry.demo.order.core.domain.model.OrderEntity;

/**
 * @author lee
 * @date 2022/11/8
 */
public interface IOrderQuery {
    OrderEntity order(String orderNo);
}
