package designpattern.theory.performance.repository;

/**
 * @author lee
 * @date 2022/9/30
 */
public class RequestRepositoryFactory {
    private static IRequestRepository requestRepository = new RedisRequestRepository();
    public static IRequestRepository newInstance(){
        return requestRepository;
    }
}
