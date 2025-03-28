package javabasic.judge;

/**
 * @author lee
 * @since 8/10/21
 * 默认不启动断言检查，一般用于开发测试，是一种调试方式
 * 若开启断言检查(vm options添加参数 -ea 或 -enableassertions)，断言为false会抛出AssertionError
 */
public class AssertDemo {
    public static void main(String[] args) {
        try {
            String s = "test";
            assert s == null;
            System.out.println("执行");
        } catch (Exception e){
            System.err.println(e);
        }
    }
}
