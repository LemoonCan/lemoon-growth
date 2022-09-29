package designpattern.performance.show;

import designpattern.performance.statistics.InterfaceStatisticsInfo;

import java.util.List;

/**
 * @author lee
 * @date 2022/9/29
 */
public interface IShow {
    void trigger(List<InterfaceStatisticsInfo> statistic);
}
