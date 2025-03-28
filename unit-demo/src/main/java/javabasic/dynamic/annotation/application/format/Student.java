package javabasic.dynamic.annotation.application.format;

import java.util.Date;

/**
 * @author lee
 * @since 6/3/21
 */
public class Student {
    @Label("姓名")
    private String name;

    @Label("出生日期")
    @Format(pattern = "yyyy-MM-dd")
    private Date born;

    @Label("分数")
    private double score;

    public Student(String name, Date born, double score) {
        this.name = name;
        this.born = born;
        this.score = score;
    }
}
