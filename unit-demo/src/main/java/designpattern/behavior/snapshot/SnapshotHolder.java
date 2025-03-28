package designpattern.behavior.snapshot;

import java.util.Stack;

/**
 * @author lee
 * @since 2022/10/21
 */
public class SnapshotHolder {
    Stack<Snapshot> snapshots = new Stack<>();

    public void pushSnapShot(InputText inputText){
        snapshots.push(inputText.createSnapshot());
    }

    public Snapshot popSnapShot(){
        return snapshots.pop();
    }
}
