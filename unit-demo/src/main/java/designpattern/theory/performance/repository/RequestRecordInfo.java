package designpattern.theory.performance.repository;

import java.util.Calendar;
import java.util.Date;

/**
 * 接口记录
 * @author lee
 * @date 2022/9/29
 */
public class RequestRecordInfo {
    private Long id;
    private String name;
    private Date startTime;
    private Date endTime;

    public RequestRecordInfo(){
    }
    public RequestRecordInfo(String name, Date startTime, Date endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long responesTime(){
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endTime);

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startTime);

        return endCal.getTimeInMillis()-startCal.getTimeInMillis();
    }

    public String getName() {
        return name;
    }
}
