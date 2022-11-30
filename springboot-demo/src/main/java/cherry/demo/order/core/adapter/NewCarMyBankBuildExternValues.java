package cherry.demo.order.core.adapter;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/29
 */
@Component
public class NewCarMyBankBuildExternValues implements IBuildExternValues {
    @Override
    public Map<String, Object> values(String orderNo) {
        Map<String, Object> externalValues = new HashMap<>(4);
        return externalValues;
    }
}
