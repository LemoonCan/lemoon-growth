package cherry.controller;

import cherry.service.transaction.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @date 2021/12/21
 */
@Component
@RestController
@RequestMapping("/transaction")
@Api(value = "事务测试")
public class TransactionDemo {
    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    private OrderService orderService;

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
    @ApiOperation(value = "变更订单状态",httpMethod = "POST")
    public void updateState(){
        orderService.updateState();
    }

}
