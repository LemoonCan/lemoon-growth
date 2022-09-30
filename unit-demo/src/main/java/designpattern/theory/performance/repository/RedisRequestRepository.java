package designpattern.theory.performance.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lee
 * @date 2022/9/29
 */
public class RedisRequestRepository implements IRequestRepository {
    private List<RequestRecordInfo> requestRecordInfos = new CopyOnWriteArrayList<>();

    @Override
    public List<RequestRecordInfo> overTimeRecord(Long startTimeMills, Long endTimeMills) {
        return requestRecordInfos;
    }

    @Override
    public void insert(RequestRecordInfo recordInfo) {
        requestRecordInfos.add(recordInfo);
    }
}
