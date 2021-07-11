/**
 * @author lee
 * @date 6/9/21
 * AOP 的一个简单实现
 * 1.扫描声明@Aspect的class文件
 * 2.初始化类与拦截器的关系，存储在map中(key:class,value:(某个点的拦截器列表(如before:[a,b])))
 * 3.声明@Aspect的类实例化时，替换实例为代理类实例(那么实际执行的实例就是代理类了)
 */
package javabasic.dynamic.agent.application.aop;