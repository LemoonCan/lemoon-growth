package designpattern.theory.performance;

import designpattern.theory.performance.repository.RequestRepositoryFactory;
import designpattern.theory.performance.view.ConsoleStatView;
import designpattern.theory.performance.view.IStatView;
import designpattern.theory.performance.stat.DataTidy;
import designpattern.theory.performance.repository.IRequestRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @since 2022/9/30
 */
public class ConsoleReporter extends ScheduleReporter{

    public ConsoleReporter() {
        //违背了依赖注入，但不妨碍，提供默认实现是将调用者不关心的东西隐藏
        super(RequestRepositoryFactory.newInstance(),new DataTidy(),new ConsoleStatView());
    }

    public ConsoleReporter(IRequestRepository requestRepository, DataTidy dataTidy, IStatView statView) {
        super(requestRepository, dataTidy, statView);
    }

    @Override
    public void start() {
        this.executor.scheduleAtFixedRate(() -> {
            Calendar cur = Calendar.getInstance();
            cur.setTime(new Date());
            Long end = cur.getTimeInMillis();
            Long start = end - 10;
            this.doStart(start, end);
        }, 0, 10, TimeUnit.SECONDS);
    }
}
