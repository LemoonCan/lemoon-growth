package cherry.demo.order.core.domain.repository;

import java.util.Map;

/**
 * @author lee
 * @date 2022/11/29
 */
public interface Sql {
    Map<String,Object> queryExecute(String sql);
}
