package cherry.demo.order.core.infrastructure.domain.repository;

import cherry.demo.order.core.domain.model.OrderEntity;
import cherry.demo.order.core.domain.repository.IOrderQuery;

/**
 * @author lee
 * @date 2022/11/21
 */
public class OrderQueryImpl implements IOrderQuery {
    @Override
    public OrderEntity order(String orderNo) {
        return new OrderEntity("001","NEW_CAR","MY_BANK","SIGN_CONTRACT");
    }
}
