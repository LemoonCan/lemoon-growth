package cherry.demo.order.core.infrastructure.contract.parambuilder;

import cherry.demo.order.core.adapter.IBuildExternValues;
import cherry.demo.order.core.domain.repository.Sql;
import cherry.demo.order.core.infrastructure.contract.configure.BizModel;
import cherry.demo.order.core.infrastructure.contract.configure.ContractConfigure;
import cherry.demo.order.core.infrastructure.contract.common.ContractNoKeyGenerator;
import cherry.demo.order.core.infrastructure.contract.configure.element.rule.ExternRule;
import cherry.demo.order.core.infrastructure.contract.configure.element.rule.InternRule;
import cherry.demo.order.core.infrastructure.contract.configure.element.rule.Rule;

import java.util.*;

/**
 * @author lee
 * @date 2022/11/4
 */
public class ValuesPreProcessingImpl implements ValuesPreProcessing {
    static Map<String, Map<String, Object>> orderCacheValues = new HashMap<>(4);

    private Sql sql;

    private Map<String, IBuildExternValues> buildExternValuesMap;

    @Override
    public void cache(String orderNo, List<ContractConfigure> contractConfigures) {
        orderCacheValues.put(orderNo, this.query(orderNo, contractConfigures));
    }

    @Override
    public void clearCache(String orderNo) {
        orderCacheValues.remove(orderNo);
    }

    @Override
    public Map<String, Object> query(String orderNo, List<ContractConfigure> contractConfigures) {
        if (orderCacheValues.containsKey(orderNo)) {
            return orderCacheValues.get(orderNo);
        }

        Map<String, Object> values = new HashMap<>(16);
        buildConNum(orderNo, contractConfigures, values);
        buildInternValues(orderNo, contractConfigures, values);
        buildExternValues(orderNo, contractConfigures, values);

        return Collections.unmodifiableMap(values);
    }

    @Override
    public Map<String, Object> query(String orderNo, ContractConfigure contractConfigure) {
        return orderCacheValues.get(orderNo);
    }

    @Override
    public String getAContractNo(Map<String, Object> values, ContractConfigure configure) {
        return (String) values.get(ContractNoKeyGenerator.build(configure.getContractType()));
    }


    private void buildConNum(String orderNo, List<ContractConfigure> contractConfigures, Map<String, Object> values) {
        for (ContractConfigure item : contractConfigures) {
            values.put(ContractNoKeyGenerator.build(item.getContractType()), UUID.randomUUID());
        }
    }

    /**
     * 取内部值
     *
     * @param orderNo
     * @param contractConfigures
     * @param values
     */
    private void buildInternValues(String orderNo, List<ContractConfigure> contractConfigures, Map<String, Object> values) {
        //筛选内部规则
        List<InternRule> internRules = new ArrayList<>();
        for (ContractConfigure item : contractConfigures) {
            Map<String, Rule> elementsMap = item.getElementsMap();
            elementsMap.forEach((key, value) -> {
                if (value instanceof InternRule) {
                    internRules.add((InternRule) value);
                }
            });
        }

        //拼接查询SQL
        StringBuilder sqlBuilder = new StringBuilder("select ");
        Set<String> tableSet = new HashSet<>();
        for (InternRule rule : internRules) {
            sqlBuilder.append(rule.getTableColumn());
            sqlBuilder.append(" as ");
            sqlBuilder.append(rule.key());
            sqlBuilder.append(" ,");
            tableSet.add(rule.getTable());
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append("from ");
        String lastTable = null;
        for (String table : tableSet) {
            if (lastTable != null) {
                sqlBuilder.append("left join ");
                sqlBuilder.append(table);
                sqlBuilder.append(" on ");
                sqlBuilder.append(lastTable);
                sqlBuilder.append(".order_no=");
                sqlBuilder.append(table);
                sqlBuilder.append(".order_no ");
            } else {
                sqlBuilder.append(table);
                sqlBuilder.append(" ");
            }
            lastTable = table;
        }
        sqlBuilder.append("where cl_order.order_no=");
        sqlBuilder.append(orderNo);
        sqlBuilder.append(";");

        values.putAll(sql.queryExecute(sqlBuilder.toString()));
    }

    /**
     * 取外部值
     *
     * @param orderNo
     * @param contractConfigures
     * @param values
     */
    private void buildExternValues(String orderNo, List<ContractConfigure> contractConfigures, Map<String, Object> values) {
        //1.业务线策略模式设置外部值
        BizModel bizModel = contractConfigures.get(0).getBizModel();
        IBuildExternValues buildExternValues = buildExternValuesMap.get(bizModel.getBusinessType()+"_"+bizModel.getFund());
        values.putAll(buildExternValues.values(orderNo));

        //2.校验需要的外部值是否都已设置
        for (ContractConfigure item : contractConfigures) {
            Map<String, Rule> elementsMap = item.getElementsMap();
            elementsMap.forEach((key, value) -> {
                if (value instanceof ExternRule) {
                    if (!values.containsKey(value.key())) {
                        throw new IllegalArgumentException(String.format("外部参数未设置%s参数", value.key()));
                    }
                }
            });
        }
    }
}
