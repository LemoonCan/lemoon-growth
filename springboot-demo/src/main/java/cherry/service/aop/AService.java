package cherry.service.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 2022/2/27
 */
@Component("aService")
public class AService {
    @Autowired
    private BService b;

    public void invoke(){
        System.out.println("Executing a...");
    }

    public void invokeB(){
        b.invoke();
    }
}
