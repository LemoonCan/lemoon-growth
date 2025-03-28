package cherry.service.transaction;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;


/**
 * @author lee
 * @since 2022/3/26
 */
@Component("orderService")
public class OrderService {
    @Transactional(rollbackOn = {Exception.class})
    public void updateState(){
        System.out.println("变更订单状态");
    }

}
