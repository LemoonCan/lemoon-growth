package designpattern.performance.storage;

import java.util.Calendar;
import java.util.Date;

/**
 * 接口记录
 * @author lee
 * @date 2022/9/29
 */
public class InterfaceRecordInfo {
    private Long id;
    private String name;
    private Date startTime;
    private Date endTime;

    public Long responesTime(){
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endTime);

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startTime);

        return endCal.getTimeInMillis()-startCal.getTimeInMillis();
    }

}
