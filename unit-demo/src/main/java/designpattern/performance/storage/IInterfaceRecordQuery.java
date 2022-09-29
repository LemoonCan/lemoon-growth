package designpattern.performance.storage;

import java.util.Date;
import java.util.List;

/**
 * @author lee
 * @date 2022/9/29
 */
public interface IInterfaceRecordQuery {
    /**
     * 一段时间的记录
     * @param startTime
     * @param endTime
     * @return
     */
    List<InterfaceRecordInfo> overTimeRecord(Date startTime, Date endTime);
}
