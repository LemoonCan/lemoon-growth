package cherry.demo.order.core.domain.repository;

import java.util.Map;

/**
 * @author lee
 * @date 2022/11/29
 */
public interface Sql {
    /**
     * 查询 SQL 执行
     * @param sql
     * @return
     */
    Map<String,Object> queryExecute(String sql);
}
