package cherry.demo.order.core.infrastructure.contract.configure.element.rule;

/**
 * 对外的抽象规则
 * @author lee
 * @date 2022/11/4
 */
public interface Rule {
    /**
     * 关键字
     * @return
     */
    String key();

    /**
     * 格式化
     * @param value
     * @return
     */
    String format(Object value);
}
