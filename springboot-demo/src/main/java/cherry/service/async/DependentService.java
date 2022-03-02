package cherry.service.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 2022/3/2
 */
@Component
public class DependentService {
    @Autowired
    @Lazy
    private AsyncService asyncService;
}
