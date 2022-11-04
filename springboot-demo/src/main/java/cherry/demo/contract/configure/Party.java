package cherry.demo.contract.configure;

import java.util.Map;

/**
 * @author lee
 * @date 2022/11/4
 */
public class Party {
    private Type type;
    private Position position;

    public enum Type {
        FINANCE_LEASING_HEAD,
        CAR_SERVICE,
        SHOP,
        CUSTOMER,
        MORTGAGE,
        ;
    }

    public static class Position {
        //坐标、关键字 关联使用何类参数
        private String type;
        private Map<String,Object> params;
    }
}
