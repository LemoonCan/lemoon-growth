package designpattern.theory.performance.collect;

import designpattern.theory.performance.repository.IRequestRepository;
import designpattern.theory.performance.repository.RequestRecordInfo;

import java.util.Calendar;

/**
 * @author lee
 * @date 2022/9/30
 */
public class MetricsCollector {
    private IRequestRepository requestRepository;

    public MetricsCollector(IRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void recordRequest(String apiName, Long startTimeMillis, Long endTimeMillis) {
        Calendar startC = Calendar.getInstance();
        startC.setTimeInMillis(startTimeMillis);

        Calendar endC = Calendar.getInstance();
        endC.setTimeInMillis(endTimeMillis);

        RequestRecordInfo recordInfo = new RequestRecordInfo(apiName,startC.getTime(),endC.getTime());
        requestRepository.insert(recordInfo);
    }
}
