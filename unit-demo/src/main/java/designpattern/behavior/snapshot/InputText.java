package designpattern.behavior.snapshot;

/**
 * @author lee
 * @date 2022/10/21
 */
public class InputText {
    private StringBuilder text = new StringBuilder();
    public String getText(){
        return text.toString();
    }

    public void append(String s){
        text.append(s);
    }

    public Snapshot createSnapshot(){
        return new Snapshot(text.toString());
    }

    public void restoreSnapshot(Snapshot snapshot){
        this.text.replace(0,text.length(),snapshot.getText());
    }
}
