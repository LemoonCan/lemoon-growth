package cherry.service.aop;

import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 2022/2/27
 */
@Component("aService")
public class AService {
    private final BService b;

    public AService(BService b) {
        this.b = b;
    }

    public void invoke(){
        System.out.println("Executing a...");
    }

    public void invokeB(){
        b.invoke();
    }
}
