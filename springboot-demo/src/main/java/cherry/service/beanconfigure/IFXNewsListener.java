package cherry.service.beanconfigure;

/**
 * @author lee
 * @date 8/31/21
 */
public interface IFXNewsListener {
    String[] getAvailableNewIds();
    FXNewsBean getNewsByPrimaryKey(String key);
    void postprocessIfNecessary(String newsId);
}
