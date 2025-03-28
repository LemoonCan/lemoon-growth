package designpattern.behavior.state;

import designpattern.behavior.state.classify.IMario;

/**
 * @author lee
 * @since 2022/10/18
 */
public class StateMachine {
    private IMario curState;

    public void obtainMushroom() {
        curState.obtainMushroom(this);
    }

    public void obtainCape() {
        curState.obtainCape(this);
    }

    public void obtainFire() {
        curState.obtainFire(this);
    }

    public void meetMonster() {
        curState.meetMonster(this);
    }

    public void setCurState(IMario curState) {
        this.curState = curState;
    }
}
