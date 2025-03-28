package designpattern.behavior.snapshot;

import java.util.Scanner;

/**
 * @author lee
 * @since 2022/10/21
 */
public class Demo {
    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapshotHolder snapshotHolder = new SnapshotHolder();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String input = scanner.next();
            if(":list".equals(input)){
                System.out.println(inputText.getText());
            }else if(":undo".equals(input)){
                Snapshot snapshot = snapshotHolder.popSnapShot();
                inputText.restoreSnapshot(snapshot);
            }else{
                snapshotHolder.pushSnapShot(inputText);
                inputText.append(input);
            }
        }
    }
}
