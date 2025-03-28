package designpattern.theory.performance;

import designpattern.theory.performance.repository.RequestRepositoryFactory;
import designpattern.theory.performance.view.EmailStatView;
import designpattern.theory.performance.view.IStatView;
import designpattern.theory.performance.stat.DataTidy;
import designpattern.theory.performance.repository.IRequestRepository;

/**
 * @author lee
 * @since 2022/9/30
 */
public class EmailReporter extends ScheduleReporter {
    public EmailReporter() {
        super(RequestRepositoryFactory.newInstance(), new DataTidy(), new EmailStatView());
    }

    public EmailReporter(IRequestRepository requestRepository, DataTidy dataTidy, IStatView statView) {
        super(requestRepository, dataTidy, statView);
    }

    @Override
    public void start() {
    }
}
