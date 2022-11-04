package cherry.demo.contract.configure.element.format;

import java.util.List;

/**
 * @author lee
 * @date 2022/11/4
 */
public class DateFormat extends Format{

    public DateFormat(String pattern) {
        super(pattern);
    }

    @Override
    public String translate(Object origin) {
        return null;
    }

    @Override
    public List<String> patternOptions() {
        return null;
    }
}
