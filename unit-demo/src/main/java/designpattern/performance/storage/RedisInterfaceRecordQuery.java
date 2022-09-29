package designpattern.performance.storage;

import java.util.Date;
import java.util.List;

/**
 * @author lee
 * @date 2022/9/29
 */
public class RedisInterfaceRecordQuery implements IInterfaceRecordQuery{
    @Override
    public List<InterfaceRecordInfo> overTimeRecord(Date startTime, Date endTime) {
        return null;
    }
}
