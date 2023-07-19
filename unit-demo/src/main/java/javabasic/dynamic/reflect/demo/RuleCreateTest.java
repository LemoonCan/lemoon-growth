package javabasic.dynamic.reflect.demo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @since 2023/4/25
 */
public class RuleCreateTest {
    public static void main(String[] args) {
        Map<String,String[]> map = new HashMap<>(4);
        map.put("rules",new String[]{"{\"fileQuantityRule\":[2,9]}]}"});

        String config = JSON.toJSONString(map);
        JSONArray rulesArray = JSON.parseObject(config).getJSONArray("rules");
        for (int i = 0; i < rulesArray.size(); i++) {
            Map<String,Object> ruleObject = rulesArray.getJSONObject(i);
            for (Map.Entry<String, Object> entry : ruleObject.entrySet()) {
                String ruleName = entry.getKey();
                JSONArray params = (JSONArray) entry.getValue();
                Rule<AbstractDataBlock<?>> x =  RuleFactory.createRule(ruleName, params.toArray());
                x.verify(new FileBlock("1"));
            }
        }
    }
}
