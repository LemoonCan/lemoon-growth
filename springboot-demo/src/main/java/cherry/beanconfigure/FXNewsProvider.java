package cherry.beanconfigure;

import java.util.Objects;

/**
 * @author lee
 * @date 8/31/21
 */
public class FXNewsProvider {
    private IFXNewsListener listener;
    private IFXNewsPersistener persistener;

    public FXNewsProvider() {
        this.listener = new DowJonesNewsListener();
        this.persistener = new DowJonesNewsPersistener();
    }

    //IOC方式 构造方法注入
    public FXNewsProvider(IFXNewsListener listener, IFXNewsPersistener persistener) {
        this.listener = listener;
        this.persistener = persistener;
    }
    //IOC方式 setter
    //IOC方式 接口注入

    public void getAndPersistNews() {
        String[] newsIds = listener.getAvailableNewIds();
        if (Objects.isNull(newsIds) || newsIds.length <= 0) {
            return;
        }
        for (String newsId : newsIds) {
            FXNewsBean newsBean = listener.getNewsByPrimaryKey(newsId);
            persistener.persistNewsBean(newsBean);
            listener.postprocessIfNecessary(newsId);
        }
    }
}
