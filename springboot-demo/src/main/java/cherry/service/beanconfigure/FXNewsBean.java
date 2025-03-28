package cherry.service.beanconfigure;

/**
 * @author lee
 * @since 8/31/21
 */
public class FXNewsBean {
    private String name;

    public FXNewsBean(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
