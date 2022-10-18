package designpattern.behavior.state.classify;

import designpattern.behavior.state.State;
import designpattern.behavior.state.StateMachine;

/**
 * @author lee
 * @date 2022/10/18
 */
public interface IMario {
    State state();
    void obtainMushroom(StateMachine stateMachine);
    void obtainCape(StateMachine stateMachine);
    void obtainFire(StateMachine stateMachine);
    void meetMonster(StateMachine stateMachine);
}