package designpattern.behavior.state.classify;

import designpattern.behavior.state.State;
import designpattern.behavior.state.StateMachine;

/**
 * @author lee
 * @date 2022/10/18
 */
public class CapeMario implements IMario{
    private State state = State.CAPE;
    private final CapeMario instance = new CapeMario();

    public CapeMario getInstance() {
        return instance;
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public void obtainMushroom(StateMachine stateMachine) {
    }

    @Override
    public void obtainCape(StateMachine stateMachine) {

    }

    @Override
    public void obtainFire(StateMachine stateMachine) {

    }

    @Override
    public void meetMonster(StateMachine stateMachine) {
        stateMachine.setCurState(new FireMario());
    }
}
