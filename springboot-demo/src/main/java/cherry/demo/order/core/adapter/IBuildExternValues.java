package cherry.demo.order.core.adapter;

import java.util.Map;

/**
 * @author lee
 * @date 2022/11/29
 */
public interface IBuildExternValues {
    /**
     * 取外部值
     * @param orderNo
     * @return
     */
    Map<String,Object> values(String orderNo);
}
