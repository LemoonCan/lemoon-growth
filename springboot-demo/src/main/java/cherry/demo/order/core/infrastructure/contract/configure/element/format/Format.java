package cherry.demo.order.core.infrastructure.contract.configure.element.format;

import java.util.List;

/**
 * 格式化
 * @author lee
 * @date 2022/11/4
 */
public abstract class Format {
    protected String pattern;
    public Format(String pattern) {
        this.pattern = pattern;
    }

    /**
     * 转换
     * @param origin
     * @return
     */
    public abstract String translate(Object origin);

    /**
     * 格式化配置项
     * @return
     */
    public abstract List<String> patternOptions();
}
