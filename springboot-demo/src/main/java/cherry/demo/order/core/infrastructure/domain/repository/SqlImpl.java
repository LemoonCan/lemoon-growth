package cherry.demo.order.core.infrastructure.domain.repository;

import cherry.demo.order.core.domain.repository.Sql;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 2022/11/30
 */
@Component
public class SqlImpl implements Sql {
    @Override
    public Map<String, Object> queryExecute(String sql) {
        Map<String,Object> values = new HashMap<>(16);

        return values;
    }
}
