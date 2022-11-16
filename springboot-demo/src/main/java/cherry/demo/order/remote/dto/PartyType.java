package cherry.demo.order.remote.dto;

/**
 * @author lee
 * @date 2022/11/4
 */
public enum PartyType {
    user(1),
    self_company(2),
    other_company(3),
    ;
    private int code;

    PartyType(int code) {
        this.code = code;
    }
}
