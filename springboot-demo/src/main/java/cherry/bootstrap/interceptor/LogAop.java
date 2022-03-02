package cherry.bootstrap.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 2021/11/17
 */
@Component
@Aspect
public class LogAop {
    @Pointcut("execution(* cherry.controller.AopDemo.*(..)) " +
            "|| execution(* cherry.service.aop.*.*(..))")
    public void intercept() {
    }

    @Before("intercept()")
    public void before(JoinPoint point) {
        String name = point.getSignature().getName();
        System.out.println(name + "方法执行前...");
    }

    @After("intercept()")
    public void after(JoinPoint point) {
        String name = point.getSignature().getName();
        System.out.println(name + "方法执行后...");
    }

    @AfterReturning(value = "intercept()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法返回值为: " + result);
    }

    @AfterThrowing(value = "intercept()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法发生异常: " + e.getMessage());
    }
}
