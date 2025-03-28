package cherry.service.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @since 2022/3/2
 */
@Component
public class AsyncService {
    @Autowired
    private DependentService dependentService;

    @Async
    public void reply(){
        System.out.println("Welcome to new world!");
    }
}
