package designpattern.theory.performance.stat;

/**
 * @author lee
 * @date 2022/9/29
 */
public class RequestStatInfo {
    private String name;
    private Long avg;
    private Long max;
    private Long min;

    public RequestStatInfo(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvg(Long avg) {
        this.avg = avg;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public Long getAvg() {
        return avg;
    }

    public Long getMax() {
        return max;
    }

    public Long getMin() {
        return min;
    }
}
