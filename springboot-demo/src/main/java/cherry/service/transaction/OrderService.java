package cherry.service.transaction;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @author lee
 * @date 2022/3/26
 */
@Component("orderService")
public class OrderService {
    @Transactional(rollbackOn = {Exception.class})
    public void updateState(){
        System.out.println("变更订单状态");
    }

}
