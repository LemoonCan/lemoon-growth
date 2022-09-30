package designpattern.theory.performance.stat;

import designpattern.theory.performance.repository.RequestRecordInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lee
 * @date 2022/9/29
 */
public class DataTidy {
    public List<RequestStatInfo> generateReports(List<RequestRecordInfo> records) {
        if(Objects.isNull(records)||records.size()==0){
            return new ArrayList<>();
        }

        Map<String,List<RequestRecordInfo>> interfaceGroup = records.stream().collect(Collectors.groupingBy(RequestRecordInfo::getName));
        List<RequestStatInfo> statisticsInfos = new ArrayList<>();
        interfaceGroup.forEach((key,value)->{
            RequestStatInfo statisticsInfo = new RequestStatInfo(key);
            statisticsInfo.setAvg(avg(value));
            statisticsInfo.setMax(max(value));
            statisticsInfo.setMin(min(value));
            statisticsInfos.add(statisticsInfo);
        });

        return statisticsInfos;
    }

    private Long avg(List<RequestRecordInfo> records) {
        Long sum = records.stream()
                .map(RequestRecordInfo::responesTime)
                .reduce(0L, (a, b) -> a + b);
        return sum / records.size();
    }

    private Long max(List<RequestRecordInfo> records) {
        Long max = records.stream()
                .map(RequestRecordInfo::responesTime)
                .reduce(-1L,(a,b) -> b>a?b:a);
        return max;
    }

    private Long min(List<RequestRecordInfo> records) {
        Long min = records.stream()
                .map(RequestRecordInfo::responesTime)
                .reduce(Long.MAX_VALUE,(a,b) -> b<a?b:a);
        return min;
    }

    //增加统计数据
}
