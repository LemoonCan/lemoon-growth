package cherry.service.beanconfigure;

/**
 * @author lee
 * @date 8/31/21
 */
public class DowJonesNewsListener implements IFXNewsListener {
    @Override
    public String[] getAvailableNewIds() {
        String[] news = {"morning", "noon", "night"};
        return news;
    }

    @Override
    public FXNewsBean getNewsByPrimaryKey(String key) {
        System.out.println(key + "新闻");
        return new FXNewsBean(key + " " + System.currentTimeMillis());
    }

    @Override
    public void postprocessIfNecessary(String newsId) {
        System.out.println(newsId + " post process");
    }
}
