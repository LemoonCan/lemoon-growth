package cherry.demo.order.core.infrastructure.contract.configure.element.rule;

import cherry.demo.order.api.dto.ItemDTO;
import cherry.demo.order.core.infrastructure.contract.configure.element.format.Format;

import java.util.Arrays;
import java.util.List;

/**
 * 内部数据规则
 * @author lee
 * @date 2022/11/4
 */
public class InternRule extends AbstractRuleAndConfigure {
    private String tableColumn;
    private Format format;

    public InternRule(String tableColumn, Format format) {
        this.tableColumn = tableColumn;
        this.format = format;
    }

    public String getTableColumn() {
        return tableColumn;
    }
    public String getTable() {
        return tableColumn.split("\\.")[0];
    }

    @Override
    public List<ItemDTO> configures() {
        return Arrays.asList(
                new ItemDTO("tableColumn","表字段"),
                new ItemDTO("format","格式化参数")
        );
    }

    @Override
    public String key() {
        return tableColumn;
    }

    @Override
    public String format(Object value) {
        return format.translate(value);
    }
}
