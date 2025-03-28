package designpattern.behavior.snapshot;

/**
 * @author lee
 * @since 2022/10/21
 */
public class Snapshot {
    private String text;

    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
