package cherry.service.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 2022/2/27
 */
@Component("bService")
public class BService {
    @Autowired
    private AService a;

    public void invoke(){
        System.out.println("Executing b...");
    }

    public void invokeA(){
        a.invoke();
    }
}
