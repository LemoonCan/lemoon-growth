package designpattern.behavior.state.classify;

import designpattern.behavior.state.State;
import designpattern.behavior.state.StateMachine;

/**
 * @author lee
 * @date 2022/10/18
 */
public class SuperMario implements IMario{
    private State state = State.SUPER;
    private final SuperMario instance = new SuperMario();

    public SuperMario getInstance() {
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
