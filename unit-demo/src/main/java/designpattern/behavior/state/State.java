package designpattern.behavior.state;

/**
 * @author lee
 * @since 2022/10/18
 */
public enum State {
    /**
     * 小马里奥
     */
    SMALL(0),
    /**
     * 超级马里奥
     */
    SUPER(1),
    /**
     * 火焰马里奥
     */
    FIRE(2),
    /**
     * 斗篷马里奥
     */
    CAPE(3),
    ;
    private Integer code;

    State(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
