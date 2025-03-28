package cherry.controller;

import cherry.service.transaction.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @since 2021/12/21
 */
@Component
@RestController
@RequestMapping("/transaction")
@Tag(name = "事务测试")
public class TransactionDemo {
    private final PlatformTransactionManager transactionManager;

    private final OrderService orderService;

    public TransactionDemo(PlatformTransactionManager transactionManager, OrderService orderService) {
        this.transactionManager = transactionManager;
        this.orderService = orderService;
    }

    void update() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {

        } catch (RuntimeException e) {
            transactionManager.rollback(status);
        } catch (Error e) {
            transactionManager.rollback(status);
        }
        transactionManager.commit(status);
    }

    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    @Operation(description = "变更订单状态",method = "POST")
    public void updateState(){
        orderService.updateState();
    }

}
