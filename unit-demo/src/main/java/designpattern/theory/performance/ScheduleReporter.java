package designpattern.theory.performance;

import designpattern.theory.performance.view.IStatView;
import designpattern.theory.performance.stat.DataTidy;
import designpattern.theory.performance.stat.RequestStatInfo;
import designpattern.theory.performance.repository.IRequestRepository;
import designpattern.theory.performance.repository.RequestRecordInfo;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author lee
 * @date 2022/9/29
 */
public abstract class ScheduleReporter {
    protected IRequestRepository requestRepository;
    protected DataTidy dataTidy;
    protected IStatView statView;

    protected ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public ScheduleReporter(IRequestRepository requestRepository, DataTidy dataTidy, IStatView statView) {
        this.requestRepository = requestRepository;
        this.dataTidy = dataTidy;
        this.statView = statView;
    }

    /**
     * 开始
     */
    public abstract void start();

    protected void doStart(Long startTime,Long endTime){
        //查询指定时间的数据
        List<RequestRecordInfo> recordInfoList = requestRepository.overTimeRecord(startTime,endTime);
        //数据整理
        List<RequestStatInfo> statisticsInfoList = dataTidy.generateReports(recordInfoList);
        //输出到相应终端
        statView.output(statisticsInfoList);
    }
}
