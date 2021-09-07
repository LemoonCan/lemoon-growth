package cherry.beanconfigure;

/**
 * @author lee
 * @date 8/31/21
 */
public class DowJonesNewsPersistener implements IFXNewsPersistener {
    @Override
    public void persistNewsBean(FXNewsBean newsBean) {
        System.out.println("Persisting " + newsBean);
    }
}
