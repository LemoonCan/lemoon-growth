package cherry.demo.order.core.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/29
 */
public class NewCarMyBankBuildExternValues implements IBuildExternValues{
    @Override
    public Map<String, Object> values(String orderNo) {
        return new HashMap<>();
    }
}
