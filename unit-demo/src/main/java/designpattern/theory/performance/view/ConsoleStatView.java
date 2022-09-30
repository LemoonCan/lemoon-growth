package designpattern.theory.performance.view;

import com.alibaba.fastjson2.JSON;
import designpattern.theory.performance.stat.RequestStatInfo;

import java.util.List;

/**
 * @author lee
 * @date 2022/9/29
 */
public class ConsoleStatView implements IStatView {
    @Override
    public void output(List<RequestStatInfo> statInfos) {
        System.out.println(JSON.toJSONString(statInfos));
    }
}
