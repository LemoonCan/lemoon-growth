package cherry.demo.contract;

/**
 * @author lee
 * @date 2022/11/8
 */
public class OrderQuery {
    public OrderEntity order(String orderNo){
        return new OrderEntity(orderNo,"TESTING","SUN_FUND","WAIT_SIGN");
    }
}
