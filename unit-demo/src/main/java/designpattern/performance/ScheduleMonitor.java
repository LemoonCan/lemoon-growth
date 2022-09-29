package designpattern.performance;

import designpattern.performance.show.IShow;
import designpattern.performance.statistics.DataTidy;
import designpattern.performance.statistics.InterfaceStatisticsInfo;
import designpattern.performance.storage.IInterfaceRecordQuery;
import designpattern.performance.storage.InterfaceRecordInfo;
import designpattern.performance.storage.RedisInterfaceRecordQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 * @date 2022/9/29
 */
public class ScheduleMonitor {
    private IInterfaceRecordQuery interfaceRecordQuery;
    private DataTidy dataTidy;
    private IShow show;

    public ScheduleMonitor(IShow show) {
        this.interfaceRecordQuery = new RedisInterfaceRecordQuery();
        this.dataTidy = new DataTidy();
        this.show = show;
    }

    public ScheduleMonitor(IInterfaceRecordQuery interfaceRecordQuery, DataTidy dataTidy, IShow show) {
        this.interfaceRecordQuery = interfaceRecordQuery;
        this.dataTidy = dataTidy;
        this.show = show;
    }

    public void start(){
        //TODO 线程池

        //查询指定时间的数据
        List<InterfaceRecordInfo> recordInfoList = new ArrayList<>();
        //数据整理
        List<InterfaceStatisticsInfo> statisticsInfoList = dataTidy.generateReports(recordInfoList);
        //输出到相应终端
        show.trigger(statisticsInfoList);
    }
}
