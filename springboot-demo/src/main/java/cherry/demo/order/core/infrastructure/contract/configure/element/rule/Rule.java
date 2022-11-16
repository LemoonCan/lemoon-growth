package cherry.demo.order.core.infrastructure.contract.configure.element.rule;

/**
 * @author lee
 * @date 2022/11/4
 */
public interface Rule {
    String key();

    /**
     * 格式化
     * @param value
     * @return
     */
    String format(Object value);
}
