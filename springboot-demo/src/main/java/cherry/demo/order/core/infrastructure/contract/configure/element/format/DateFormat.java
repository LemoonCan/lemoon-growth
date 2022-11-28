package cherry.demo.order.core.infrastructure.contract.configure.element.format;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * 日期格式化
 * @author lee
 * @date 2022/11/4
 */
public class DateFormat extends Format {

    public DateFormat(String pattern) {
        super(pattern);
    }

    @Override
    public String translate(Object origin) {
        if(origin instanceof Date) {
            SimpleDateFormat format = new SimpleDateFormat(this.pattern);
            return format.format(origin);
        }
        if(origin instanceof LocalDateTime){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.pattern);
            return ((LocalDateTime)origin).format(formatter);
        }
        throw new IllegalArgumentException(String.format("非法的日期类型%s",origin));
    }

    @Override
    public List<String> patternOptions() {
        return null;
    }
}
