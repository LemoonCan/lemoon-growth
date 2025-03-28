package designpattern.theory.performance.repository;

import java.util.List;

/**
 * @author lee
 * @since 2022/9/29
 */
public interface IRequestRepository {
    /**
     * 一段时间的记录
     * @param startTimeMills
     * @param endTimeMills
     * @return
     */
    List<RequestRecordInfo> overTimeRecord(Long startTimeMills, Long endTimeMills);

    /**
     * 插入记录
     * @param recordInfo
     */
    void insert(RequestRecordInfo recordInfo);
}
