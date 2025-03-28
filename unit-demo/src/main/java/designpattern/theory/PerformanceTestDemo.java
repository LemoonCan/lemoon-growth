package designpattern.theory;

import designpattern.theory.performance.ConsoleReporter;
import designpattern.theory.performance.collect.MetricsCollector;
import designpattern.theory.performance.repository.RequestRepositoryFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * 性能监控
 * TODO 扩展功能
 * 1.支持文本不同格式自由组装
 * 2.自定义显示终端
 * 3.被动统计
 *
 * 测试 仓储层应该如何MOCK
 *
 * 需要考虑的非功能性需求(质量)
 * 1.易用
 * 2.性能
 * 3.扩展性
 * 4.容错性
 * 5.通用性
 *
 * @author lee
 * @since 2022/9/30
 */
public class PerformanceTestDemo {
    public static void main(String[] args) {
        ConsoleReporter consoleReporter = new ConsoleReporter();
        consoleReporter.start();

//        EmailReporter emailReporter = new EmailReporter();
//        emailReporter.start();

        MetricsCollector metricsCollector = new MetricsCollector(RequestRepositoryFactory.newInstance());
        Calendar curCal = Calendar.getInstance();
        curCal.setTime(new Date());
        Long start = curCal.getTimeInMillis();
        Long end = start + 10;
        metricsCollector.recordRequest("register", start, end);

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
