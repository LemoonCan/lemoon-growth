package designpattern.behavior.state.classify;

import designpattern.behavior.state.State;
import designpattern.behavior.state.StateMachine;

/**
 * @author lee
 * @date 2022/10/18
 */
public class SmallMario implements IMario{
    private State state = State.SMALL;
    private final SmallMario instance = new SmallMario();

    public SmallMario getInstance() {
        return instance;
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public void obtainMushroom(StateMachine stateMachine) {
        stateMachine.setCurState(new SuperMario());
    }

    @Override
    public void obtainCape(StateMachine stateMachine) {
        stateMachine.setCurState(new CapeMario());
    }

    @Override
    public void obtainFire(StateMachine stateMachine) {
        stateMachine.setCurState(new FireMario());
    }

    @Override
    public void meetMonster(StateMachine stateMachine) {
        stateMachine.setCurState(new SmallMario());
    }
}
