package designpattern.theory.performance.view;

import designpattern.theory.performance.stat.RequestStatInfo;

import java.util.List;

/**
 * @author lee
 * @date 2022/9/29
 */
public interface IStatView {
    void output(List<RequestStatInfo> statInfos);
}
