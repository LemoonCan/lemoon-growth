package cherry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author lee
 * @date 2021/12/21
 */
@Component
public class TransactionDemo {
    @Autowired
    PlatformTransactionManager transactionManager;

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
}
