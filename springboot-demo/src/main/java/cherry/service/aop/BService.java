package cherry.service.aop;

import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 2022/2/27
 */
@Component("bService")
public class BService {
    private final AService a;

    public BService(AService a) {
        this.a = a;
    }

    public void invoke(){
        System.out.println("Executing b...");
    }

    public void invokeA(){
        a.invoke();
    }
}
