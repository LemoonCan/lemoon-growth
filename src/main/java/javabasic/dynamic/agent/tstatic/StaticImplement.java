package javabasic.dynamic.agent.tstatic;

/**
 * @author lee
 * @date 5/30/21
 */
public class StaticImplement {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        System.out.println(subject.say("Moon",0));
    }
}

interface Subject {
    /**
     * 介绍
     *
     * @param name
     * @param age
     * @return
     */
    String say(String name, int age);
}

class RealSubject implements Subject {
    @Override
    public String say(String name, int age) {
        return name + " " + age;
    }
}