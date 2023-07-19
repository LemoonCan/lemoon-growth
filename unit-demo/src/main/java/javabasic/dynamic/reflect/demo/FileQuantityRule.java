package javabasic.dynamic.reflect.demo;

/**
 * @author lee
 * @since 2023/4/24
 */
public class FileQuantityRule implements Rule<FileBlock>{
    public static final String ID = "fileQuantityRule";
    private final Integer lowNumber;
    private final Integer highNumber;

    public FileQuantityRule(Integer lowNumber, Integer highNumber){
        this.lowNumber = lowNumber;
        this.highNumber = highNumber;
    }

    @Override
    public String identity() {
        return ID;
    }

    @Override
    public void verify(FileBlock block) {
        System.out.printf("fileQuantityRule verify: lowNumber %s,highNumber %s", lowNumber, highNumber);
    }
}
