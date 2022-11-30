package cherry.demo.order.core.infrastructure.domain.repository;

import cherry.demo.order.core.domain.model.CustomerInfo;
import cherry.demo.order.core.domain.model.OrderEntity;
import cherry.demo.order.core.domain.model.ShopInfo;
import cherry.demo.order.core.domain.repository.IOrderQuery;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 2022/11/21
 */
@Component
public class OrderQueryImpl implements IOrderQuery {
    @Override
    public OrderEntity order(String orderNo) {
        return new OrderEntity(orderNo,"NEW_CAR","MY_BANK","SIGN_CONTRACT");
    }

    public CustomerInfo customer(String orderNo){
        return new CustomerInfo(orderNo,"lee","603713","8008820");
    }

    @Override
    public ShopInfo shop(String orderNo) {
        return new ShopInfo(orderNo,"s001","太阳花","c5555","笛","id9999","888888");
    }
}
